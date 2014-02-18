void setup(){
  size(displayWidth, displayHeight);
  noFill();
  background(0);
}

color strokeColor = color(255, 10);

void draw(){
  if(mousePressed){
    translate(width/2, height/2);
    
    int circleResolution = (int)map(mouseY+100, 0, height, 2, 10);
    float radius = mouseX-width/2 + 0.5;
    float angle = TWO_PI/circleResolution;
    
    strokeWeight(2);
    stroke(strokeColor);
    
    beginShape();
    for (int i=0; i<=circleResolution; i++){
      float x = 0 + cos(angle*i) * radius;
      float y = 0 + sin(angle*i) * radius;
      vertex(x, y);
    }
    endShape();
  }
}

void keyReleased(){
  if (key == DELETE || key == BACKSPACE) background(0);
  
  switch(key){
    case '1':
      strokeColor = color(#11C5F0, 10);
      break;
    case '2':
      strokeColor = color(#F0116E, 10);
      break;
    case '3':
      strokeColor = color(#F0A611, 10);
      break;
    case '4':
      strokeColor = color(255, 10);
      break;
  }
}

