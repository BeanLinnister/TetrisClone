import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class NextPanel extends JPanel {
	Tetris game;
	private int sqSize = 10;
	
	
	/**
	 * Create the panel.
	 */
	public NextPanel(Tetris game) {
		this.game = game;

	}
	
	/**
	 * Paints the current board
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		//draws in the outline for the next boxes
//		for(int i = 0; i < 2; i++) {
//			for(int j = 0; j < 4; j++) {
//				g2.drawRect(7+sqSize*j, 25+sqSize*i, sqSize, sqSize);
//				g2.drawRect(7+sqSize*j, 25+sqSize*i + 2*sqSize+10, sqSize, sqSize);
//				g2.drawRect(7+sqSize*j, 25+sqSize*i + 4*sqSize+20, sqSize, sqSize);
//			}
//		}
		for(int spot = 0; spot < 3; spot++) { //cycle through the 3 spots
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 4; j++) {
					if(game.getBoard().getNextPixel(spot,i,j) != 0) {
						switch(game.getBoard().getNextPixel(spot,i,j)) {
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
						default : g.setColor(Color.RED);
						}
						/* sqSize = a
						 * spot = b
						 * 25 + ai + 2ba + 10b
						 * 25 + 10b + (2b+i)a
						 */
						g2.fillRect(7+sqSize*j, 25 + 10*spot + (2*spot + i) * sqSize, sqSize, sqSize);
					}
				}
			}
		}
	}

}
