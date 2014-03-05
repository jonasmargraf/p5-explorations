class Lissajous3D {
	int pointCount = 1000;
	float x, y, z;
	float freqX, freqY, freqZ;
	float phiX, phiY;
	float angle;
	PVector lissajous3DPoints[];

	Lissajous3D(float fx, float fy, float fz){
		freqX = fx;
		freqY = fy;
		freqZ = fz;
	}

	void calculatePoints(){
		lissajous3DPoints = new PVector[pointCount+1];
		// f = scaling factor?
		float f = client.getLWidth()/2;

		for (int i = 0; i <= pointCount; i++){
			angle = map(i, 0, pointCount, 0, TWO_PI);
			x = sin(angle * freqX + radians(phiX)) * sin(angle * 2) * f*4;
			y = sin(angle * freqY + radians(phiY)) * f;
			z = sin(angle * freqZ) * f;
			lissajous3DPoints[i] = new PVector(x, y, z);
		}
	}

	void display(){

		noiseSeed(1);
		randomSeed(2);

		background(0);
		lights();

		if (ms % 500 <= 20){
			freqX = random(20);
			freqY = random(5);
			freqZ = random(10);
		}

		phiX = (phiX + 0.25) % 360;
		phiY = (phiY + 0.35) % 360;

		lissajous3D.calculatePoints();

		// move midpoint to first display
		// translate(client.getMWidth()*0.25, client.getMHeight()*0.5, zoom);
		// move midpoint to second display
		// translate(client.getMWidth()*0.75, client.getMHeight()*0.5, zoom);
		// move midpoint to center of both displays
		translate(client.getMWidth()*0.5, client.getMHeight()*0.5, zoom);


		noStroke();
		beginShape(TRIANGLE_FAN);
		int j = imgColors.length;
		for (int i = 0; i < pointCount-2; i++){
			if (i % 3 == 0) {
				j-= j - i;
				fill(imgColors[i], 10);
				vertex(0,0,0);
				fill(imgColors[j], 220);
				vertex(lissajous3DPoints[i].x, lissajous3DPoints[i].y,
						lissajous3DPoints[i].z);
				vertex(lissajous3DPoints[i+2].x, lissajous3DPoints[i+2].y,
						lissajous3DPoints[i+2].z);
			}
		}
		endShape();

		stroke(255, 155);
		noFill();
		beginShape();
		for (int i = 0; i < pointCount; i++){
			vertex(lissajous3DPoints[i].x, lissajous3DPoints[i].y,
					lissajous3DPoints[i].z);
		}
		endShape();
	}
}

class Lissajous2D {
	int pointCount = 600;
	float x, y;
	float freqX, freqY;
	float phiX, phiY;
	float angle;

	Lissajous2D(float fx, float fy){
		freqX = fx;
		freqY = fy;
	}

	void display(){

		noiseSeed(1);
		randomSeed(2);

		noLights();
		fill(0, 100);
		noStroke();
		rect(0, 0, width, height);
		noFill();
		stroke(255, 200);
		// move midpoint to first display
		translate(client.getMWidth()*0.25, client.getMHeight()*0.5);
		// move midpoint to second display
		// translate(client.getMWidth()*0.75, client.getLHeight()*0.5, zoom);

		beginShape();
		for (int i = 0; i <= pointCount; i++){
			angle = map(i, 0, pointCount, 0, TWO_PI);
			x = sin(angle * freqX + radians(phiX));
			y = sin(angle * freqY);

			// x = x * width*0.45;
			// y = y * 200;

			x = x * 400;
			y = y * 400;

			vertex(x, y);
		}
		endShape();

		phiX = (phiX + 0.5) % 360;

		freqX += 0.00005;
		freqY += 0.0000025;
		freqX = freqX % 10;
		freqY = freqY % 50;
		if (frameCount % 6 == 0){
			freqX = random(3);
			freqY = random(3);
		}
	}
}