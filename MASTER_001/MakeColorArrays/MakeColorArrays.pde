PImage img;
color[] colors;
PrintWriter file;

void setup() {
	img = loadImage("img_003.jpg");
	frameRate(1);
	file = createWriter("colors.csv");
}

void draw(){
	writeColors();
	// noLoop();
	exit();

}

void writeColors() {
	int i = 0;
	colors = new color[img.width * img.height];
	for (int y = 0; y < img.height; y++){
		for (int x = 0; x < img.width; x++){
			colors[i] = img.get(x, y);
			file.println(red(colors[i]) + "," + green(colors[i]) + "," + blue(colors[i]));
			i++;
		}
	}

	// i = 0;
	// for (int y = 0; y < img.height/4; y++){
	// 	for (int x = 0; x < img.width/4; x++){
	// 		fill(colors[i]);
	// 		rect(x*4, y*4, x+20, y+20);
	// 		println("red = " + red(colors[i]) + "\tgreen = " + green(colors[i]) + "\t~blue " + blue(colors[i]));
	// 		i++;
	// 	}
	// }
	file.flush();
	file.close();
}