import processing.core.*;

public class sketch extends PApplet {
	public Features f;
	public final int width = 490;
	public final int height = 790;
	public PImage bird;
	public PImage birdJumping;
	public PImage birdFalling;
	public PImage birdDive;
	public PImage backgroundPic;
	public PFont font;
	public String[] scores;
	
	
	public void settings() {
		size(width, height);
	}

	public void setup() {
		bird = loadImage("BirdStationary.png");
		birdJumping = loadImage("BirdJumping.png");
		birdFalling = loadImage("BirdFalling.png");
		birdDive = loadImage("BirdDive.png");
		backgroundPic = loadImage("Background.png");
		font = createFont("04B_19__.TTF", 64);
		scores = loadStrings("hiScore.txt");
		
		f = new Features(this, width, height, bird, birdJumping, birdFalling, birdDive, backgroundPic, font, scores);
		f.setMovementFactors();
		f.setPipes();
		f.scores = new String[1];
	}

	public void draw() {
		f.drawBackground();
		f.drawPipes();
		f.movePipes();
		f.drawGround();
		f.movement();
		f.textAnimation();
		f.drawBird();
		f.hitDetection();
		f.score();
		f.hiScore();
		f.displayScore();
		f.menuScreen();
		f.deathEffect();
		f.savehiScore();
	}
	
	public void keyPressed(){
		f.keyPressed();
	}

	public static void main(String[] args) {
		PApplet.main("sketch");
	}
}
