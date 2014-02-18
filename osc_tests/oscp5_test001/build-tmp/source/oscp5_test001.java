import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import oscP5.*; 
import netP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class oscp5_test001 extends PApplet {




OscP5 oscP5;
NetAddress myRemoteLocation;

int firstValue;
int secondValue = 10;
float thirdValue;

public void setup() {
  frameRate(15);
  // size(1200,600, "processing.core.PGraphicsRetina2D");
  size(1200,600);
//  hint(ENABLE_RETINA_PIXELS);
  background(255);
  noStroke();
  oscP5 = new OscP5(this, 10001);
  myRemoteLocation = new NetAddress("127.0.0.1", 10001);
}

public void mousePressed() {
  OscMessage myMessage = new OscMessage("/float01");
  myMessage.add(123);
  oscP5.send(myMessage, myRemoteLocation);
}

public void oscEvent(OscMessage theOscMessage) {
  if(theOscMessage.checkAddrPattern("int01")==true) {
    firstValue = theOscMessage.get(0).intValue();
    print(theOscMessage.addrPattern() + " ");
    println(firstValue);
  }
  else if(theOscMessage.checkAddrPattern("int02")==true) {
    secondValue = theOscMessage.get(0).intValue();
    print(theOscMessage.addrPattern() + " ");
    println(secondValue);
  }
  else if(theOscMessage.checkAddrPattern("float01")==true) {
    thirdValue = theOscMessage.get(0).floatValue();
    print(theOscMessage.addrPattern() + " ");
    println(thirdValue);
  }
}

public void draw() {
  int x = PApplet.parseInt(random(width));
  int y = PApplet.parseInt(random(height));
  ellipseMode(CENTER);
  noFill();
  stroke(0, 0, 0, firstValue);
//  fill(random(255), 20, 20, firstValue*0.5);
  ellipse(x, y, secondValue, secondValue);
  noStroke();
  fill(40, 0, 120, firstValue);
  ellipse(x, y, secondValue/2, secondValue/2);
  fill(0, 30, 150, PApplet.parseInt(thirdValue*50));
  ellipse(x, y, PApplet.parseInt(thirdValue*100), PApplet.parseInt(thirdValue*100));
}

public void keyReleased() {
  if (key == BACKSPACE) background(255);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "oscp5_test001" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
