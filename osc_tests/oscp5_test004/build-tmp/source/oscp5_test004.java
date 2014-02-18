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

public class oscp5_test004 extends PApplet {




OscP5 oscP5;
NetAddress myRemoteLocation;

int i, x, y;
int n_circles = 1;
float float01;

public void setup(){
  size(1800, 900);
  background(255);
  oscP5 = new OscP5(this, 10001);
  myRemoteLocation = new NetAddress("127.0.0.1", 10001);
}

public void oscEvent(OscMessage theOscMessage){
  if(theOscMessage.checkAddrPattern("float01")==true){
    float01 = theOscMessage.get(0).floatValue();
    print(theOscMessage.addrPattern() + " ");
    println(float01);
  }
}

public void draw(){
  if (i == 100){
    background(255, 255, 255);
    i = 0;
  }
  noStroke();
  fill(0, 0, 0, 150);
//  ellipseMode(CENTER);
  x = width / (n_circles % 10);
  if (n_circles % 10 == 0 && n_circles != 40){
    y += height / 4;
  }
  else{
    y = 0;
    n_circles = 1;
  }
  ellipse(x, y, PApplet.parseInt(float01*2000), PApplet.parseInt(float01*2000));
  i++;
  n_circles++;
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "oscp5_test004" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
