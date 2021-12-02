package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


/**
 * The Player class tracks each player's points, current location, name and avatar.
 * @author @shenessy
 *
 */
public class Player {
	private String name;
	private  int totalPoints;
	private GameSquare location;
	private Texture avatar;
	
	
	public Player (String name) {
		this.name = name;
		GameSquare gameSquare = new GameSquare(1);
		this.location = gameSquare;
	}
	
	public GameSquare getLocation() {
		return this.location;
	}
	
	public void setLocation(GameSquare newLocation) {
		this.location = newLocation;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public int getTotalPoints() {
		return this.totalPoints;
	}
	
	public void setTotalPoints(int newTotalPoints) {
		this.totalPoints = newTotalPoints;
	}
	
	public Texture getAvatar() {
		return this.avatar;
	}
	
	/**
	 * Add points to the player's total points count.
	 * @param newPoints points being added to the player.
	 */
	public void addPoints(int newPoints) {
		this.totalPoints += newPoints;
	}
		
	/**
	 * Subtracts the inputed number of points from the player's total points.
	 * @param numPoints points being subtracted from the player
	 */
	public void removePoints(int numPoints) {
		this.totalPoints -= numPoints;

	}
	
	/**
 	* @return a string with player's name and total number of points.
 	*/
	public String toString() {
		return this.name + " has " + this.totalPoints + " points.";
	}

}
