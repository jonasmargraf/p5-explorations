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

public class P_2_001 extends PApplet {

int x_offset, x_offset_prev;
int y_offset = height;

public void setup(){
  size(displayWidth, displayHeight);
  background(255);
}

public void draw(){
  strokeCap(SQUARE);
  smooth();
  noFill();
  if (keyPressed == true && key == BACKSPACE){
//  if (y_offset == height || y_offset % height == 0){
//  if (frameCount == 1 || frameCount % 600 == 0){
    background(255);
  }
  translate((width/2 + x_offset) % width, height + y_offset % height);
  
  int circleResolution = (int) map(mouseY, 0, height, 2, 80);
  float radius = mouseX*0.1f + 0.5f;
  float angle = TWO_PI/circleResolution;
  
//  strokeWeight(mouseY/20);
//  strokeWeight(2);
  
  beginShape();
    for (int i = 0; i <= circleResolution; i++){
      float x = (cos(angle*i) * radius) + random(-20, 20);
      float y = sin(angle*i) * radius + random(-20, 20);
      stroke(0, 0, 0, 40);
//      line(0, 0, x, y);
      noStroke();
      fill(0xff00437C, 5);
      vertex(x, y);
      if(frameCount % 20 == 0){
        noFill();
        stroke(0, 60, 155, 170);
//        line(0, 0, x, y);
        vertex(x,y);
      }
      if(frameCount % 100 == 0){
        noFill();
        stroke(0xffFFFFFF, 255);
//        stroke(255, 255, 0, 170);
        line(0, 0, x*1.5f, y*1.5f);
      }
    }
    endShape();
    
//    x_offset_prev = x_offset + int(random(-10, 10));
    x_offset += random(-50, 50);
    y_offset -= 5;
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "P_2_001" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
