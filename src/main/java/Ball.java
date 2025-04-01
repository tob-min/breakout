import java.awt.*;
import java.util.Random;

public class Ball {

	// variables
	private double x;
	private double y;
	private int speed;
	private int r;
	private double dx;
	private double dy;
	private double angle;
	private final double maxAngle; // greatest angle from horizontal that can be set

	public static final double PI = Math.PI;
	private Random rand = new Random();

	// constructor
	public Ball(int r, int v, double maxAngle) {

		this.r = r;
		speed = v;
		this.maxAngle = maxAngle;
		angle = rand.nextDouble(maxAngle, PI - maxAngle);
		x = BreakoutGame.frameWidth / 2;
		y = BreakoutGame.frameHeight - 60;

		updateV();
	}

	public Ball() {
		this(7, 1, Math.PI / 5);
	}

	// methods

	private void updateV() {
		dx = speed * Math.cos(angle);
		dy = speed * Math.sin(angle);
	}

	public void move() {
		x += dx;
		y -= dy;
		if (x - r <= 0 && dx < 0 || x + r >= BreakoutGame.frameWidth && dx > 0) {
			sideBounce();
		} else if (y - r <= 0 && dy > 0 || y + r >= BreakoutGame.frameHeight && dy < 0) {
			roofBounce();
		}
	}

	public void sideBounce() {
		dx = -dx;
		angle = (PI - angle) % (2 * PI);
	}

	public void roofBounce() {
		dy = -dy;
		angle = (2 * PI - angle) % (2 * PI);
	}

	public void paddleBounce(double ratio) {
		angle = PI - maxAngle - ratio * (PI - 2 * maxAngle);
		updateV();
	}

	public boolean brickHit(int bx, int by, int width, int height) {
		if (x - r <= bx + width && x + r >= bx && y - r <= by + height && y + r >= by) { // ball is touching brick

			if (dx > 0 && bx - x > 0 && bx - x <= r || dx < 0 && bx + width - x < 0 && bx + width - x >= -r) {
				sideBounce();
			}
			if (dy < 0 && by - y > 0 && by - y <= r || dy > 0 && by + height - y < 0 && by + height - y >= -r) {
				roofBounce();
			}
			return true;
		}
		return false;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval((int) (x - r), (int) (y - (r)), 2 * r, 2 * r);
	}

	// getters
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getR() {
		return r;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

}
