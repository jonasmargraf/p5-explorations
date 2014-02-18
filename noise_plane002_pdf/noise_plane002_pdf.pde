// note: PDF export doesn't work in this one
// the console throws an array index out of bonds error

import processing.opengl.*;
import processing.pdf.*;

// number of "tiles" to draw
int tileCount = 50;
// scaling factor for height of the noise plane
int zScale = 350;
// initialize rotation variables
float rotationX = 0, rotationZ = 0;
// boolean variable which triggers PDF export
boolean recordPDF = false;

void setup(){
  // use OpenGL renderer
  size(displayWidth, displayHeight, OPENGL);
}

void draw(){
  if (recordPDF){
    beginRaw(PDF, "01.pdf");
  }
  // change color mode to HueSaturationBrightness
  colorMode(HSB, 360, 100, 100);
  // set white background
  background(0, 0, 30);
  // turn on lighting
  lights();
  
  // draw vertex outlines with opacity
  stroke(360, 100, 100, 40);
  // set random seed for predictability
  randomSeed(0);
 
 // move coordinate system's point of origin to the center
 pushMatrix();
 translate(width*0.5, height*0.5, -300);
 
 // rotate when left-clicking
 if (mousePressed && mouseButton == LEFT){
   rotationX = (mouseY/float(-height))*TWO_PI;
   rotationZ = (mouseX/float(-width))*TWO_PI;
 }
 // right-click resets rotation
 if (mousePressed && mouseButton == RIGHT){
   rotationX = 0;
   rotationZ = 0;
 }
 
 rotateX(rotationX);
 rotateZ(rotationZ);
 
  float tileSizeY = (float)height/tileCount;
  float noiseStepY = 0.2;
  
  for (int meshY = 0; meshY <= tileCount; meshY++){
    beginShape(QUAD_STRIP);
    for (int meshX = 0; meshX <= tileCount; meshX++){
      // translate "tile numbers" meshX & meshY to vertex coordinates
      float vertex_x = map(meshX, 0, tileCount, -height*0.5, height*0.5);
      float vertex_y = map(meshY, 0, tileCount, -height*0.5, height*0.5);
      // scale noiseX & noiseY into appropriate range for noise() function
      // this scaling determines the overall "look" / noisiness of the shape
      float noiseX = map(meshX, 0, tileCount, 0, 10);
      float noiseY = map(meshY, 0, tileCount, 0, 10);
      float z1 = noise(noiseX, noiseY);
      float z2 = noise(noiseX, noiseY + noiseStepY);
      // set fill color to white
      fill(0, 0, 100, 150);
      // draw shape in white as underlay to make colors stand out more against dark background
      vertex(vertex_x, vertex_y, z1*zScale);
      vertex(vertex_x, vertex_y + tileSizeY, z2*zScale);
     // set fill color to random shades of red
      fill(random(340, 360), random(60, 200), 100, random(40, 120));
      // draw shape in color
      vertex(vertex_x, vertex_y, z1*zScale);
      vertex(vertex_x, vertex_y + tileSizeY, z2*zScale);
   }
   endShape();
  }
  popMatrix();
  if (recordPDF){
    endRaw();
    recordPDF = false;
  }
}

void keyReleased(){
  if (key == 'p' || key == 'P'){
   recordPDF = true;
  }
}
