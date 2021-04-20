import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

public class Features {

	public PVector topPipe1;
	public PVector botPipe1;
	public PVector topPipe2;
	public PVector botPipe2;
	public PVector startPos;
	public PVector startPos2;
	public PImage bird;
	public PImage birdJumping;
	public PImage birdFalling;
	public PImage birdDive;
	public PImage backgroundPic;
	public float location; // Location of shape
	public float velocity; // Velocity of shape
	public float gravity; // Gravity acts at the shape's acceleration
	public boolean jump;
	public boolean startGame;
	public boolean fallDown = false;
	public boolean gameOver = false;
	public PFont font;
	public int score = 0;
	public boolean increaseScore;
	public boolean increaseScore2;
	public boolean onOff = true;
	public boolean onOff2 = true;
	public int tintVal = 225;
	public PVector textLocation;
	public PVector textVelocity;
	public PVector textGrav;
	public int hiScore = 0;
	public String hiScores;
	public String[] scores;
	public boolean saveScore = false;
	public int w;
	public int h;
	PApplet parr;

	public Features(PApplet parent, int w, int h, PImage bird, PImage birdJumping, PImage birdFalling, PImage birdDive,
			PImage backgroundPic, PFont font, String[] scores) {

		textLocation = new PVector(w / 2 - 3, h / 2 - 3);
		textVelocity = new PVector((float) 0.2, (float) 0.2);
		textGrav = new PVector((float) 0.05, (float) 0.05);

		this.w = w;
		this.h = h;

		this.bird = bird;
		this.birdJumping = birdJumping;
		this.birdFalling = birdFalling;
		this.birdDive = birdDive;
		this.backgroundPic = backgroundPic;
		this.font = font;
		this.scores = scores;
		parr = parent;

		hiScore = Integer.parseInt(scores[0]);
	}

	void drawBird() {
		bird.resize(95, 55);
		birdJumping.resize(95, 58);
		birdDive.resize(95, 55);
		birdFalling.resize(95, 58);

		// Display image at location
		if (velocity < 3) {
			parr.imageMode(PConstants.CENTER);
			parr.image(birdJumping, 50, location);
		} else if (velocity >= 7 && velocity < 11) {
			parr.imageMode(PConstants.CENTER);
			parr.image(birdFalling, 50, location);
		} else if (velocity >= 3 && velocity < 7) {
			parr.imageMode(PConstants.CENTER);
			parr.image(bird, 50, location);
		} else {
			parr.imageMode(PConstants.CENTER);
			parr.image(birdDive, 50, location);
		}
	}

	public void setPipes() {
		startPos = new PVector(w + 295, parr.random(150, h - 240));
		startPos2 = new PVector(w + 295, parr.random(150, h - 240));

		////// First set
		botPipe1 = new PVector(startPos.x, startPos.y, 700); // x-location, y-location, height
		topPipe1 = new PVector((float) (startPos.x + 44.5), startPos.y); // x-location, y-location

		////// Second set
		botPipe2 = new PVector(startPos2.x + 295, startPos2.y, 700); // x-location, y-location, height
		topPipe2 = new PVector((float) (startPos2.x + 339.5), startPos2.y); // x-location, y-location
	}

	public void drawPipes() {
		parr.fill(119, 181, 54);
		parr.stroke(0);
		parr.strokeWeight(4);

		// Bottom Pipe 1
		parr.rectMode(PConstants.CORNER);
		parr.rect(botPipe1.x, botPipe1.y + 115, 90, botPipe1.z, 12); // bottom
		parr.rectMode(PConstants.CENTER);
		parr.rect(topPipe1.x, topPipe1.y + 115, 105, 50, 2, 2, 7, 7); // top

		// Top Pipe 1
		parr.rectMode(PConstants.CORNER);
		parr.rect(botPipe1.x, botPipe1.y - 115, 90, botPipe2.z * -1, 12); // bottom
		parr.rectMode(PConstants.CENTER);
		parr.rect(topPipe1.x, topPipe1.y - 115, 105, 50, 7, 7, 2, 2); // top

		///////////////////////// Second set of pipes

		// Bottom Pipe 2
		parr.rectMode(PConstants.CORNER);
		parr.fill(119, 181, 54);
		parr.rect(botPipe2.x, botPipe2.y + 115, 90, botPipe2.z, 12); // bottom
		parr.rectMode(PConstants.CENTER);
		parr.rect(topPipe2.x, topPipe2.y + 115, 105, 50, 2, 2, 7, 7); // top

		// Top Pipe 2
		parr.rectMode(PConstants.CORNER);
		parr.rect(botPipe2.x, botPipe2.y - 115, 90, botPipe2.z * -1, 12); // bottom
		parr.rectMode(PConstants.CENTER);
		parr.rect(topPipe2.x, topPipe2.y - 115, 105, 50, 7, 7, 2, 2); // top
	}

	public void movePipes() {
		if (startGame == true && gameOver == false) {
			botPipe1.x -= 3;
			topPipe1.x -= 3;
			botPipe2.x -= 3;
			topPipe2.x -= 3;
			startPos.y = parr.random(150, h - 240);
			startPos2.y = parr.random(150, h - 240);
		}

		if (topPipe1.x <= -44.5) {
			topPipe1.x = (float) (w + 44.5);
			botPipe1.x = w;
			topPipe1.y = startPos.y;
			botPipe1.y = startPos.y;
		}

		if (topPipe2.x <= -44.5) {
			topPipe2.x = (float) (w + 44.5);
			botPipe2.x = w;
			topPipe2.y = startPos2.y;
			botPipe2.y = startPos2.y;
		}
	}

	public void drawGround() {
		parr.noTint();
		parr.rectMode(PConstants.CENTER);
		parr.fill(222, 217, 149);
		parr.stroke(183, 240, 108);
		parr.rect(w / 2, 750, w + 5, 200);
	}

	public void drawBackground() {
		parr.background(114, 198, 208);
		parr.tint(255, 100);
		backgroundPic.resize(w * 2, (int) (h / 1.5));
		parr.image(backgroundPic, w / 2, h / 2 + 60);
	}

	public void hitDetection() {

		if ((topPipe1.x >= 30 * -1 && topPipe1.x <= 130)
				&& (location <= topPipe1.y - 60 || location >= botPipe1.y + 60)) {
			gameOver = true;
		}

		if ((topPipe2.x >= 30 * -1 && topPipe2.x <= 130)
				&& (location <= topPipe2.y - 60 || location >= botPipe2.y + 60)) {
			gameOver = true;
		}

		if (location > 624.5) {
			gameOver = true;
		}

	}

	public void score() {
		/// First set of pipes
		if (topPipe1.x <= 49) {
			increaseScore = true;
			if (increaseScore == true && onOff == true) {
				score++;
				onOff = false;
			}
			increaseScore = false;
		} else if (topPipe1.x > 100)
			onOff = true;

		///// Second set of pipes
		if (topPipe2.x <= 49) {
			increaseScore2 = true;
			if (increaseScore2 == true && onOff2 == true) {
				score++;
				onOff2 = false;
			}
			increaseScore2 = false;
		} else if (topPipe2.x > 100)
			onOff2 = true;
	}

	public void displayScore() {
		if (startGame == true) {
			parr.textFont(font);
			parr.fill(255);
			parr.text(score, w / 2 - 16, 100);

			parr.textSize(32);
			parr.text(hiScore, w / 2 - 8, 132);
		}
	}

	public void hiScore() {
		if (score > hiScore) {
			hiScore = score;
			saveScore = true;
		}
		hiScores = "" + hiScore;
		scores[0] = hiScores;
	}

	void savehiScore() {
		if (saveScore == true) {
			parr.saveStrings("hiScore.txt", scores);
			saveScore = false;
		}
	}

	public void menuScreen() {
		parr.textFont(font);
		parr.fill(255);
		parr.textSize(32);
		if (startGame == false) {
			parr.text("press space", textLocation.x - 100, textLocation.y - 50);
			parr.text("to start!", textLocation.x - 75, textLocation.y - 15);
			parr.textSize(20);
			parr.text("By: Alain Ballen", textLocation.x - 230, textLocation.y - 370);
		}
	}

	public void deathEffect() {
		if (gameOver == true) {
			parr.rectMode(PConstants.CENTER);
			parr.noStroke();
			parr.fill(0, tintVal);
			if (tintVal > 0) {
				tintVal -= 20;
			}
			parr.rect(w / 2, h / 2, w, h);
		}
	}

	public void textAnimation() {

		textLocation.add(textVelocity);
		textVelocity.add(textGrav);
		if (textLocation.x >= w / 2) {
			textVelocity.x = (float) (textVelocity.x * -0.91);
			textLocation.x = w / 2;
		}
		if (textLocation.y >= h / 2) {
			textVelocity.y = (float) (textVelocity.y * -0.91);
			textLocation.y = h / 2;
		}
	}

	public void setMovementFactors() {
		location = (float) (h / 2.5);
		velocity = (float) 1.5;
		gravity = (float) 0.63;
	}

	public void movement() {
		if (startGame == true && gameOver == false && fallDown == false) {
			location = location + velocity;
			velocity = velocity + gravity;

			if (jump == true) {
				velocity = 7;
				velocity = (float) (velocity * -1.362);
				jump = false;
			}

			if (location < 24) {
				location = 24;
				velocity = 0;
			}
		} else if (gameOver == true) {
			velocity = 12;
			gravity = 4;
			location = location + velocity;
			if (location > 624.5) {
				location = (float) 624.5;
				velocity = (float) 1.1;
				resetText();
			}
		}
	}

	public void reset() {
		score = 0;
		location = (float) (h / 2.5); // Location of shape
		velocity = -7; // Velocity of shape
		gravity = (float) 0.63;
		tintVal = 225;

		startPos = new PVector(w + 295, parr.random(150, h - 240));
		startPos2 = new PVector(w + 295, parr.random(150, h - 240));

		////// First set
		botPipe1 = new PVector(startPos.x, startPos.y, 700); // x-location, y-location, height
		topPipe1 = new PVector((float) (startPos.x + 44.5), startPos.y); // x-location, y-location
		////// Second set
		botPipe2 = new PVector(startPos2.x + 295, startPos2.y, 700); // x-location, y-location, height
		topPipe2 = new PVector((float) (startPos2.x + 339.5), startPos2.y); // x-location, y-location

	}

	public void resetText() {
		parr.textFont(font);
		parr.fill(255);
		parr.textSize(32);

		parr.text("press space", textLocation.x - 100, textLocation.y - 50);
		parr.text("to restart!", textLocation.x - 75, textLocation.y - 15);
	}

	public void keyPressed() {
		if (gameOver == false) {
			if (parr.key == 32) {
				jump = true;
				startGame = true;
			}
		} else {
			if (parr.key == 32 && location >= 624.5) {
				startGame = true;
				gameOver = false;
				fallDown = false;
				reset();
			}
		}
	}

}
