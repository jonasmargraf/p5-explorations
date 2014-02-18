// a class encapsulates the agents that are drawn to the screen
class NoiseAgent {

	PVector p, pOld;
	float angle;

	NoiseAgent() {

		p = new PVector(random(width), random(height));
		pOld = new PVector(p.x, p.y);

	}

	void move() {

		angle = noise(p.x/noiseScale, p.y/noiseScale) * noiseStrength;

		p.x += cos(angle) * 10;
		p.y += sin(angle) * 10;

	if (p.x < -10) offscreen = true;
	else if (p.x > width + 10) offscreen = true;
	else if (p.y < -10) offscreen = true;
	else if (p.y > height + 10) offscreen = true;

	if (offscreen) {
		p.x = random(width);
		p.y = random(height);
		pOld.set(p);
	}

	bezier(pOld.x, pOld.y, control_point1_x * width, control_point1_y * height, 
			control_point2_x * width, control_point2_y * height, p.x, p.y);

	pOld.set(p);

	offscreen = false;

	}

}