package com.mygdx.game;

import java.util.ArrayList;

/**
 * Executes a player's move based on their dice roll.
 * Undoes a player's last move by returning them to their previous tile.
 * @author @pmishra, @shennessy
 *
 */
public class MovePlayerCommand implements Command {
	private final int LADDER_POINTS = 10;
	private final int SHOOT_POINTS = -10;
	private final int END_POINTS = 15;
	private Player player;
	private int diceRoll;
	private GameBoard board;
	private GameSquare previousSquare;
	private int earnedPoints;

	public MovePlayerCommand(Player player, int diceRoll, GameBoard board) {
		this.player = player;
		this.diceRoll = diceRoll;
		this.board = board;
	}
	
	/**
	 * Moves a player to a new location based on the value of their dice roll.
	 * Checks to see if the player has hit the 100th title. If so, they have completed the game.
	 * Also checks to see if player lands at the base of a ladder or top of a shoot and adjusts their location accordingly.
	 * @return int representing the tile number of the player's new location.
	 */
	public int doCommand() {

		GameSquare currentLocation = player.getLocation();
		int currentTile = currentLocation.getTileNumber();
		int newTile = currentTile + diceRoll;

		//Setting player location to the new location
		ArrayList<GameSquare> boardTiles = board.getGameSquares();
		
		if( newTile > 100 ) {
			GameSquare newLocation = boardTiles.get(100);
			player.setLocation(newLocation);
			player.addPoints(END_POINTS);
			return newLocation.getTileNumber();
		}

		GameSquare newLocation = boardTiles.get(newTile);
		player.setLocation(newLocation);
		previousSquare = currentLocation;

		if (newLocation.isLadder()) {
			
			int topOfLadderVal = newLocation.returnTopLadderVal();
			GameSquare topOfLadderTile = boardTiles.get(topOfLadderVal);
			player.setLocation(topOfLadderTile);
			player.addPoints(LADDER_POINTS);
			int newPoints = topOfLadderTile.getPointValue();
			player.addPoints(newPoints);
			earnedPoints += (LADDER_POINTS + newPoints);
			return newLocation.getTileNumber();
			
		}
		
		if (newLocation.isTopOfShoot()) {
			
			int bottomOfShootVal = newLocation.returnBottomShootVal();
			GameSquare bottomOfShootTile = boardTiles.get(bottomOfShootVal);
			player.setLocation(bottomOfShootTile);
			player.addPoints(SHOOT_POINTS);
			int newPoints = bottomOfShootTile.getPointValue();
			player.addPoints(newPoints);
			earnedPoints += (SHOOT_POINTS + newPoints);
			return newLocation.getTileNumber();
		}
		
		
		int newPoints = newLocation.getPointValue();
		player.addPoints(newPoints);
		earnedPoints += newPoints;
		return newLocation.getTileNumber();
	}
	
	/**
	 * To be implemented: will undo a player's last move.
	 */
	public void undoCommand() {
		player.setLocation(previousSquare);
		int pointsAfterUndo = player.getTotalPoints() - earnedPoints;
		player.setTotalPoints(pointsAfterUndo);
	}
	
	/*
	public static void main(String[] args) {
		
		Player player = new Player("human");
		GameSquare beforeMove = player.getLocation();
		System.out.println(beforeMove.getTileNumber());
		GameBoard board = new GameBoard();
		board.createBoard();
		MovePlayerCommand c = new MovePlayerCommand(player, 8, board);
		c.doCommand();
		GameSquare beforeUndo = player.getLocation();
		System.out.println(beforeUndo.getTileNumber());
		c.undoCommand();
		GameSquare afterUndo = player.getLocation();
		System.out.println(afterUndo.getTileNumber());
		
	} */


}
