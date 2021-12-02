import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mygdx.game.Player;
import com.mygdx.game.RollDiceCommand;

/**
 * Tests to make sure the dice roll function is working
 * @author @mhunter
 *
 */
public class DiceRollTest {
	
	/**
	 * just checks and makes sure the rollDiceCommand.doCommand() is returning an int that is a viable
	 * dice roll int
	 */
	@Test
	public void test() {
		Player player = new Player("human");
		RollDiceCommand rollDiceCommand = new RollDiceCommand(player);
		int diceRoll = rollDiceCommand.doCommand();
		assert(diceRoll == 2 || diceRoll == 3 || diceRoll == 4 || diceRoll == 5 || diceRoll == 6 || diceRoll == 7 ||
				 diceRoll == 8 || diceRoll == 9 || diceRoll == 10 || diceRoll ==11 || diceRoll == 12  );
	}

	
}
