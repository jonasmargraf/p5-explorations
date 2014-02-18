// creates a swarm of NoiseAgents, drawn as bezier curves, whose control points
// relate to mouse position

// Jonas Margraf
// jmargraf@berklee.edu

// import libraries
import oscP5.*;
import netP5.*;
import processing.opengl.*;

// intialize osc, agent array, objects and variables
OscP5 oscP5;
NetAddress myRemoteLocation;

NoiseAgent[] agents = new NoiseAgent[100];
float noiseScale = 300, noiseStrength = 10;
float control_point1_x, control_point1_y;
float control_point2_x, control_point2_y;
boolean offscreen = false, clear_screen = false;

int pause = 0;

void setup() {

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
	stroke(#FF2424, 2);

	// iterate over array to create the agents
	for (int i = 0; i < agents.length; i++) {
		agents[i] = new NoiseAgent();
	}

}

// iterate over array during each frame to draw agents' movement
void draw() {

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