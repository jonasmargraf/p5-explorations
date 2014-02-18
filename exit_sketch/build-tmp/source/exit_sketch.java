import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class exit_sketch extends PApplet {

public void setup(){
  size(displayWidth, displayHeight);
  background(0, 255, 240);
}

public void draw(){
  int x = mouseX;
  int y = mouseY;
  int px = pmouseX;
  int py = pmouseY;
//  line(x, y, px, py);
  if(mousePressed == true){
    stroke(255);
    line(x, y << 1, px, py >> 1);
    stroke(random(255), random(255), random(255), 160);
    line(x, y << 1, px, py >> 1);
  }
}

// exit when any key is pressed
public void keyPressed(){
  exit();
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "exit_sketch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
