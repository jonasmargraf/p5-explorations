import oscP5.*;
import netP5.*;

OscP5 oscP5;
NetAddress myRemoteLocation;

int firstValue;
int secondValue = 10;
float thirdValue;

void setup() {
  frameRate(15);
  // size(1200,600, "processing.core.PGraphicsRetina2D");
  size(1200,600);
//  hint(ENABLE_RETINA_PIXELS);
  background(255);
  noStroke();
  oscP5 = new OscP5(this, 10001);
  myRemoteLocation = new NetAddress("127.0.0.1", 10001);
}

void mousePressed() {
  OscMessage myMessage = new OscMessage("/float01");
  myMessage.add(123);
  oscP5.send(myMessage, myRemoteLocation);
}

void oscEvent(OscMessage theOscMessage) {
  if(theOscMessage.checkAddrPattern("int01")==true) {
    firstValue = theOscMessage.get(0).intValue();
    print(theOscMessage.addrPattern() + " ");
    println(firstValue);
  }
  else if(theOscMessage.checkAddrPattern("int02")==true) {
    secondValue = theOscMessage.get(0).intValue();
    print(theOscMessage.addrPattern() + " ");
    println(secondValue);
  }
  else if(theOscMessage.checkAddrPattern("float01")==true) {
    thirdValue = theOscMessage.get(0).floatValue();
    print(theOscMessage.addrPattern() + " ");
    println(thirdValue);
  }
}

void draw() {
  int x = int(random(width));
  int y = int(random(height));
  ellipseMode(CENTER);
  noFill();
  stroke(0, 0, 0, firstValue);
//  fill(random(255), 20, 20, firstValue*0.5);
  ellipse(x, y, secondValue, secondValue);
  noStroke();
  fill(40, 0, 120, firstValue);
  ellipse(x, y, secondValue/2, secondValue/2);
  fill(0, 30, 150, int(thirdValue*50));
  ellipse(x, y, int(thirdValue*100), int(thirdValue*100));
}

void keyReleased() {
  if (key == BACKSPACE) background(255);
}
