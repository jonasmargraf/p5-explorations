// the Circle class
class Circle {
  color c_body;
  color c_outline;
  float xpos;
  float ypos;
  float diameter;
  
  // the constructor
  Circle(color temp_c_body, color temp_c_outline,
         float temp_xpos, float temp_ypos, float temp_diameter) {
           c_body = temp_c_body;
           c_outline = temp_c_outline;
           xpos = temp_xpos;
           ypos = temp_ypos;
           diameter = temp_diameter;
         }
         
         void display(){
//           translate(25, 25);
//           ellipseMode(CORNER);
           noStroke();
           fill(255, 100);
           ellipse(xpos, ypos, diameter, diameter);
           stroke(c_outline);
           fill(c_body);
           ellipse(xpos, ypos, diameter, diameter);
         }
}
