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

public class NoiseAgent001 extends PApplet {

NoiseAgent[] agents = new NoiseAgent[4000];
float noiseScale = 300, noiseStrength = 10;
boolean offscreen = false;

public void setup() {
	size(displayWidth, displayHeight);
	background(0xff1E9D6F);
	stroke(0xffC63800, 40);

	for (int i = 0; i < agents.length; i++) {
		agents[i] = new NoiseAgent();
	}
}

public void draw() {
	fill(0xff1E9D6F, 10);
	rect(0, 0, width, height);
	for (int i = 0; i < agents.length; i++) {
		agents[i].move();
	}
}

class NoiseAgent {
	PVector p, pOld;
	float angle;

	NoiseAgent() {
		p = new PVector(random(width), random(height));
		pOld = new PVector(p.x, p.y);
	}

	public void move() {
		angle = noise(p.x/noiseScale, p.y/noiseScale) * noiseStrength;

		p.x += cos(angle) * 5;
		p.y += sin(angle) * 5;
		
		// p.x += (cos(angle) * 50) % 10;
		// p.y += (sin(angle) * 50) % 10;
		
		// p.x += tan(angle) * 5;
		// p.y += cos(angle) * 5;

	if (p.x < -10) offscreen = true;
	else if (p.x > width + 10) offscreen = true;
	else if (p.y < -10) offscreen = true;
	else if (p.y > height + 10) offscreen = true;

	if (offscreen) {
		p.x = random(width);
		p.y = random(height);
		pOld.set(p);
	}

	strokeWeight(1);
	line(pOld.x, pOld.y, p.x, p.y);

	pOld.set(p);

	offscreen = false;

	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "NoiseAgent001" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
