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

public class P_2_002 extends PApplet {

public void setup(){
  size(displayWidth, displayHeight);
  noFill();
  background(0);
}

int strokeColor = color(255, 10);

public void draw(){
  if(mousePressed){
    translate(width/2, height/2);
    
    int circleResolution = (int)map(mouseY+100, 0, height, 2, 10);
    float radius = mouseX-width/2 + 0.5f;
    float angle = TWO_PI/circleResolution;
    
    strokeWeight(2);
    stroke(strokeColor);
    
    beginShape();
    for (int i=0; i<=circleResolution; i++){
      float x = 0 + cos(angle*i) * radius;
      float y = 0 + sin(angle*i) * radius;
      vertex(x, y);
    }
    endShape();
  }
}

public void keyReleased(){
  if (key == DELETE || key == BACKSPACE) background(0);
  
  switch(key){
    case '1':
      strokeColor = color(0xff11C5F0, 10);
      break;
    case '2':
      strokeColor = color(0xffF0116E, 10);
      break;
    case '3':
      strokeColor = color(0xffF0A611, 10);
      break;
    case '4':
      strokeColor = color(255, 10);
      break;
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "P_2_002" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
