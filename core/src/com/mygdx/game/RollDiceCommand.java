package com.mygdx.game;
import java.util.Random;

/**
 * Command class for rolling dice. Implements both roll dice and undo dice roll methods.
 * Tracks to see id doubles were rolled.
 * @author mHunter
 *
 */
public class RollDiceCommand implements Command{
	
	private int diceRoll;
	private Player player;
	private boolean rolledDoubles = false;
	
	public RollDiceCommand(Player player) {
		this.player = player;
	}

	
	/**
	 * Rolls dice and returns the number of spaces the player gets to move based on that dice roll.
	 * @return int representing the dice roll value
	 */
	@Override
	public int doCommand() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int diceOneRoll = rand.nextInt(6)+1;
		int diceTwoRoll = rand.nextInt(6)+1;
		diceRoll = diceOneRoll + diceTwoRoll;
		if( diceOneRoll == diceTwoRoll ) {
			if (player != null) {
			player.addPoints(2);
			rolledDoubles = true;
			}
		}
		return diceRoll;
	}
	
	

	/**
	 * Undoes any points rewarded for a dice roll
	 * Checks to see if dice was a double and if so subtracts the points rewarded for the roll
	 */
	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
		if( rolledDoubles == true ) {
			player.removePoints(2);
		}
	}

}
