package com.mygdx.game;

/**
 * the classic way of playing the game; the first player to get to tile 100 wins
 * 
 * Authors: @mhunter
 */

public class ClassicStrategy implements GameStrategy {
	
	/**
	 * checks to see if anyone is at tile 100
	 * @return 2 if the game was a tie, 1 if the human won, -1 if the computer won, and 0 if the game isn't over
	 */
	public int checkWinner(Player human, Player computer) {
		if( human.getLocation().getTileNumber() == 100 && computer.getLocation().getTileNumber() == 100 ) {
			return 2;
		} else if( human.getLocation().getTileNumber() == 100 ) {
			return 1;
		} else if (computer.getLocation().getTileNumber() == 100 ){
			return -1;
		}
		return 0;
	}
	

}
