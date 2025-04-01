import javax.swing.*;
import java.io.Serial;

public class BreakoutGame extends JFrame {

	@Serial
	private static final long serialVersionUID = 1L;

	public static final int frameWidth = 1000;
	public static final int frameHeight = 600;

	private Screen screen;

	public BreakoutGame() {
		setTitle("Breakout game");
		
		setBounds(0,0, frameWidth + 17, frameHeight + 40);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		screen = new Screen();
		add(screen);
		setVisible(true);

	}

}
