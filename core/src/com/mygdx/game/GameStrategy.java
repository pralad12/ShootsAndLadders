package com.mygdx.game;

/**
 * Game Strategy interface that requires all game strategies to check which of two given players is the winner.
 * Authors: @shennessy
 *
 */
public interface GameStrategy {
	
	/**
	 * 
	 * @param human game player
	 * @param computer game player
	 * @return 2 if the game was a tie, 1 if the human won, -1 if the computer won, and 0 if the game isn't over
	 */
	public int checkWinner(Player human, Player computer);
}
