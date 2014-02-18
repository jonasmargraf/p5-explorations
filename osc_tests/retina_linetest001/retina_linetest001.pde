void setup(){
  size(1400, 700, "processing.core.PGraphicsRetina2D");
  background(255);
}

void draw(){
  if (mousePressed == true){
    stroke(0, 0, 0, 60);
    noFill();
    bezier(mouseX, mouseY*cos(mouseY), pmouseX*sin(pmouseX), pmouseY,
         width-mouseX, height-mouseY*cos(mouseY), width-pmouseX*sin(pmouseX), height-pmouseY);
  }
}
