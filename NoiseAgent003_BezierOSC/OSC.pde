void oscSetup() {	

	// start oscP5 at localhost, port 10001
	oscP5 = new OscP5(this, 10001);
	// address for OSC messages to be sent to
	myRemoteLocation = new NetAddress("127.0.0.1", 10002);

}

void oscSendParametersToMax(){

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
void oscEvent(OscMessage theOscMessage) {

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
		int newSeed = int(random(1000));
		noiseSeed(newSeed);
		stroke(int(random(255)), 0, 0, 2);
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