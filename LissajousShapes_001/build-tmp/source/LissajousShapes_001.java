import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class LissajousShapes_001 extends PApplet {

// Simple exploration of Lissajous shapes made from simple sin/cos functions

// Jonas Margraf
// jmargraf@berklee.edu

int n_points = 2000;
float x, y, freqX, freqY, angle, phiX, phiY;

public void setup() {
	// size(displayWidth, displayHeight, "processing.core.PGraphicsRetina2D");
	size(displayWidth, displayHeight, OPENGL);
	background(0);
	
	freqX = random(10);
	freqY = random(20);
	phiX = 300;
	// phiY = 0;

}

public void draw() {
	// background(0);
	fill(0, 100);
	noStroke();
	rect(0, 0, width, height);
	noFill();
	stroke(255, 200);
	// println(freqX + "\t" + freqY);

	// pushMatrix();
	translate(width/2, height/2);

	beginShape();
	for (int i = 0; i <= n_points; i++){
		angle = map(i, 0, n_points, 0, TWO_PI);
		x = sin(angle * freqX + radians(phiX));
		y = sin(angle * freqY);

		// x = x * width*0.45;
		// y = y * 200;

		x = x * 400;
		y = y * 400;

		vertex(x, y);
	}
	endShape();

	// beginShape();
	//   for (int i=0; i<=n_points; i++){
	//     angle = map(i, 0,n_points, 0,TWO_PI);

	//     x = sin(angle*freqX + radians(phiX));
	//     y = sin(angle*freqY);

	//     x = x * 500;
	//     y = y * 500;

	//     vertex(x, y);
	//   }
	// endShape();

	phiX = (phiX + 0.5f) % 360;
	// println(phiX);

	freqX += 0.00005f;
	freqY += 0.0000025f;
	freqX = freqX % 10;
	freqY = freqY % 50;
	if (frameCount % 6 == 0){
		freqX = random(3);
		freqY = random(3);
	}

	// popMatrix();
	frame.setTitle(" " + frameRate);
	fill(255, 255);
	text(frameRate, width*0.45f, height*0.45f);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "LissajousShapes_001" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
