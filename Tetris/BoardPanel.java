import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/*
 * A class that extends the JPanel class, adding the functionality
 * of painting the tetris board
 */
public class BoardPanel extends JPanel{
	private Tetris game;
	private int sqSize = 15;
	private int gameHeight;
	private int gameWidth;
	
	public BoardPanel(Tetris game2){
		game = game2;
		gameHeight = game.getBoard().getHeight();
		gameWidth = game.getBoard().getWidth();
	}
	
	/**
	 * Paints the current board
	 */
	public void paintComponent(Graphics g){
		sqSize = this.getHeight() / (game.getBoard().getHeight()+1);
		int mid = this.getWidth() / 2;
		int topLeftCorner = mid - gameWidth / 2 * sqSize ;
		Graphics2D g2 = (Graphics2D)g;
		for(int i = 2; i < gameHeight; i++) {
			for(int j = 0; j < gameWidth; j++) {
				g2.drawRect(topLeftCorner + sqSize*j, sqSize*i, sqSize, sqSize);
			}
		}
		
		for(int i = 2; i < gameHeight; i++) {
			for(int j = 0; j < gameWidth; j++) {
				if(game.getBoard().getPixel(i, j) != 0) {
					switch(game.getBoard().getPixel(i,j)) {
					case 1: g.setColor(Color.CYAN);
							break;
					case 2: g.setColor(Color.BLUE);
							break;
					case 3: g.setColor(Color.ORANGE);
							break;
					case 4: g.setColor(Color.YELLOW);
							break;
					case 5: g.setColor(Color.GREEN);
							break;
					case 6: g.setColor(Color.MAGENTA);
							break;
					case 7: g.setColor(Color.RED);
							break;
					default: g.setColor(Color.LIGHT_GRAY);
							break;
					}
					g2.fillRect(topLeftCorner + sqSize*j, sqSize*i, sqSize, sqSize);
				}
			}
		}
	}
}
