package com.mygdx.game;
/**
 * Command interface for the dice roll and player move commands
 * 
 * Authors: @mhunter, @shennessy, @mbehenna, @pmishra
 */
public interface Command {
	
	/**
	 * This method performs the command for the diceRoll and movePlayer
	 * @return - the integer representing the number for the diceRoll or the tile number that the player should move to
	 */
	public int doCommand();
	
	public void undoCommand();
}
