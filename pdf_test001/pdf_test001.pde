// proof of concept for PDF export when using OpenGL renderer

import processing.opengl.*;
import processing.pdf.*;

boolean recordPDF = false;

void setup(){
  size (displayWidth, displayHeight, OPENGL);
}

void draw(){
  if (recordPDF){
    beginRaw(PDF, "01.pdf");
  }
  
  beginShape(TRIANGLE_STRIP);
  
  vertex(width*0.25, height*0.25, 100);
  vertex(width*0.75, height*0.25, 100);
  vertex(width*0.75, height*0.75, 100);
  vertex(width*0.25, height*0.75, 100);
  
  endShape();
  
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
