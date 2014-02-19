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

int n_points = 500;
float x, y, freqX, freqY, angle, phiX, phiY;

public void setup() {
	// size(displayWidth, displayHeight, "processing.core.PGraphicsRetina2D");
	size(displayWidth, displayHeight);
	background(0);
	
	freqX = 1;
	freqY = 2;
	phiX = 300;
	// phiY = 0;

}

public void draw() {
	// background(0);
	fill(0, 255);
	noStroke();
	rect(0, 0, width, height);
	noFill();
	stroke(255);
	// println(freqX + "\t" + freqY);

	// pushMatrix();
	translate(width/2, height/2);

	beginShape();
	for (int i = 0; i <= n_points; i++){
		angle = map(i, 0, n_points, 0, TWO_PI);
		x = sin(angle * freqX + radians(phiX));
		y = sin(angle * freqY);

		x = x * 300;
		y = y * 300;

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

	freqX += 0.001f + random(0.01f);
	freqY += 0.0005f + random(0.01f);
	// if (frameCount % 10 == 0){
	// 	freqX += random(-0.2, 0.2);
	// 	freqY += random(-0.2, 0.2);
	// }

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
