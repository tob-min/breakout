import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Screen extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	private LinkedList<Brick> bricks = new LinkedList<Brick>();
	private boolean left = false;
	private boolean right = false;
	private Paddle paddle;
	private Ball ball;
	private Timer timer;

	public Screen() {
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();

		paddle = new Paddle(200);
		ball = new Ball();

		Color[] colours = { Color.RED, new Color(239, 46, 7), Color.YELLOW, Color.GREEN, Color.BLUE };
		int width = 60;
		int height = 20;
		int spacing = 2;

		for (int i = 0; i < 5; i++) {
			for (int j = spacing; j <= BreakoutGame.frameWidth - width; j += width + spacing) {
				bricks.add(new Brick(j, 100 + i * (height + spacing), colours[i], width, height));
			}
		}

		timer = new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 5; i++) {
					ball.move();
					if (left) {
						paddle.moveLeft();
					}
					if (right) {
						paddle.moveRight();
					}
					checkCollision();
				}
				repaint();
			}
		});
		timer.start();
	}

	public void checkCollision() {

		// paddle collision
		if (ball.getY() + ball.getR() >= paddle.getY() && ball.getY() <= paddle.getY()
				&& ball.getX() + ball.getR() >= paddle.getX()
				&& ball.getX() - ball.getR() <= paddle.getX() + paddle.getWidth()) {
			ball.paddleBounce((ball.getX() - paddle.getX()) / paddle.getWidth());
		} else if (ball.getY() > paddle.getY() + 4 * ball.getR()) { // life lost
			lifeLost();
		} else {
			LinkedList<Brick> remove = new LinkedList<Brick>();
			for (Brick brick : bricks) {
				if (ball.brickHit(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight())) {
					if (brick.hit()) {
						remove.add(brick);
					}
				}
			}
			for (Brick brick : remove) {
				bricks.remove(brick);
			}
		}
	}

	public void lifeLost() {
		timer.stop();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paddle.paintComponent(g);
		ball.paintComponent(g);

		for (Brick brick : bricks) {
			brick.paintComponent(g);

		}
	}

	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		switch (code) {

		case KeyEvent.VK_LEFT:
			left = true;
			break;

		case KeyEvent.VK_RIGHT:
			right = true;
			break;

		case KeyEvent.VK_P:
			if (timer.isRunning()) {
				timer.stop();
				// paddle.setMove(false);
			} else {
				timer.start();
				// paddle.setMove(true);
			}
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {

		case KeyEvent.VK_LEFT:
			left = false;
			break;

		case KeyEvent.VK_RIGHT:
			right = false;
			break;

		case KeyEvent.VK_P:
			if (timer.isRunning()) {
				timer.stop();
				// paddle.setMove(false);
			} else {
				timer.start();
				// paddle.setMove(true);
			}
			break;
		}

	}

}
