NoiseAgent[] agents = new NoiseAgent[4000];
float noiseScale = 300, noiseStrength = 10;
boolean offscreen = false;

void setup() {
	size(displayWidth, displayHeight);
	background(#1E9D6F);
	stroke(#C63800, 40);

	for (int i = 0; i < agents.length; i++) {
		agents[i] = new NoiseAgent();
	}
}

void draw() {
	fill(#1E9D6F, 10);
	rect(0, 0, width, height);
	for (int i = 0; i < agents.length; i++) {
		agents[i].move();
	}
}

class NoiseAgent {
	PVector p, pOld;
	float angle;

	NoiseAgent() {
		p = new PVector(random(width), random(height));
		pOld = new PVector(p.x, p.y);
	}

	void move() {
		angle = noise(p.x/noiseScale, p.y/noiseScale) * noiseStrength;

		p.x += cos(angle) * 5;
		p.y += sin(angle) * 5;
		
		// p.x += (cos(angle) * 50) % 10;
		// p.y += (sin(angle) * 50) % 10;
		
		// p.x += tan(angle) * 5;
		// p.y += cos(angle) * 5;

	if (p.x < -10) offscreen = true;
	else if (p.x > width + 10) offscreen = true;
	else if (p.y < -10) offscreen = true;
	else if (p.y > height + 10) offscreen = true;

	if (offscreen) {
		p.x = random(width);
		p.y = random(height);
		pOld.set(p);
	}

	strokeWeight(1);
	line(pOld.x, pOld.y, p.x, p.y);

	pOld.set(p);

	offscreen = false;

	}
}