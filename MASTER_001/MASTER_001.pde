// Jonas Margraf
// jmargraf@berklee.edu

// remember to start the server from the command line first!
// cd to directory with server .jar file, then:
// java -jar mpeServer-2.0.2.jar -screens2

import mpe.client.*;
import oscP5.*;
import netP5.*;
import processing.opengl.*;

TCPClient client;

OscP5 oscP5;
NetAddress myRemoteLocation;

int points = 600;
int ms, scene;
boolean bump;
float r, g, b;
float zoom = -100;

color[] colors = new color[points];

Lissajous2D lissajous2D = new Lissajous2D(15.23, 2.02);
Lissajous3D lissajous3D = new Lissajous3D(15.23, 2.02, 1.5);

void setup(){

	client = new TCPClient(this, "mpe_config.xml");
	
	size(displayWidth, displayHeight, OPENGL);
	
	resetEvent(client);
	client.start();

	// setup OSC networking (see OSC.pde)
	oscSetup();
	// send those Processing parameters to Max that should be controllable
	// from within Ableton / MaxForLive
	oscSendParametersToMax();

	lissajous3D.calculatePoints();

	calculateColors();
}

public void resetEvent(TCPClient c){}

void draw(){}

void frameEvent(TCPClient c){

	ms = millis();
	if (bump){
		zoom+= 55;
	}
	else {
		zoom-= 20;
	}

	zoom = constrain(zoom, -1200, 100);

	switch(scene){
		case 0:
			lissajous2D.display();
			break;
		case 1:
			lissajous3D.display();
			break;
	}

	// display FPS in window title bar
	fill(255, 10);
	frame.setTitle(" " + frameRate);
}

void calculateColors(){
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
	}
}