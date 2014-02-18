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

public class NoiseAgent003_BezierOSC extends PApplet {

// creates a swarm of NoiseAgents, drawn as bezier curves, whose control points
// relate to mouse position

// Jonas Margraf
// jmargraf@berklee.edu

// import libraries




// intialize osc, agent array, objects and variables
OscP5 oscP5;
NetAddress myRemoteLocation;

NoiseAgent[] agents = new NoiseAgent[100];
float noiseScale = 300, noiseStrength = 10;
float control_point1_x, control_point1_y;
float control_point2_x, control_point2_y;
boolean offscreen = false, clear_screen = false;

int pause = 0;

public void setup() {

	// setup as fullscreen, using OpenGL renderer
	size(displayWidth, displayHeight, OPENGL);
	// setup OSC networking (see OSC.pde)
	oscSetup();
	// send those Processing parameters to Max that should be controllable
	// from within Ableton / MaxForLive
	oscSendParametersToMax();
	// set background & drawing colors, cursor type, fill mode
	background(255);
	noFill();
	cursor(CROSS);
	stroke(0xffFF2424, 2);

	// iterate over array to create the agents
	for (int i = 0; i < agents.length; i++) {
		agents[i] = new NoiseAgent();
	}

}

// iterate over array during each frame to draw agents' movement
public void draw() {

	if (clear_screen) {
		fill(255, 255);
		rect(0, 0, width, height);
		noFill();
		clear_screen = false;
	}

	// background(0, 1);
	for (int i = 0; i < agents.length; i++) {
		agents[i].move();
	}

}
public void oscSetup() {	

	// start oscP5 at localhost, port 10001
	oscP5 = new OscP5(this, 10001);
	// address for OSC messages to be sent to
	myRemoteLocation = new NetAddress("127.0.0.1", 10002);

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

	// stop draw() loop when Live's is not playing
	if (theOscMessage.checkAddrPattern("stop")) {
		noLoop();
		println(theOscMessage);
	}
	// resume draw() loop when Live is playing
	if (theOscMessage.checkAddrPattern("play")) {
		loop();
		println(theOscMessage);
	}

	// clear screen
	if (theOscMessage.checkAddrPattern("clear")) {
		clear_screen = true;
	}
	
	// seed Perlin noise generator
	// also creates new shade of red (or "black", in case of low values)
	if (theOscMessage.checkAddrPattern("seed")) {
		int newSeed = PApplet.parseInt(random(1000));
		noiseSeed(newSeed);
		stroke(PApplet.parseInt(random(255)), 0, 0, 2);
	}
	
	if (theOscMessage.checkAddrPattern("noiseScale")) {
		noiseScale = theOscMessage.get(0).floatValue() * 300;
		println("noiseScale = " + noiseScale);
	}

	else if (theOscMessage.checkAddrPattern("noiseStrength")) {
			noiseStrength = theOscMessage.get(0).floatValue() * 10;
			println("noiseStrength = " + noiseStrength);
	}
	
	else if (theOscMessage.checkAddrPattern("cp1_x")) {
			control_point1_x = theOscMessage.get(0).floatValue();
			println("control_point1_x = " + control_point1_x);
	}
	
	else if (theOscMessage.checkAddrPattern("cp1_y")) {
			control_point1_x = theOscMessage.get(0).floatValue();
			println("control_point1_y = " + control_point1_y);
	}
	
	else if (theOscMessage.checkAddrPattern("cp2_x")) {
			control_point2_x = theOscMessage.get(0).floatValue();
			println("control_point2_x = " + control_point2_x);
	}
	
	else if (theOscMessage.checkAddrPattern("cp2_y")) {
			control_point2_y = theOscMessage.get(0).floatValue();
			println("control_point2_y = " + control_point2_y);
	}

	// println(theOscMessage);

}
// a class encapsulates the agents that are drawn to the screen
class NoiseAgent {

	PVector p, pOld;
	float angle;

	NoiseAgent() {

		p = new PVector(random(width), random(height));
		pOld = new PVector(p.x, p.y);

	}

	public void move() {

		angle = noise(p.x/noiseScale, p.y/noiseScale) * noiseStrength;

		p.x += cos(angle) * 10;
		p.y += sin(angle) * 10;

	if (p.x < -10) offscreen = true;
	else if (p.x > width + 10) offscreen = true;
	else if (p.y < -10) offscreen = true;
	else if (p.y > height + 10) offscreen = true;

	if (offscreen) {
		p.x = random(width);
		p.y = random(height);
		pOld.set(p);
	}

	bezier(pOld.x, pOld.y, control_point1_x * width, control_point1_y * height, 
			control_point2_x * width, control_point2_y * height, p.x, p.y);

	pOld.set(p);

	offscreen = false;

	}

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "NoiseAgent003_BezierOSC" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
