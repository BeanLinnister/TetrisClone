import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class Tetris {
	GameBoard board;
	private int level;
	private int points;
	private int scoreToNextLevel;
	private int held = 0;
	private int[] next = {0,0,0};
	private boolean holding = false;
	private ArrayList<Integer> bag;

	/*
	 * initiates a game of tetris with a default sized board and at level 1
	 */
	public Tetris() {
		this(22,10,1);
//		placeHeld();
	}
	
	/*
	 * initiates a game of tetris with the given dimensions at the given level
	 */
	public Tetris(int height, int width, int level) {
		board = new GameBoard(height, width);
		this.level = level;
		scoreToNextLevel = 5 * level;
		bag = newBag();
	}
	
	/*
	 * initiates a game of tetris with the given level
	 */
	public Tetris(int level) {
		this();
		this.level = level;
		scoreToNextLevel = 5 * level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) { 
		this.level = level;
		scoreToNextLevel = level * 5;
	}
	
	/*
	 * increases the level up one and adjusts the scoreToNextLevel accordingly
	 */
	public void levelUp() {
		if(scoreToNextLevel <= 0) {
			level++;
			scoreToNextLevel = level * 5;
//			scoreToNextLevel = 1;
		}
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public GameBoard getBoard() {
		return board;
	}
	
	public void setPixel(int x, int y, int block) {
		board.setPixel(x, y, block);
	}
	
	public boolean getHolding() {
		return holding;
	}
	
	
	/*
	 * holds the current piece given the blockType
	 * 
	 * generates the held piece as a Tetromino using its blockType
	 * returns the held piece as a Tetromino
	 * 
	 */
	public Tetromino hold(int blockType) {
		holding = true;
		int block = held;
		held = blockType;
		placeHeld();
		Tetromino piece;
		switch(block) {
		case 0:
			piece = newTetromino();
			break;
		case 1:
			piece = newIBlock();
			break;
		case 2:
			piece = newJBlock();
			break;
		case 3:
			piece = newLBlock();
			break;
		case 4:
			piece = newOBlock();
			break;
		case 5:
			piece = newSBlock();
			break;
		case 6:
			piece = newTBlock();
			break;
		default:
			piece = newZBlock();
		}
		return piece;
		
	}
	
	
	/*
	 * draws the held piece into the hold space on the board
	 * uses a switch statement to draw the piece using the blockType
	 */
	private void placeHeld() {
		clearHold();
		
		//draw the piece into the space
		switch(held) {
		case 0: //empty
			break;
		case 1: //I 
			for(int i = 0; i < 4; i++) {
				board.setHeldPixel(1, i, 1);
			}
			break;
		case 2: //L
			board.setHeldPixel(0, 1, 2);
			for(int i = 1; i < 4; i++) {
				board.setHeldPixel(1, i, 2);
			}
			break;
		case 3: //J
			board.setHeldPixel(0, 3, 3);
			for(int i = 1; i < 4; i++) {
				board.setHeldPixel(1, i, 3);
			}
				break;
		case 4: //O
			for(int i = 1; i < 3; i++) {
				board.setHeldPixel(0,i,4);
				board.setHeldPixel(1, i, 4);
			}
				break;
		case 5: //S
			for(int i = 1; i < 3; i++) {
				board.setHeldPixel(0,1+i,5);
				board.setHeldPixel(1, i, 5);
			}
				break;
		case 6: //T
			board.setHeldPixel(0, 2, 6);
			for(int i = 1; i < 4; i++) {
				board.setHeldPixel(1, i, 6);
			}
			
				break;
		default : //Z
			for(int i = 1; i < 3; i++) {
				board.setHeldPixel(0,i,7);
				board.setHeldPixel(1,1+i, 7);
			}
		}
	}
	
	/*
	 * places all the next pieces into the corresponding next space
	 * does so using the each next piece's blockType
	 */
	private void placeNext() {
		
		//clear next space
		clearNext();
		
		
		for(int spot = 0; spot < 3; spot++) { //loops through the three next pieces
			
			//draw the piece into the space
			switch(next[spot]) {
			case 0:
				break;
			case 1: 
				for(int j = 0; j < 4; j++) {
					board.setNextPixel(spot,1, j, 1);
				}
				break;
			case 2: 
				board.setNextPixel(spot,0, 1, 2);
				for(int j = 1; j < 4; j++) {
					board.setNextPixel(spot,1, j, 2);
				}
				break;
			case 3: 
				board.setNextPixel(spot,0, 3, 3);
				for(int j = 1; j < 4; j++) {
					board.setNextPixel(spot,1, j, 3);
				}
					break;
			case 4: 
				for(int j = 1; j < 3; j++) {
					board.setNextPixel(spot,0,j,4);
					board.setNextPixel(spot,1, j, 4);
				}
					break;
			case 5: 
				for(int j = 1; j < 3; j++) {
					board.setNextPixel(spot,0,1+j,5);
					board.setNextPixel(spot,1, j, 5);
				}
					break;
			case 6: 
				board.setNextPixel(spot,0, 2, 6);
				for(int j = 1; j < 4; j++) {
					board.setNextPixel(spot,1, j, 6);
				}
				
					break;
			default : 
				for(int j = 1; j < 3; j++) {
					board.setNextPixel(spot,0,j,7);
					board.setNextPixel(spot,1,1+j, 7);
				}
			}
		}
	}

	/*
	 * clears everything on the board
	 */
	public void clear() {
		board.clear();
		
	}
	
	public void clearHold() {
		board.clearHold();
	}
	
	public void clearNext() {
		board.clearNext();
	}
	
	/*
	 * generates the intial three next Tetrominos when a new game starts
	 * creates a bag and draws the first three Tetrominos from the bag
	 */
	public void gameStartTetrominos() {
		bag = newBag();
		for(int i = 0; i < 3; i++) {
			next[i] = bag.remove(0);
		}
		placeNext();
	}
	
	/*
	 * randomly generates a new tetromino
	 * takes the first block in the bag and
	 * returns it as a Tetromino
	 * 
	 * if there is nothing in the bag it creates a new bag using newBag()
	 */
	public Tetromino newTetromino() {
		holding = false;
		Tetromino block;
		if(bag.size() == 0) {
			bag = newBag();
		}
		int nextBlock = bag.remove(0);
		
		switch(next[0]) {
		case 1: block = newIBlock();
				break;
		case 2: block = newJBlock();
				break;
		case 3: block = newLBlock();
				break;
		case 4: block = newOBlock();
				break;
		case 5: block = newSBlock();
				break;
		case 6: block = newTBlock();
				break;
		default: block = newZBlock();
		}
		
		next[0] = next[1];
		next[1] = next[2];
		next[2] = nextBlock;
		placeNext();
		return block;
	}
	
	/*
	 * generates the new Tetrominos similar to the original Tetris games
	 * 	creates a bag of the 7 tetrominos, empties the bag in a random order, repeat
	 * 
	 * returns the bag as an ArrayList
	 */
	private ArrayList<Integer> newBag() {
		ArrayList<Integer> bag = new ArrayList<Integer>();
		for(int i = 1; i <= 7; i++) {
			bag.add(i);
		}
		
		Collections.shuffle(bag);
		
		return bag;
		
	}
	
	/*
	 * methods to make new tetrominos in the game
	 * each returns the Tetromino version of the corresponding block
	 */
	public Tetromino newIBlock() {
		Tetromino iBlock = new IBlock(board);
		iBlock.draw();
		return iBlock;
	}
	public Tetromino newJBlock() {
		Tetromino jBlock = new JBlock(board);
		jBlock.draw();
		return jBlock;
	}
	public Tetromino newLBlock() {
		Tetromino lBlock = new LBlock(board);
		lBlock.draw();
		return lBlock;
	}
	public Tetromino newOBlock() {
		Tetromino oBlock = new OBlock(board);
		oBlock.draw();
		return oBlock;
	}
	public Tetromino newSBlock() {
		Tetromino sBlock = new SBlock(board);
		sBlock.draw();
		return sBlock;
	}
	public Tetromino newTBlock() {
		Tetromino tBlock = new TBlock(board);
		tBlock.draw();
		return tBlock;
	}
	public Tetromino newZBlock() {
		Tetromino zBlock = new ZBlock(board);
		zBlock.draw();
		return zBlock;
	}
	
	/*
	 * method to clear rows if there are full rows
	 */
	public int clearRows() {
		ArrayList<Integer> rows = checkClears();
		int points = clearRows(rows);
		return points;
	}
	
	/*
	 * clears the given rows and awards points/level up accordingly
	 */
	private int clearRows(ArrayList<Integer> rows) {
		int numRows = rows.size();
		levelUp();
		for(int i = 0; i < rows.size(); i++) {
			board.deleteRow(rows.get(i)+i);
		}
		int x = 0;
		if(numRows == 1) { 
			x = 40;
			scoreToNextLevel -= 1;
		}
		if(numRows == 2) {
			x = 100;
			scoreToNextLevel -= 3;
		}
		if(numRows == 3) {
			x = 300;
			scoreToNextLevel -= 5;
		}
		if(numRows == 4) {
			x = 1200;
			scoreToNextLevel -= 8;
		}
		points += x * (level);
		return x * (level);
	}
	
	/*
	 * helper method to check which rows to clear
	 */
	private ArrayList<Integer> checkClears() {
		ArrayList<Integer> rowsToClear = new ArrayList<Integer>();
		for(int i = board.getHeight()-1; i >= 0; i--) {
			if(board.rowFull(i)) rowsToClear.add(i);
		}
		return rowsToClear;
	}
	
	/*
	 * Put in Tetris
	 * returns whether the game has been lost
	 * 
	 * returns true when lost, false when still playing
	 */
	public boolean gameLost() {
		for(int i = 0; i < board.getWidth(); i++) {
			if(board.isOccupied(0,i)) return true;
		}
		return false;
	}
	
	public void loseGame() {
		board.setPixel(0, 0, 1);
	}
	
	public String toString() {
		String output = "level: " + level + 
				"\n points: " + points + "\n";
		for(int i = 2; i < board.getHeight(); i++) {
			for(int a: board.getGameState()[i]) {
				if(a==0) output += ".";
				else output += a;
			}
			output += "\n";
		}
		return output;
	}

}
