import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import oscP5.*; 
import netP5.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class LissajousShapes_002_3D extends PApplet {





OscP5 oscP5;
NetAddress myRemoteLocation;

int points = 600;
int ms;
float freqX = 15.23f;
float freqY = 2.02f;
float freqZ = 1.5f;
float phiX = 0;
float phiY = 77;
float x, y, angle;

boolean bump;

float r, g, b;

float zoom = -100;

PVector lissajousPoints[];

int[] colors = new int[points];

public void setup() {
	size(displayWidth, displayHeight, OPENGL);
	// setup OSC networking (see OSC.pde)
	oscSetup();
	// send those Processing parameters to Max that should be controllable
	// from within Ableton / MaxForLive
	oscSendParametersToMax();
	calculateLissajousPoints();
	calculateColors();
}

public void draw() {

	ms = millis();
	// if (ms % 500 <= 150){
	if (bump){
		zoom+= 55;
	}
	else {
		zoom-= 20;
	}

	zoom = constrain(zoom, -1200, 100);

	// drawLissajous2D();
	drawLissajous3D();

	fill(255, 10);

	frame.setTitle(" " + frameRate);
}

public void calculateLissajousPoints(){
	lissajousPoints = new PVector[points+1];
	float f = width/5;

	for (int i = 0; i <= points; i++){
		float angle = map(i, 0, points, 0, TWO_PI);
		float x = sin(angle * freqX + radians(phiX)) * sin(angle * 2) * f;
		float y = sin(angle * freqY + radians(phiY)) * f;
		float z = sin(angle * freqZ) * f;
		lissajousPoints[i] = new PVector(x, y, z);
	}
}

public void drawLissajous2D(){
	fill(0, 100);
	noStroke();
	rect(0, 0, width, height);
	noFill();
	stroke(255, 200);
	translate(width/2, height/2, zoom);

	beginShape();
	for (int i = 0; i <= points; i++){
		angle = map(i, 0, points, 0, TWO_PI);
		x = sin(angle * freqX + radians(phiX));
		y = sin(angle * freqY);

		// x = x * width*0.45;
		// y = y * 200;

		x = x * 400;
		y = y * 400;

		vertex(x, y);
	}
	endShape();

	phiX = (phiX + 0.5f) % 360;

	freqX += 0.00005f;
	freqY += 0.0000025f;
	freqX = freqX % 10;
	freqY = freqY % 50;
	if (frameCount % 6 == 0){
		freqX = random(3);
		freqY = random(3);
	}
}

public void drawLissajous3D(){
	background(0);
	lights();

	if (ms % 2000 <= 10){
		// println(ms);
		freqX = random(20);
		freqY = random(5);
		freqZ = random(10);

		calculateColors();
	}

	phiX = (phiX + 0.25f) % 360;
	phiY = (phiY + 0.25f) % 360;

	calculateLissajousPoints();

	translate(width/2, height/2, zoom);

	noStroke();
	beginShape(TRIANGLE_FAN);
	for (int i = 0; i < points-2; i++){
		if (i % 3 == 0) {
			fill(colors[i], 10);
			vertex(0,0,0);
			fill(colors[i], 120);
			vertex(lissajousPoints[i].x, lissajousPoints[i].y, lissajousPoints[i].z);
			vertex(lissajousPoints[i+2].x, lissajousPoints[i+2].y, lissajousPoints[i+2].z);
		}
	}
	endShape();

	stroke(255, 155);
	noFill();
	beginShape();
	for (int i = 0; i < points; i++){
		vertex(lissajousPoints[i].x, lissajousPoints[i].y, lissajousPoints[i].z);
	}
	endShape();
}

public void calculateColors(){
	for (int i = 0; i < points; i++){
		
		if (i % 2 == 0) {
			r = noise(i) * 100 + 155;
			g = noise(i+1) * 20 + 130;
			b = noise(i+2) * 0;
		}
		
		else if (i % 2 == 1) {
			r = noise(i) * 0;
			g = noise(i+1) * 40 + 215;
			b = noise(i+2) * 15 + 240;
		}

		colors[i] = color(r, g, b);

		// println(colors[i]);
	}
}
public void oscSetup() {	

	// start oscP5 at localhost, port 10001
	oscP5 = new OscP5(this, 7001);
	// oscP5 = new OscP5(this, 7003);
	// address for OSC messages to be sent to
	myRemoteLocation = new NetAddress("127.0.0.1", 7002);

}

public void oscSendParametersToMax(){

	OscMessage p5Parameters = new OscMessage("/p5Parameters");

	p5Parameters.add("noiseScale");
	p5Parameters.add("noiseStrength");
	p5Parameters.add("cp1_x");
	p5Parameters.add("cp1_y");
	p5Parameters.add("cp2_x");
	p5Parameters.add("cp2_y");

	oscP5.send(p5Parameters, myRemoteLocation);

}

// function that listens for incoming OSC messages
public void oscEvent(OscMessage theOscMessage) {

	if (theOscMessage.checkAddrPattern("/zoom")){
		bump = PApplet.parseBoolean(theOscMessage.get(0).stringValue());
		println(bump);
	}

	// println(theOscMessage);
	// println(zoom);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "LissajousShapes_002_3D" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
