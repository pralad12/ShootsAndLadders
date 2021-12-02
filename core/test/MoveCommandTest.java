import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.mygdx.game.ClassicStrategy;
import com.mygdx.game.GameBoard;
import com.mygdx.game.GameSquare;
import com.mygdx.game.GameStrategy;
import com.mygdx.game.MovePlayerCommand;
import com.mygdx.game.Player;

/**
 * Tests to make sure the move command function is actually changing the position of the player
 * @author @pmishra
 *
 */
public class MoveCommandTest {

	/**
	 * creates a new player and a new gameboard, initializes the game board, and tells the player
	 * to move 6 spaces across the game board. Then checks to make sure the player ends at the correct
	 * position (7, because the player starts on square 1)
	 */
	@Test
	public void testDoCommand() {
		
		Player human = new Player("human");
		GameBoard gameboard = new GameBoard();
		gameboard.createBoard();
		int diceRoll = 6;
		MovePlayerCommand movePlayer = new MovePlayerCommand(human, diceRoll, gameboard);
		assertEquals(7, movePlayer.doCommand()); // should be 7 because starts at 1
		
	}

	/*
	 * This tests that undoing the location works properly by comparing the initial tile the player was at 
	 * and the tile after the undo command has run. The player should end up at the same location as it 
	 * started on
	 */
	@Test
	public void testUndoLocation() {
		
		Player player = new Player("human");
		GameBoard board = new GameBoard();
		int initialTile = player.getLocation().getTileNumber();
		board.createBoard();
		MovePlayerCommand command = new MovePlayerCommand(player, 2, board);
		command.doCommand();
		//GameSquare beforeUndo = player.getLocation();
		command.undoCommand();
		GameSquare afterUndo = player.getLocation();
		assertEquals(initialTile, afterUndo.getTileNumber());
		
	}
	
	/*
	 * This tests if the undo works for reversing the player's points by comparing the initial 
	 * points of the player with the points after the undo is executed. The player gains points
	 * through the do command and loses through the undo command
	 */
	@Test
	public void testUndoPoints() {
		
		Player player = new Player("human");
		GameBoard board = new GameBoard();
		int initialPoints = player.getTotalPoints();
		board.createBoard();
		MovePlayerCommand command = new MovePlayerCommand(player, 2, board);
		command.doCommand();
		//GameSquare beforeUndo = player.getLocation();
		command.undoCommand();
		int afterUndo = player.getTotalPoints();
		assertEquals(initialPoints, afterUndo);
		
	}
	
	/*
	 * This tests if the undo command works when the player goes up a ladder
	 */
	@Test
	public void testUndoLadder() {
		
		Player player = new Player("human");
		GameBoard board = new GameBoard();
		int initialPoints = player.getTotalPoints();
		board.createBoard();
		MovePlayerCommand command = new MovePlayerCommand(player, 42, board);
		command.doCommand();
	//	System.out.println(player.getTotalPoints());
		//undo
		command.undoCommand();
		int afterUndo = player.getTotalPoints();
		assertEquals(initialPoints, afterUndo);
		
	}
	
	/*
	 * This tests if the undo command works when the player goes down a shoot
	 */
	@Test
	public void testUndoShoot() {
		
		Player player = new Player("human");
		GameBoard board = new GameBoard();
		int initialPoints = player.getTotalPoints();
		board.createBoard();
		MovePlayerCommand command = new MovePlayerCommand(player, 94, board);
		command.doCommand();
		System.out.println(player.getTotalPoints());
		//GameSquare beforeUndo = player.getLocation();
		command.undoCommand();
		int afterUndo = player.getTotalPoints();
		assertEquals(initialPoints, afterUndo);
		
	}
	
	/*
	 * This tests if the green tile awards the player 2 points
	 */
	@Test
	public void testGreenTilePoints() {
		Player human = new Player("Steve");
		GameBoard board = new GameBoard();
		board.createBoard();
		MovePlayerCommand moveToTile = new MovePlayerCommand(human, 2, board);
		moveToTile.doCommand();
		assertEquals(2, human.getTotalPoints());
		
	} 
	
	/*
	 * This tests if the blue tile awards the player 4 points
	 */
	@Test
	public void testBlueTilePoints() {
		Player human = new Player("Steve");
		GameBoard board = new GameBoard();
		board.createBoard();
		MovePlayerCommand moveToTile = new MovePlayerCommand(human, 4, board);
		moveToTile.doCommand();
		assertEquals(4, human.getTotalPoints());
		
	} 
	
	/*
	 * This tests if the yellow tile does not award the player any points
	 */
	@Test
	public void testYellowTilePoints() {
		Player human = new Player("Steve");
		GameBoard board = new GameBoard();
		board.createBoard();
		MovePlayerCommand moveToTile = new MovePlayerCommand(human, 1, board);
		moveToTile.doCommand();
		assertEquals(0, human.getTotalPoints());
		
	} 
	
	/*
	 * This tests if going up a ladder awards the player 10 points AND if it adds the points for the 
	 * tile it lands on after climbing the ladder
	 */
	@Test
	public void testLadderPoints() {
		Player human = new Player("Steve");
		GameBoard board = new GameBoard();
		board.createBoard();
		MovePlayerCommand moveToTile = new MovePlayerCommand(human, 5, board);
		moveToTile.doCommand();
		assertEquals(12, human.getTotalPoints());
	}
	
	/*
	 * This tests if going down a shoot subtracts 10 points from the player AND adds the 
	 * points for the tile it lands on after going down the shoot
	 */
	@Test
	public void testShootsPoints() {
		Player human = new Player("Steve");
		GameBoard board = new GameBoard();
		board.createBoard();
		MovePlayerCommand moveToTile = new MovePlayerCommand(human, 46, board);
		moveToTile.doCommand();
		assertEquals(-6, human.getTotalPoints());
	}
	
	
}