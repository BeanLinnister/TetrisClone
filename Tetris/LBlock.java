/*
 * the L termino. has blockType 3
 *   X
 * XXX
 */
public class LBlock extends Tetromino {

	public LBlock(GameBoard game) {
		super(game);
		int mid = game.getWidth() / 2;
		location[0][0] = 0; 
		location[0][1] = mid + 1;
		for(int i = 1; i < 4; i++) {
			location[i][0] = 1;
			location[i][1] = mid - 2 + i;
		}
		softFall();
		softFall();
		blockType = 3;
	}
	
	/*
	 * spins the termino by 1 clockwise
	 */
	//the dot spins independently from the line at times. FIX
	public void spin() {
		int[][] old = moved();
		boolean movable = true;
		int[] spinPoint = old[2];
		/*
		 * moves the dot and calls spinLine to spin the 3pixel line part
		 */
		if(orientation==0) {
			if(board.isOccupied(spinPoint[0]+1,spinPoint[1]+1)) movable = false;
			movable = spinLine(spinPoint, 0, movable);
			if(movable) {
				location[0][0] = spinPoint[0]+1;
				location[0][1] = spinPoint[1]+1;
			}
			
		}
		if(orientation==1) {
			if(board.isOccupied(spinPoint[0]+1, spinPoint[1]-1)) movable = false;
			movable = spinLine(spinPoint, 1, movable);
			if(movable) {
				location[0][0] = spinPoint[0]+1;
				location[0][1] = spinPoint[1]-1;
			}
			
		}
		if(orientation==2) {
			if(board.isOccupied(spinPoint[0]-1,spinPoint[1]-1)) movable = false;
			movable = spinLine(spinPoint, 2, movable);
			if(movable) {
				location[0][0] = spinPoint[0]-1;
				location[0][1] = spinPoint[1]-1;
			}
			
		}
		if(orientation==3) {
			if(board.isOccupied(spinPoint[0]-1,spinPoint[1]+1)) movable = false;
			movable = spinLine(spinPoint, 3, movable);
			if(movable) {
				location[0][0] = spinPoint[0]-1;
				location[0][1] = spinPoint[1]+1;
			}
			
		}
		if(movable) super.spin(); //changes orientation if spun
	}
	
	/*
	 * helper method to spin the 3 pixel line part of the L termino
	 */
	private boolean spinLine(int[] spinPoint, int orient, boolean movable) {
		if(orient == 0 || orient == 2) { //currently horizontal
			for(int i = 1; i < 4 && movable; i++) {
				if(board.isOccupied(spinPoint[0]-2+i, spinPoint[1]))
					movable = false;
			}
			if(movable) {
				for(int i = 1; i < 4; i++) {
					location[i][0] = spinPoint[0]-2+i;
					location[i][1] = spinPoint[1];
				}
			}
		}
		else { //currently vertical
			for(int i = 1; i < 4 && movable; i++) { 
				if(board.isOccupied(spinPoint[0], spinPoint[1]-2+i))
					movable = false;
			}
			if(movable) {
				for(int i = 1; i < 4; i++) {
					location[i][0] = spinPoint[0];
					location[i][1] = spinPoint[1]-2+i;
				}
			}
		}
		return movable;
	}

}
