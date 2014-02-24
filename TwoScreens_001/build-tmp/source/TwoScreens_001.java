import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import mpe.client.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class TwoScreens_001 extends PApplet {

// First test using Daniel Shiffman's Most Pixels Ever framework
// see this tutorial for more info:
// github.com/shiffman/Most-Pixels-Ever-Processing/wiki/Processing-Tutorial

// Jonas Margraf
// jmargraf@berklee.edu

// remember to start the server from the command line first!
// cd to directory with server .jar file, then:
// java -jar mpeServer-2.0.2.jar -screens2



TCPClient client;

float x, y, angle;

boolean forward = true;

public void setup() {
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

public void draw() {
}

public void frameEvent(TCPClient c) {
	background(255);
	fill(255, 0, 0, 150);
	ellipse(x, y, 150, 150);

	// x = (x + 10 + random(2)) % (client.getMWidth() + 50);
	// y = (y + 10 + random(2)) % (client.getMHeight() - 50);
	if (x < client.getMWidth() && forward){
		x+= 12;
	}

	if (x >= client.getMWidth()){
		forward = false;
	}

	if (x >= 0 && !forward){
		x-= 12;
	}

	if (x <= 0){
		forward = true;
	}

	angle = map(x, 0, client.getMHeight(), 0, TWO_PI);
	y = (client.getMHeight()/2) - (sin(angle * 0.5f) * 100);
	// x = (x + 10 + random(2)) % (client.getMWidth() + 50);
	// y = (y + 10 + random(2)) % (client.getMHeight() - 50);
	// x += 5;

	text(frameRate, client.getLWidth() - 100, client.getLHeight() - 10);

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "TwoScreens_001" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
