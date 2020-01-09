/*
 * the O termino. has blockType 4
 * XX
 * XX
 */
public class OBlock extends Tetromino {

	public OBlock(GameBoard game) {
		super(game);
		int mid = game.getWidth() / 2;
		location[0][0] = 0;
		location[0][1] = mid;
		
		location[1][0] = 0;
		location[1][1] = mid - 1;
		
		location[2][0] = 1;
		location[2][1] = mid;
		
		location[3][0] = 1;
		location[3][1] = mid -1;
		
		softFall();
		softFall();
		blockType = 4;
	}
	
	//OBlocks don't spin
	
	
}
