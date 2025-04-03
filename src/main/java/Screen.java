import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serial;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Screen extends JPanel implements KeyListener {

	@Serial
	private static final long serialVersionUID = 1L;

	private ArrayList<Brick> bricks = new ArrayList<>();
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

		timer = new Timer(15, _ -> {
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
        });
		timer.start();
	}

	public void checkCollision() {
		ball.checkCollisions(bricks, paddle, paddle.getY() + 20);
		cleanup();
	}

	private void cleanup() {
		if (!ball.isAlive()) {
			lifeLost();
		}
		bricks = bricks.stream().filter(Brick::isAlive).collect(Collectors.toCollection(ArrayList::new));
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
