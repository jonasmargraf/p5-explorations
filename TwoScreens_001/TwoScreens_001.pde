// First test using Daniel Shiffman's Most Pixels Ever framework
// see this tutorial for more info:
// github.com/shiffman/Most-Pixels-Ever-Processing/wiki/Processing-Tutorial

// Jonas Margraf
// jmargraf@berklee.edu

// remember to start the server from the command line first!
// cd to directory with server .jar file, then:
// java -jar mpeServer-2.0.2.jar -screens2

import mpe.client.*;

TCPClient client;

float x, y, angle;

boolean forward = true;

void setup() {
	client = new TCPClient(this, "mpe_config.xml");
	size(client.getLWidth(), client.getLHeight());
	resetEvent(client);
	client.start();
	randomSeed(1000);
	noCursor();
}

public void resetEvent(TCPClient c) {
	noStroke();
	x = -50;
	y = height/2;
}

void draw() {
}

void frameEvent(TCPClient c) {
	background(255);
	fill(255, 0, 0, 150);
	ellipse(x, y, 150, 150);

	// x = (x + 10 + random(2)) % (client.getMWidth() + 50);
	// y = (y + 10 + random(2)) % (client.getMHeight() - 50);
	if (x < client.getMWidth() && forward){
		x+= 20;
	}

	if (x >= client.getMWidth()){
		forward = false;
	}

	if (x >= 0 && !forward){
		x-= 20;
	}

	if (x <= 0){
		forward = true;
	}

	angle = map(x, 0, client.getMHeight(), 0, TWO_PI);
	y = (client.getMHeight()/2) - (sin(angle * 0.5) * 100);
	// x = (x + 10 + random(2)) % (client.getMWidth() + 50);
	// y = (y + 10 + random(2)) % (client.getMHeight() - 50);
	// x += 5;

	text(frameRate, client.getLWidth() - 100, client.getLHeight() - 10);

}