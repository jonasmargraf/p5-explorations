// First test using Daniel Shiffman's Most Pixels Ever framework
// see this tutorial for more info:
// github.com/shiffman/Most-Pixels-Ever-Processing/wiki/Processing-Tutorial

// Jonas Margraf
// jmargraf@berklee.edu

// remember to start the server from the command line first!
// 	java -jar mpeServer-2.0.2.jar -screens2

import mpe.client.*;

TCPClient client;

float x, y;

boolean forward = true;

void setup() {
	client = new TCPClient(this, "mpe_config.xml");
	size(client.getLWidth(), client.getLHeight());
	resetEvent(client);
	client.start();
	randomSeed(1000);
}

public void resetEvent(TCPClient c) {
	noStroke();
	x = -50;
	y = height/2;
}

void draw() {
	// ellipse(x, y, 100, 100);
}

void frameEvent(TCPClient c) {
	background(255);
	// background(int(random(255)), 0, 0);
	fill(255, 0, 0, 150);
	ellipse(x, y, 150, 150);

	// x = (x + 10 + random(2)) % (client.getMWidth() + 50);
	// y = (y + 10 + random(2)) % (client.getMHeight() - 50);
	if (x < client.getMWidth() && forward){
		x++;
	}

	if (x == client.getMWidth()){
		forward = false;
	}

	if (x >= 0 && !forward){
		x--;
	}

	if (x == 0){
		forward = true;
	}
	// x = (x + 10 + random(2)) % (client.getMWidth() + 50);
	// y = (y + 10 + random(2)) % (client.getMHeight() - 50);
	// x += 5;
}