import java.awt.*;

public class Brick {

	private int x, y, width, height;
	private int health;
	private Color colour;

	public Brick(int x, int y, Color colour, int width, int height) {
		this.x = x;
		this.y = y;
		this.colour = colour;
		this.width = width;
		this.height = height;
		health = 1;
	}

	public Brick(int x, int y, Color colour) {
		this(x, y, colour, 50, 20);
	}

	public Brick(int x, int y) {
		this(x, y, Color.RED);
	}

	public void paintComponent(Graphics g) {
		g.setColor(colour);
		g.fillRoundRect(x, y, width, height, 10, 10);
	}

	public void hit() {
		health--;
	}
	
	public String toString() {
		return String.format("Brick: (%d, %d); Colour: %s; width: %d; height: %d" , x, y, colour, width, height);
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public boolean isAlive() { return health > 0;}

}
