import java.util.Arrays;

/*
 * Class to create tetrominos for tetris
 * 
 * Ben Lin
 */
public abstract class Tetromino {
	protected GameBoard board;
	protected Tetris game;
	protected int orientation;
	protected int[][] location;
	protected int blockType;
	protected boolean moving;
	protected int[][] dropShadow;
	
	/*
	 * creates a tetromino in the given game
	 */
	public Tetromino(GameBoard board) {
		this.board = board;
		orientation = 0;
		location = new int[4][2];
		moving = true;
		
		int[][] shadow = new int[4][2];
		for(int i = 0; i < 4; i++) {
			shadow[i][0] = location[i][0];
			shadow[i][1] = location[i][1];
		}
		dropShadow = shadow;
		
	}
	
	
	/*
	 * creates a shadow under the block of where it would land if it fell
	 * returns the shadow as an int[][] of the coordinates of the pixels
	 */
	private int[][] createDropShadow(){
		if(!moving) {
			return new int[4][2];
		}
			//create shadow
			int[][] shadow = new int[4][2];
			for(int i = 0; i < 4; i++) {
				shadow[i][0] = location[i][0];
				shadow[i][1] = location[i][1];
			}
			//remove the old shadow
			for(int i = 0; i < 4; i++) {
				board.setPixel(dropShadow[i][0], dropShadow[i][1], 0);
			}
			
			//drop the shadow
			for(int i = 0; i < board.getHeight(); i++) {
				boolean movable = true;
				for(int j = 0; j < 4; j++) {
					if(board.isOccupied(shadow[j][0]+1, shadow[j][1]))
						movable = false;
				}
				if(movable) {
					for(int j = 0; j < 4; j++) {
						shadow[j][0] = ++shadow[j][0];
					}
				}
				
			}
			return shadow;
	}
	
	
	public int[][] getLocation(){
		return location;
	}
	
	public int[][] getDropShadow(){
		return dropShadow;
	}
	
	public void setBlockType(int blockType) {
		//to do
	}
	
	public int getBlockType() {
		return blockType;
	}
	
	public int getOrientation() {
		return orientation;
	}
	
	public boolean getMoving() {
		return moving;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	/*
	 * spins the tetromino left by one
	 * and adjusts the orientation accordingly
	 */
	public void spin() {
		if(orientation == 3) orientation = 0;
		else orientation++;
		
		dropShadow = createDropShadow();
	}
	
	/*
	 * makes tetromino fall by 1 space
	 */
	public boolean softFall() {
		int[][] old = this.moved();
		boolean movable = true;
		for(int i = 0; i < 4; i++) {
			if(board.isOccupied(old[i][0]+1, old[i][1]))
				movable = false;
		}
		if(movable) {
			for(int i = 0; i < 4; i++) {
				location[i][0] = ++old[i][0];
			}
		}
		dropShadow = createDropShadow();
		return movable;
	}
	
	/*
	 * makes tetromino fall to the bottom
	 */
	public void hardFall() {
		for(int i = location[0][0]; i < board.getHeight(); i++) {
			this.softFall();
		}
		moving = false;
	}
	
	/*
	 * moves the tetromino left 1 space
	 */
	public void moveLeft() {
		int[][] old = moved();
		boolean movable = true;
		for(int i = 0; i < 4; i++) {
			if(board.isOccupied(old[i][0], old[i][1]-1))
				movable = false;
		}
		if(movable) {
			for(int i = 0; i < 4; i++) {
				location[i][1] = --old[i][1];
			}
		}
		dropShadow = createDropShadow();
	}
	
	/*
	 * moves the tetromino right 1 space
	 */
	public void moveRight() {
		int[][] old = moved();
		boolean movable = true;
		for(int i = 0; i < 4; i++) {
			if(board.isOccupied(old[i][0], old[i][1]+1))
				movable = false;
		}
		if(movable) {
			for(int i = 0; i < 4; i++) {
				location[i][1] = ++old[i][1];
			}
		}
		dropShadow = createDropShadow();
	}
	
	/*
	 * helper method for the tetromino being moved
	 * Deletes the old tetromino
	 * returns its old pixel coordinates
	 */
	protected int[][] moved(){
		int[][] old = new int[4][2];
		for(int i = 0; i < 4; i++) {
			old[i][0] = location[i][0];
			old[i][1] = location[i][1];
			board.setPixel(location[i][0], location[i][1], 0);
		}
		return old;
	}
	
	public void draw() {
		for(int i = 0; i < 4; i++) {
			board.setPixel(dropShadow[i][0], dropShadow[i][1], 8);
			board.setPixel(location[i][0], location[i][1], blockType);	
		}
	}

}
