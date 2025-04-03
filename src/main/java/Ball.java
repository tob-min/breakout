import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ball {

    public static final double PI = Math.PI;
    private final double maxAngle; // greatest angle from horizontal that can be set
    // variables
    private double x, y; // (0,0) top left corner
    private int r;
    private double dx; // right +ve
    private double dy; // up +ve
    private boolean alive;

    private Random rand = new Random();

    // constructor
    public Ball(int r, int v, double maxAngle) {

        this.r = r;
        this.maxAngle = maxAngle;


        double speed = v;
        double angle = rand.nextDouble(maxAngle, PI - maxAngle);

        setV(angle, speed);
        alive = true;
        x = BreakoutGame.frameWidth / 2;
        y = BreakoutGame.frameHeight - 60;
    }

    // methods

    public Ball() {
        this(7, 1, Math.PI / 5);
    }

    private void setV(double angle, double speed) {
        dx = speed * Math.cos(angle);
        dy = speed * Math.sin(angle);
    }

    private void setDirection(double angle) {
        setV(angle, Math.sqrt(dx * dx + dy * dy));
    }

    public void move() {
        x += dx;
        y -= dy;
        if (x - r <= 0 && dx < 0 || x + r >= BreakoutGame.frameWidth && dx > 0) {
            sideBounce();
        } else if (y - r <= 0 && dy > 0) {
            roofBounce();
        }
    }

    public void sideBounce() {
        dx = -dx;
    }

    public void roofBounce() {
        dy = -dy;
    }

    public boolean intersectsPlane(int px, int py, int width, int height) {
        return x - r <= px + width && x + r >= px && y - r <= py + height && y + r >= py;
    }

    public void checkCollisions(ArrayList<Brick> bricks, Paddle p, int deathHeight) {
        if (y > deathHeight) {
            alive = false;
        } else {
            checkPaddleCollision(p);
            for (Brick b : bricks) {
                checkBrickHit(b);
            }
        }
    }

    public void checkPaddleCollision(Paddle paddle) {
        if (intersectsPlane(paddle.getX(), paddle.getY(), paddle.getWidth(), 0)) {
            paddleBounce((x - paddle.getX()) / paddle.getWidth());
        }
    }

    public void paddleBounce(double ratio) {
        double angle = PI - maxAngle - ratio * (PI - 2 * maxAngle);
        setDirection(angle);
    }

    public void checkBrickHit(Brick b) {
        int bx = b.getX();
        int by = b.getY();
        int width = b.getWidth();
        int height = b.getHeight();
        if (intersectsPlane(bx, by, width, height)) { // ball is touching brick

            if (dx > 0 && bx > x && bx <= x + r || dx < 0 && bx + width < x && bx + width >= x - r) {
                sideBounce();
            }
            if (dy < 0 && by > y && by <= y + r || dy > 0 && by + height < y && by + height >= y - r) {
                roofBounce();
            }
            b.hit();
        }
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
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

    public boolean isAlive() {
        return alive;
    }
}
