package com.mygdx.game;
/**
 * Second strategy to be implemented during 3rd iteration.
 * Winner will be based on highest accrued point total rather than speed.
 *
 */
public class PointsStrategy implements GameStrategy {

	/**
	 * checks to see who won based on point strategy - player with the most points wins
	 * @return 2 if the game was a tie, 1 if the human won, -1 if the computer won, and 0 if the game isn't over
	 */
	@Override
	public int checkWinner(Player human, Player computer) {

		// TODO Auto-generated method stub
		int humanPoints = human.getTotalPoints();
		int computerPoints = computer.getTotalPoints();
		boolean gameFinished = playerAtEnd(human, computer);
		if(humanPoints > computerPoints && gameFinished) {
			return 1;
		}
		if(humanPoints < computerPoints && gameFinished) {
			return -1;
		}
		if(humanPoints == computerPoints && gameFinished) {
			return 2;
		}
		return 0;
	}
	/**
	 * helper method that determines if a player is at the of the board 
	 * @param human - the human player 
	 * @param computer - the computer player 
	 * @return true if a player is at the end of the board and false otherwise
	 */
	private boolean playerAtEnd(Player human, Player computer) {
		if(human.getLocation().getTileNumber() ==100) {
			return true;
		}
		if(computer.getLocation().getTileNumber() == 100) {
			return true;
		}
		else {
			return false;
		}
	}

}
