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

public class OOP_classes_001 extends PApplet {

// A first exploration of object-oriented programming & classes in Processing.
// Drawing a grid of circles with random sizes and colors.

// Jonas Margraf
// jmargraf@berklee.edu
// February 2, 2014

// two-dimensional array of object Circle
Circle[][] circles;
int columns = 10;
int rows = 10;
// intialize circles with random diameter
float diameter = random(height/rows);

public void setup() {
  size(displayWidth, displayHeight);
  frameRate(15);
  // create array of new Circle objects
  circles = new Circle[columns][rows];  
}

public void draw() {
  // only redraw background every 60th frame
  if (frameCount == 1 || frameCount % 60 == 0){
    background(255, 162, 196);
  }
  // move coordinate system so that circles are always drawn centered
  translate((width/columns)/2, (height/rows)/2);
  // iterate over the array using a nested for-loop
  // going row by row
  for (int i = 0; i < columns; i++){
    for (int j = 0; j < rows; j++){
      diameter += random(-10, 10);
      if (abs(diameter) >= (height/rows)){
        diameter = 1;
      }
      // passing arguments to the circle object
      circles[i][j] = new Circle(color(random(255), random(255), random(255), 40/*0, 185, 255, 40*/),
                                 color(0, 91, 126, 160),
                                 j*(width/circles.length), i*(height/circles.length), diameter);
    }
  }
  // finally, drawing the circles by calling the display() function of the Circle class
  for (int i = 0; i < columns; i++){
    for (int j = 0; j < rows; j++){
      circles[i][j].display();
    }
  }
}
// the Circle class
class Circle {
  int c_body;
  int c_outline;
  float xpos;
  float ypos;
  float diameter;
  
  // the constructor
  Circle(int temp_c_body, int temp_c_outline,
         float temp_xpos, float temp_ypos, float temp_diameter) {
           c_body = temp_c_body;
           c_outline = temp_c_outline;
           xpos = temp_xpos;
           ypos = temp_ypos;
           diameter = temp_diameter;
         }
         
         public void display(){
//           translate(25, 25);
//           ellipseMode(CORNER);
           noStroke();
           fill(255, 100);
           ellipse(xpos, ypos, diameter, diameter);
           stroke(c_outline);
           fill(c_body);
           ellipse(xpos, ypos, diameter, diameter);
         }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "OOP_classes_001" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
