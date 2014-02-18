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
// 	java -jar mpeServer-2.0.2.jar -verbose -framerate30 -screens2



TCPClient client;

float x, y;

public void setup() {
	client = new TCPClient(this, "mpe_config.xml");
	size(client.getLWidth(), client.getLHeight());
	resetEvent(client);
	client.start();
}

public void resetEvent(TCPClient c) {
	noStroke();
	x = -50;
	y = height/2;
}

public void draw() {
	// ellipse(x, y, 100, 100);
}

public void frameEvent(TCPClient c) {
	background(255);
	// background(int(random(255)), 0, 0);
	fill(255, 0, 0, 150);
	ellipse(x, y, 100, 100);

	x = (x + 10) % (client.getMWidth() + 50);
	// x += 5;
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
