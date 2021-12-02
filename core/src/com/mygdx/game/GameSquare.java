package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * GameSquare represents the individual tiles on the board as a GameSquare Object.
 * Authors: @mbehenna, @pmishra
 */
public class GameSquare {

	private int xPosition;
	private int yPosition;
	private int tileNumber;
	private int pointVal;
	private boolean isLadderBase = false;
	private boolean isTopOfShoot = false;
	private int topOfLadder;
	private int bottomOfShoot;
	private String squareImageFile;

	
	public GameSquare(int tileNumber) {
		this.tileNumber = tileNumber;
	}
	
	
	public int getXPosition() {
		return this.xPosition;
	}
	
	
	public int getYPosition() {
		return this.yPosition;
	}
	
	
	public int getPointValue() {
		return this.pointVal;
	}
	
	
	public int getTileNumber() {
		return this.tileNumber;
	}
	
	
	public boolean isLadder() {
		return this.isLadderBase;
	}
	
	
	public boolean isTopOfShoot() {
		return this.isTopOfShoot;
	}
	
	
	public int returnTopLadderVal() {
		return this.topOfLadder;
	}
	
	public int returnBottomShootVal() {
		return this.bottomOfShoot;
	}
	
	public void setXPosition(int xVal) {
		this.xPosition = xVal;
	}
	
	
	public void setYPosition(int yVal) {
		this.yPosition = yVal;
	}
	
	
	public void setPointValue(int pointVal) {
		this.pointVal = pointVal;
	}
	
	
	public void setTileNumber(int tileNum) {
		this.tileNumber = tileNum;
	}
	
	
	public void setLadder() {
		this.isLadderBase = true;
	}
	
	public void setTopOfShoot() {
		this.isTopOfShoot = true;
	}
	
	
	public void setTopLadderVal(int topLadderTile) {
		this.topOfLadder = topLadderTile;
	}
	
	public void setBottomShootVal(int bottomShootTile) {
		this.bottomOfShoot = bottomShootTile;
	}
	
	public void setGameSquareImageFile(String image) {
		this.squareImageFile = image;
	}
	
	public String getSquareImageFile() {
		return this.squareImageFile;
	}

}
