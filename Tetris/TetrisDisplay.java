import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Color;

/*
 *Displays a game of Tetris
 */
public class TetrisDisplay extends JFrame implements KeyListener{

	private JPanel contentPane;
	private JLabel score = new JLabel();
	private Tetris game;
	private Tetromino block;
//	private int timeToNextBlock = 100;
	private Timer fallTimer;
	private JButton playQuitButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TetrisDisplay frame = new TetrisDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Adds a button panel to the frame and
	 * initializes the usage of each button.
	 */
	public TetrisDisplay() {
		game = new Tetris();
//		block = game.newTetromino();
		addKeyListener(this);
		setFocusable(true);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/*
		 * creates a Timer and defines what will occur when
		 * it is run when the user clicks the "start" button
		 */
		fallTimer = new Timer(generateGravity(), new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// what should happen each time the Timer is fired off
//				System.out.println(fallTimer.getDelay());
//				boolean movable = true;
//				if(!(block == null)) {
//					if(movable) {
//						movable = block.softFall();
//						if(!movable && timeToNextBlock > 1) timeToNextBlock = 1;
//					}
//					else block.softFall();
//					block.draw();
//					if(timeToNextBlock <= 0 ) blockStopped();
//					repaint();
//					timeToNextBlock--;
//				}
				if(!(block == null)) {
					if(!block.softFall()) { 
						
						//set half second delay here
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						blockStopped();
					
					}
					if(!(block==null))
						block.draw();
				}
				repaint();
			}
			
		});
		
		/*
		 * creates the button panel
		 */
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		
		/*
		 * creates a button that starts and resets the game
		 * The label toggles between "Play" and "Quit"
		 */
		playQuitButton = new JButton("Play");
		buttonPanel.add(playQuitButton);
		
		playQuitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(playQuitButton.getText().equals("Play")){
					playQuitButton.setText("Quit");
					if(block == null) { 
						game.gameStartTetrominos();
						block = game.newTetromino();
						repaint();
					}
					fallTimer.start();
					resetFocus();
				}
				else{
					playQuitButton.setText("Quit");
//					fallTimer.stop();
//					resetFocus();
					playQuitButton.setText("Play");
					if(!game.gameLost()) {
						game.loseGame();
						gameover();
				}
//					resetFocus();
				}
				
			}
			
		});
		
		
		/*
		 * displays the points
		 */
		score.setText("Level: " + game.getLevel() + " Points: " + game.getPoints());
		buttonPanel.add(score);
		
		/*
		 * adds the panel which displays the Game
		 * board. See the BoardPanel class for details.
		 */
		JPanel boardPanel = new BoardPanel(game);
		contentPane.add(boardPanel, BorderLayout.CENTER);
		
		/*
		 * adds a panel to display the held piece
		 */
		JPanel heldPanel = new HoldPanel(game);
		contentPane.add(heldPanel, BorderLayout.WEST);
		heldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel(" Held Block "); 
		heldPanel.add(lblNewLabel);
		
		JPanel nextPanel = new NextPanel(game);
		contentPane.add(nextPanel, BorderLayout.EAST);
		
		JLabel lblNewLabel_1 = new JLabel(" Next Block "); 
		nextPanel.add(lblNewLabel_1);
		
	}
	
	@Override
	/*
	 * controls for playing the game
	 */
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();

		if(!(block == null)) {
			switch(keyCode) {
			
			case KeyEvent.VK_UP:
	            // handle up 
				block.spin();
	            break;
	        case KeyEvent.VK_DOWN:
	            // handle down
	        	block.softFall();
	            break;
	        case KeyEvent.VK_LEFT:
	            // handle left
	        	block.moveLeft();
	            break;
	        case KeyEvent.VK_RIGHT:
	            // handle right
	        	block.moveRight();
	            break;
	        case KeyEvent.VK_C: //hold
	        	//handle c
	        	if(!game.getHolding()) { //this check doesnt work for the first time
		        	int type = block.getBlockType();
		        	//remove old block
		        	for(int i = 0; i < 4; i++) {
		        		game.setPixel(block.getLocation()[i][0], block.getLocation()[i][1], 0);
		        		game.setPixel(block.getDropShadow()[i][0], block.getDropShadow()[i][1], 0);
		        	}
		        	block = game.hold(type);
	        	}
	        	break;
	        case KeyEvent.VK_SPACE :
	        	//handle space
	        	blockStopped();
	        	break;
	        //for bug tests only
	        case KeyEvent.VK_N: //new tetromino
				if(block.getMoving() == false)
					newTetromino();
				break;
			case KeyEvent.VK_M:
				gameover();
				break;
	        case KeyEvent.VK_S:
	        	fallTimer.setDelay(generateGravity());
	        	break;
			}
			if(!(block==null)) block.draw();
			repaint();
	//		System.out.println(game);
		}
		
	}
	
	/*
	 * generates the time per row in milliseconds
	 * does so with the following formula (from Tetris Wiki):
	 * 	1000 * (0.8-((Level-1)*0.007))^(Level-1)
	 * 
	 * returns the value as an int
	 */
	public int generateGravity() {
		return (int) (1000 * Math.pow((0.8-((game.getLevel()-1)*0.007)), (game.getLevel()-1)));
	}
	
	/*
	 * what to do to stop a block
	 */
	public void blockStopped() {
		block.hardFall();
    	block.draw();
    	repaint();
    	newTetromino();
    	gameover();
    	fallTimer.setDelay(generateGravity());
//    	timeToNextBlock = game.getBoard().getHeight();
	}
	
	/*
	 * what to do when a new tetromino is called
	 */
	public void newTetromino() {
		block.setMoving(false);
		game.clearRows();
		score.setText("Level: " + game.getLevel() + " Points: " + game.getPoints());
		block = game.newTetromino();
	}
	
	/*
	 * what to do when the game is lost
	 */
	public void gameover() {
		if(game.gameLost()) {
			System.out.println("Game Over");
			game.clear();
			game.setPoints(0);
			game.setLevel(1);
			score.setText("Level: " + game.getLevel() + " Points: " + game.getPoints());
			playQuitButton.setText("Play");
			block = null;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void resetFocus() {
		requestFocus();
	}

}
