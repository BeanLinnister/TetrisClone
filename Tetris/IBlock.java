/*
 * the I termino. has blockType 1
 * XXXX
 */
public class IBlock extends Tetromino {

	public IBlock(GameBoard game) {
		super(game);
		int mid = game.getWidth() / 2;
		for(int i = 0; i < 4; i++) {
			location[i][0] = 0;
			location[i][1] = mid - 1 + i;
		}
		softFall();
		softFall();
		blockType = 1;
	}
	
	/*
	 * spins the termino by 1 clockwise
	 */
	public void spin() {
		int[][] old = moved();
		boolean movable = true;
		int[] spinPoint = old[2];
		if(orientation == 0 || orientation == 2) { //currently horizontal
			for(int i = 0; i < 4 && movable; i++) {
				if(board.isOccupied(spinPoint[0]-1+i, spinPoint[1]))
					movable = false;
			}
			if(movable) {
				for(int i = 0; i < 4; i++) {
					location[i][0] = spinPoint[0]-1+i;
					location[i][1] = spinPoint[1];
				}
			}
		}
		else { //currently vertical
			for(int i = 0; i < 4 && movable; i++) { 
				if(board.isOccupied(spinPoint[0]-1, spinPoint[1]-2+i))
					movable = false;
			}
			if(movable) {
				for(int i = 0; i < 4; i++) {
					location[i][0] = spinPoint[0]-1;
					location[i][1] = spinPoint[1]-2+i;
				}
			}
		}
		if(movable) super.spin(); //change orientation if spun
	}

}
