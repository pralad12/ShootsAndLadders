import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.mygdx.game.ClassicStrategy;
import com.mygdx.game.GameBoard;
import com.mygdx.game.GameSquare;
import com.mygdx.game.GameStrategy;
import com.mygdx.game.MovePlayerCommand;
import com.mygdx.game.Player;
import com.mygdx.game.PointsStrategy;

/**
 * Tests to see if the check winner function is working for the classic strategy
 * @author @mhunter
 *
 */
public class CheckWinnerTest {

	/**
	 * creates two new players and sets one player to the final square, then checks to make
	 * sure checkWinner() accurately computes that a player is on the final square so the game
	 * is over and that the human won
	 */
	@Test
	public void classicStrategyTest() {
		Player human = new Player("human");
		GameSquare humanGameSquare = new GameSquare(100);
		human.setLocation(humanGameSquare);
		Player computer = new Player("computer");
		GameSquare computerGameSquare = new GameSquare(87);
		computer.setLocation(computerGameSquare);
		GameStrategy strategy = new ClassicStrategy();
		assertEquals(1, strategy.checkWinner(human, computer));
	}
	
	
	/**
	 * This tests the points strategy when the computer has more points than the human
	 * Computer should win; checkWinner method should return -1
	 */
	@Test 
	public void pointsStrategyCompTest() {
		Player human = new Player("Steve");
		Player computer = new Player("Bot");
		GameBoard board = new GameBoard();
		ArrayList<GameSquare> boardTiles = board.getGameSquares();
		board.createBoard();
		GameStrategy ptsStrategy = new PointsStrategy();
		human.setTotalPoints(55);
		computer.setTotalPoints(60);
		GameSquare hundredTile = boardTiles.get(100);
		human.setLocation(hundredTile);
		int winner = ptsStrategy.checkWinner(human, computer);
		assertEquals("Computer should win - return -1", -1, winner);
		
	}
	
	/**
	 * This tests the points strategy when the human has more points than the computer
	 * Human should win; checkWinner method should return 1
	 */
	@Test 
	public void pointsStrategyHumanTest() {
		Player human = new Player("Steve");
		Player computer = new Player("Bot");
		GameBoard board = new GameBoard();
		ArrayList<GameSquare> boardTiles = board.getGameSquares();
		board.createBoard();
		GameStrategy ptsStrategy = new PointsStrategy();
		human.setTotalPoints(87);
		computer.setTotalPoints(60);
		GameSquare hundredTile = boardTiles.get(100);
		human.setLocation(hundredTile);
		int winner = ptsStrategy.checkWinner(human, computer);
		assertEquals("Human should win - return 1", 1, winner);
	}
	
	/**
	 * This tests the points strategy when the number of points are tied 
	 * checkWinner method should return 2
	 */
	@Test 
	public void pointsStrategyTieTest() {
		Player human = new Player("Steve");
		Player computer = new Player("Bot");
		GameBoard board = new GameBoard();
		ArrayList<GameSquare> boardTiles = board.getGameSquares();
		board.createBoard();
		GameStrategy ptsStrategy = new PointsStrategy();
		human.setTotalPoints(70);
		computer.setTotalPoints(70);
		GameSquare hundredTile = boardTiles.get(100);
		human.setLocation(hundredTile);
		int winner = ptsStrategy.checkWinner(human, computer);
		assertEquals("Should be a tie - return 2", 2, winner);
	}
	
}
