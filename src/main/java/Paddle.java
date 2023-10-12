import java.awt.*;

public class Paddle {

	private int x, y, width, height, arcWidth, arcHeight, speed;
	private boolean canMove;

	public Paddle(int width) {

		this.width = width;
		height = 10;
		arcWidth = 10;
		arcHeight = 10;
		x = BreakoutGame.frameWidth / 2 - width / 2;
		y = BreakoutGame.frameHeight - 50;
		canMove = true;
		speed = 2;
	}
	
	public void moveLeft() {
		if (canMove) {
			x -= speed;
			if (x < 0) {
				x = 0;
			}
		}
	}

	public void moveRight() {
		if (canMove) {
			x += speed;
			if (x > BreakoutGame.frameWidth - width) {
				x = BreakoutGame.frameWidth - width;
			}
		}
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
	
	//getters and setters	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}

	public void setMove(boolean b) {
		canMove = b;
	}

}
