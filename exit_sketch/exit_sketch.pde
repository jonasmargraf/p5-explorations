void setup(){
  size(displayWidth, displayHeight);
  background(0, 255, 240);
}

void draw(){
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
void keyPressed(){
  exit();
}
