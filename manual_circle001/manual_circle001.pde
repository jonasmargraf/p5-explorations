// creating a circular shape manually using sin() and cos()

// Jonas Margraf
// jmargraf@berklee.edu

int circlePoints = 8;
float stepSize = 100;
float angle = TWO_PI/circlePoints;
float x = 0;
float y = 0;
float r = 0;
int seed = (int) random(1000);
float x_pos = 0;
float y_pos = 0;

void setup(){
	size (displayWidth, displayHeight);
	background(255);
	ellipseMode(CENTER);
	noiseSeed(seed);
}

void draw(){
	background(255);
	noStroke();
	fill(0);
	pushMatrix();
	translate(width/2, height/2);
	rotate(r);

	x_pos = lerp(x_pos, mouseX, 0.008);
	y_pos = lerp(y_pos, mouseY, 0.02);
	stepSize = (lerp(x_pos, mouseX, 0.2)/width)*(height/2);

	for (int i = 0; i < circlePoints; i++){
		x = cos(angle*i) * stepSize;
		y = sin(angle*i) * stepSize;
		fill(0, noise(0, i)*255, noise(0, i)*255, 170);
		ellipse(x, y, y_pos/(circlePoints/2), y_pos/(circlePoints/2));
		// ellipse(x, y, y_pos/10, y_pos/10);
	}
	
	if (r >= TWO_PI) {
		r = 0;
	}

	r+= 0.01;

	popMatrix();
}
