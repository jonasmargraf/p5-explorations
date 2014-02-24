void oscSetup() {	

	// start oscP5 at localhost, port 10001
	oscP5 = new OscP5(this, 7001);
	// oscP5 = new OscP5(this, 7003);
	// address for OSC messages to be sent to
	myRemoteLocation = new NetAddress("127.0.0.1", 7002);

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

	if (theOscMessage.checkAddrPattern("/zoom")){
		bump = boolean(theOscMessage.get(0).stringValue());
		println(bump);
	}

	// println(theOscMessage);
	// println(zoom);
}