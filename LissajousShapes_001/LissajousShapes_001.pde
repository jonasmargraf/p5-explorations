// Simple exploration of Lissajous shapes made from simple sin/cos functions

// Jonas Margraf
// jmargraf@berklee.edu

int n_points = 500;
float x, y, freqX, freqY, angle, phiX, phiY;

void setup() {
	// size(displayWidth, displayHeight, "processing.core.PGraphicsRetina2D");
	size(displayWidth, displayHeight);
	background(0);
	
	freqX = 1;
	freqY = 2;
	phiX = 300;
	// phiY = 0;

}

void draw() {
	// background(0);
	fill(0, 255);
	noStroke();
	rect(0, 0, width, height);
	noFill();
	stroke(255);
	// println(freqX + "\t" + freqY);

	// pushMatrix();
	translate(width/2, height/2);

	beginShape();
	for (int i = 0; i <= n_points; i++){
		angle = map(i, 0, n_points, 0, TWO_PI);
		x = sin(angle * freqX + radians(phiX));
		y = sin(angle * freqY);

		x = x * 300;
		y = y * 300;

		vertex(x, y);
	}
	endShape();

	// beginShape();
	//   for (int i=0; i<=n_points; i++){
	//     angle = map(i, 0,n_points, 0,TWO_PI);

	//     x = sin(angle*freqX + radians(phiX));
	//     y = sin(angle*freqY);

	//     x = x * 500;
	//     y = y * 500;

	//     vertex(x, y);
	//   }
	// endShape();

	phiX = (phiX + 0.5) % 360;
	// println(phiX);

	freqX += 0.001 + random(0.01);
	freqY += 0.0005 + random(0.01);
	// if (frameCount % 10 == 0){
	// 	freqX += random(-0.2, 0.2);
	// 	freqY += random(-0.2, 0.2);
	// }

	// popMatrix();
	frame.setTitle(" " + frameRate);
	fill(255, 255);
	text(frameRate, width*0.45, height*0.45);
}