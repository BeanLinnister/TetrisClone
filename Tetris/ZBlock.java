/*
 * the Z termino. has blockType 7
 * XX
 *  XX
 */
public class ZBlock extends Tetromino {

	public ZBlock(GameBoard game) {
		super(game);
		int mid = game.getWidth() / 2;
		location[0][0] = 0;
		location[0][1] = mid;
		
		location[1][0] = 0;
		location[1][1] = mid -1;
		
		location[2][0] = 1;
		location[2][1] = mid;
		
		location[3][0] = 1;
		location[3][1] = mid + 1;
		
		softFall();
		softFall();
		blockType = 7;
	}
	
	public void spin() {
		int[][] old = moved();
		boolean movable = true;
		int[] spinPoint = old[2];
		if(orientation == 0 || orientation == 2) { //if horizontal
			int[][] blocks = {{spinPoint[0]-1, spinPoint[1]+1},
					{spinPoint[0], spinPoint[1]+1},
					{spinPoint[0], spinPoint[1]},
					{spinPoint[0]+1, spinPoint[1]}};
			for(int[] a: blocks) {
				if(board.isOccupied(a)) movable = false;
			}
			for(int i = 0; i < 4 && movable; i++) {
				location[i][0] = blocks[i][0];
				location[i][1] = blocks[i][1];
			}
		}
		else { //if vertical
			int[][] blocks = {{spinPoint[0]-1, spinPoint[1]-1},
					{spinPoint[0]-1, spinPoint[1]},
					{spinPoint[0], spinPoint[1]},
					{spinPoint[0], spinPoint[1]+1}};
			for(int[] a: blocks) {
				if(board.isOccupied(a)) movable = false;
			}
			for(int i = 0; i < 4 && movable; i++) {
				location[i][0] = blocks[i][0];
				location[i][1] = blocks[i][1];
			}
		}
		if(movable) super.spin(); //change orientation if spun
	}

}
