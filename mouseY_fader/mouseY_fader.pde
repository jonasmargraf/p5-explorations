float fader;

void setup(){
  size(200, 800);
}

void draw(){
  frameRate(15);
  fader = mouseY/float(height);
  println(fader);
}
