import oscP5.*;
import netP5.*;

OscP5 oscP5;
NetAddress myRemoteLocation;

int i, x, y;
int n_circles = 1;
float float01;

void setup(){
  size(1800, 900);
  background(255);
  oscP5 = new OscP5(this, 10001);
  myRemoteLocation = new NetAddress("127.0.0.1", 10001);
}

void oscEvent(OscMessage theOscMessage){
  if(theOscMessage.checkAddrPattern("float01")==true){
    float01 = theOscMessage.get(0).floatValue();
    print(theOscMessage.addrPattern() + " ");
    println(float01);
  }
}

void draw(){
  if (i == 100){
    background(255, 255, 255);
    i = 0;
  }
  noStroke();
  fill(0, 0, 0, 150);
//  ellipseMode(CENTER);
  x = width / (n_circles % 10);
  if (n_circles % 10 == 0 && n_circles != 40){
    y += height / 4;
  }
  else{
    y = 0;
    n_circles = 1;
  }
  ellipse(x, y, int(float01*2000), int(float01*2000));
  i++;
  n_circles++;
}
