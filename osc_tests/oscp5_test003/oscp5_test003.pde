import oscP5.*;
import netP5.*;

OscP5 oscP5;
NetAddress myRemoteLocation;

int i, x, y;
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
  if (i == 20){
    background(255, 255, 255);
    i = 0;
  }
  noStroke();
  fill(0, 0, 0, 150);
  ellipseMode(CENTER);
  ellipse(width/2, height/2, int(float01*2000), int(float01*2000));
  i++;
}
