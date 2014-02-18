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

public class NoiseAgent002_bezier extends PApplet {

// creates a swarm of NoiseAgents, drawn as bezier curves, whose control points
// relate to mouse position

// Jonas Margraf
// jmargraf@berklee.edu

// SPACE - pause / resume drawing
// left-click - clears screen
// right-click - new seed for Perlin noise function & new shade of red

// intialize array and variables
NoiseAgent[] agents = new NoiseAgent[100];
float noiseScale = 300, noiseStrength = 10;
boolean offscreen = false;
int pause = 0;

// setup as fullscreen, using OpenGL renderer
public void setup() {
	size(displayWidth, displayHeight, OPENGL);
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
	// background(0, 1);
	for (int i = 0; i < agents.length; i++) {
		agents[i].move();
	}
}

// mouse functions
public void mouseReleased() {
	// left-click to clear screen
	if (mouseButton == LEFT) {
		fill(255, 255);
		rect(0, 0, width, height);
		noFill();
	}
	
	// right-click to seed Perlin noise generator
	// also creates new shade of red (or "black", in case of low values)
	if (mouseButton == RIGHT) {
	int newSeed = PApplet.parseInt(random(1000));
	noiseSeed(newSeed);
	stroke(PApplet.parseInt(random(255)), 0, 0, 2);
	}	
}

// spacebar pauses / resumes drawing
public void keyReleased() {
	if (key == ' ') {
		if (pause % 2 == 0) {
			noLoop();
		}
		if (pause % 2 == 1) {
			loop();
		}
		pause++;
	}
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

	bezier(pOld.x, pOld.y, mouseX, mouseY, width-pmouseX, height-pmouseY, p.x, p.y);

	pOld.set(p);

	offscreen = false;

	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "NoiseAgent002_bezier" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
