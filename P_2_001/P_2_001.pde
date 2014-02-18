int x_offset, x_offset_prev;
int y_offset = height;

void setup(){
  size(displayWidth, displayHeight);
  background(255);
}

void draw(){
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
  float radius = mouseX*0.1 + 0.5;
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
      fill(#00437C, 5);
      vertex(x, y);
      if(frameCount % 20 == 0){
        noFill();
        stroke(0, 60, 155, 170);
//        line(0, 0, x, y);
        vertex(x,y);
      }
      if(frameCount % 100 == 0){
        noFill();
        stroke(#FFFFFF, 255);
//        stroke(255, 255, 0, 170);
        line(0, 0, x*1.5, y*1.5);
      }
    }
    endShape();
    
//    x_offset_prev = x_offset + int(random(-10, 10));
    x_offset += random(-50, 50);
    y_offset -= 5;
}
