/*
 * A class to represent the game board for a game of tetris
 * The gamestate is represented by a 2D int array. (0,0) at top left corner
 * 
 * Ben Lin
 */
public class GameBoard {
	private int[][] gameState;
	private int height;
	private int width;
	private int[][] held;
	private int[][][] next;
	
	/*
	 * constructs a 10 x 22 blank board
	 * is the default tetris board size (top 2 rows are hidden)
	 * level and points both default to 0
	 */
	public GameBoard() {
		this(22, 10);
	}
	
	/*
	 * constructs a board of given height and width and level
	 * 
	 * Parameters:
	 * 	int height: the height of the board (top 2 rows hidden)
	 * 	int width: the width of the board
	 */
	public GameBoard(int height, int width) {
		this.height = height;
		this.width = width;
		gameState = new int[height][width];
		held = new int[2][4];
		next = new int[3][2][4];
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public int getPixel(int x, int y) {
		if(outOfBounds(x,y)) return 9;
		return gameState[x][y];
	}
	
	public void setPixel(int x, int y, int block) {
		gameState[x][y] = block;
	}
	
	public int getHeldPixel(int x, int y) {
		return held[x][y];
	}
	
	public void setHeldPixel(int x, int y, int block) {
		held[x][y] = block;
	}
	
	public int getNextPixel(int slot, int x, int y) {
		return next[slot][x][y];
	}
	
	public void setNextPixel(int slot, int x, int y, int block) {
		next[slot][x][y] = block;
	}
	
	public int[][] getGameState(){
		return gameState;
	}
	
	/*
	 * returns whether a given pixel is out of bounds
	 * used by the isOccupied(x,y) method
	 */
	private boolean outOfBounds(int x, int y) {
		return x < 0 || x >= height || y < 0 || y >= width;
	}
	
	/*
	 * returns whether a given pixel is occupied
	 * a pixel that is not on the board is counted as occupied
	 * 
	 * Parameters:
	 * 	int x; the x coordinate
	 * 	int y; the y coordinate
	 */
	public boolean isOccupied(int x, int y) {
		if(outOfBounds(x, y)) return true;
		return gameState[x][y] != 0 && gameState[x][y] != 8;
	}
	
	/*
	 * returns whether a given pixel is occupied
	 * 
	 * Parameters:
	 * 	int[] pixel (length 2): the pixel and a two value array
	 * 
	 * Uses the isOccupied(x,y) method
	 */
	public boolean isOccupied(int[] pixel) {
		if(pixel.length != 2) return false; //method was incorrectly used
		return isOccupied(pixel[0], pixel[1]);
	}
	
	/*
	 * returns whether the given row is full
	 */
	public boolean rowFull(int rowNum) {
		for(int i = 0; i < width; i++) {
			if(!isOccupied(rowNum,i)) return false;
		}
		return true;
	}
	
	/*
	 * deletes the given row and moves everything above it down 1 row
	 */
	public void deleteRow(int rowNum) {
		for(int i = rowNum; i > 0; i--) {
			gameState[i] = gameState[i-1];
			gameState[i-1] = new int[width];
		}
	}
	
	/*
	 * clears everything
	 */
	public void clear() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				gameState[i][j] = 0;
			}
		}
		clearHold();
		clearNext();
	}
	
	/*
	 * clears the hold space
	 */
	public void clearHold() {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				setHeldPixel(i,j,0);
			}
		}
	}
	
	/*
	 * clears the next space
	 */
	public void clearNext() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 4; k++) {
					setNextPixel(i,j,k,0);
				}
			}
		}
	}
	
	/*
	 * draws a block of given type in the correct place
	 * 
	 * Parameters:
	 * 	int[][] blocks: array of positions to be drawn in
	 * 	int blockType: the type of block
	 */
	public void drawBlock(int[][] blocks, int blockType) {
		for(int i = 0; i < blocks.length; i++) {
			setPixel(blocks[i][0], blocks[i][1], blockType);
		}
	}

	/*
	 * toString to print the current state of the board
	 */
	public String toString() {
		String output = "";
		for(int[] a: gameState) {
			for(int b: a) {
				output += b;
			}
			output += "\n";
		}
		return output;
	}

}
