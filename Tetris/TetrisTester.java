
public class TetrisTester {

	public static void main(String[] args) {
		Tetris game = new Tetris();
		GameBoard board = new GameBoard();
		
//		System.out.println(game);
//		test0(board);
		testDelete(board);
//		testNewBlocks(game);
//		testMove(game);
//		testSpin(game);
//		testBlockGenerator(game);

	}
	
	public static void test0(GameBoard game){
		game.setPixel(21, 3, 9);
		game.setPixel(21, 8, 9);
		game.setPixel(21, 9, 9);
		game.setPixel(21,4,1);
		game.setPixel(21,5,1);
		game.setPixel(21,6,1);
		game.setPixel(21,7,1);
		game.setPixel(21,0,2);
		game.setPixel(21,1,2);
		game.setPixel(21,2,2);
		game.setPixel(20,1,2);
		game.setPixel(20,5,3);
		game.setPixel(20,6,3);
		game.setPixel(19,5,3);
		game.setPixel(19,6,3);
		
		System.out.println(game);
		System.out.println(game.rowFull(game.getHeight()-1));
		System.out.println(game);
	}
	public static void testDelete(GameBoard game) {
		for(int i = 0; i < 10; i++) {
			game.setPixel(21,i,9);
		}
		for(int i = 0; i < 9; i++) {
			game.setPixel(20,i,9);
		}for(int i = 0; i < 10; i++) {
			game.setPixel(19,i,9);
		}
		System.out.println(game);
		game.deleteRow(21);
		game.deleteRow(20);
		System.out.println(game);
		
	}
	
	public static void testNewBlocks(Tetris game) {
		Tetromino lBlock = game.newLBlock();
		System.out.println(game);
		lBlock.moveLeft();
		lBlock.moveLeft();
		lBlock.moveLeft();
		lBlock.draw();
		System.out.println(game);
		game.clear();
	}
	public static void testMove(Tetris game) {
//		Termino block = game.newLBlock();
//		block.draw();
//		System.out.println(game);
//		block.softFall();
//		block.draw();
//		System.out.println(game);
//		game.setPixel(5, 6, 9);
//		System.out.println(game);
//		block.softFall();
//		block.draw();
//		System.out.println(game);
//		block.moveLeft();
//		block.draw();
//		System.out.println(game);
//		game.setPixel(4, 2, 9);
//		block.moveLeft();
//		block.draw();
//		System.out.println(game);
//		block.moveRight();
//		block.draw();
//		System.out.println(game);
//		game.setPixel(4, 8, 9);
//		block.moveRight();
//		block.draw();
//		System.out.println(game);
//		game.getGame().clear();
		Tetromino block2 = game.newSBlock();
		block2.draw();
		System.out.println(game);
		block2.moveLeft();
		block2.moveLeft();
		block2.moveLeft();
		block2.moveLeft();
		block2.draw();
		System.out.println(game);
		block2.moveRight();
		block2.moveRight();
		block2.moveRight();
		block2.moveRight();
		block2.moveRight();
		block2.draw();
		System.out.println(game);
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.draw();
		System.out.println(game);
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.draw();
		System.out.println(game);
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.draw();
		System.out.println(game);
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.softFall();
		block2.draw();
		System.out.println(game);
		game.getBoard().clear();
		
		Tetromino block3 = game.newJBlock();
		block3.draw();
		System.out.println(game);
		block3.hardFall();
		block3.draw();
		System.out.println(game);	
	}
	public static void testSpin(Tetris game) {
//		Termino iBlock = new IBlock(game);
//		iBlock.draw();
//		System.out.println(game);
//		iBlock.spin();
//		iBlock.draw();
//		System.out.println(game);
//		iBlock.softFall();
//		iBlock.softFall();
//		iBlock.draw();
//		System.out.println(game);
//		iBlock.spin();
//		iBlock.draw();
//		System.out.println(game);
//		iBlock.spin();
//		iBlock.draw();
//		System.out.println(game);
//		game.setBlock(1, 6, 9);
//		System.out.println(game);
//		iBlock.spin();
//		iBlock.draw();
//		System.out.println(game);
//		System.out.println(iBlock.getOrientation());
//		game.clear();
		
		Tetromino block = game.newZBlock();
		block.draw();
		System.out.println(game);
		block.spin();
		block.draw();
		System.out.println(game);
		block.spin();
		block.draw();
		System.out.println(game);
		block.spin();
		block.draw();
		System.out.println(game);
		block.spin();
		block.draw();
		System.out.println(game);
		System.out.println(block.getOrientation());
		block.spin();
		block.draw();
		System.out.println(game);
		block.spin();
		block.draw();
		System.out.println(game);
		block.spin();
		block.draw();
		System.out.println(game);
		block.spin();
		block.draw();
		System.out.println(game);
		System.out.println(block.getOrientation());
	}
	public static void testBlockGenerator(Tetris game) {
		Tetromino block = game.newTetromino();
//		block = game.newIBlock();
		System.out.println(game);
		System.out.println("block " + block.getBlockType());
		block.moveLeft();
		block.moveLeft();
		block.moveLeft();
		block.draw();
		System.out.println("block " + block.getBlockType());
		System.out.println(game);
		
	}
}
