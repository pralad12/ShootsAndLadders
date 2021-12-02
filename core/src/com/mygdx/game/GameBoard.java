package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;

/**
 * Creates the game board as an array list of game squares. Adds the the ladders and shoots to the proper 
 * game square tiles. Adds colors to tiles - colors are just image files and this class adds the name of 
 * the image file that the tile will use for its color
 * Creates the Shoots and Ladders board by placing game squares, shoots and ladders.
 * @author @mbehenna, @pmishra
 *
 */
public class GameBoard {
	//private int BOARD_SIZE = 100;
	private ArrayList<GameSquare> Board = new ArrayList<GameSquare>();
	private int boardHeight = ShootsAndLadders.HEIGHT;
	private int boardWidth = ShootsAndLadders.WIDTH;
	private int boardSize = ShootsAndLadders.GAME_BOARD_SIZE;
	private String greenSquare = "greenSquare.png";
	private String yellowSquare = "yellowSquare.png";
	private String purpleSquare = "purpleSquare.png";
	private final int GREEN_VALUE = 2;
	private final int YELLOW_VALUE = 0;
	private final int PURPLE_VALUE = 4;


	/**
	 * Creates the shoots and ladders game board
	 * @return returns an array list of game squares that represent the game board 
	 */
	public ArrayList<GameSquare> createBoard(){
		instantiateBoard();
		setLadders();
		setShoots();
		setColors();
		return Board;
	}

	/**
	 * Adds the game squares, the location of the game squares, and their tile number 
	 */
	private void instantiateBoard() {
		int xPosition = 0;
		int yPosition = 0;
		int rowNum = 1;
		int size = boardHeight/boardSize;
		GameSquare tileZero = new GameSquare(0);
		Board.add(0, tileZero);

		for(int i=1; i< (boardSize*boardSize)+1 ; i++) {
			GameSquare currentSquare = new GameSquare(i);
			currentSquare.setXPosition(xPosition);
			currentSquare.setYPosition(yPosition);

			//for even rows we set tiles from left to right

			if (rowNum % 2 == 0) {
				if (i % boardSize == 0) {
					xPosition = xPosition;
				}
				else {
					xPosition -= size;
				}
			}
			else {
				if (i % boardSize == 0) {
					xPosition = xPosition;
				}
				else {
					xPosition += size;
				}
			}
			if(i % boardSize == 0) {
				yPosition += size;
				rowNum++;
			}
			Board.add(currentSquare);
		}

	}

	/**
	 * Sets the squares of where the ladders are going to be 
	 */
	private void setLadders () {
		for(int i=1; i<Board.size(); i++) {
			GameSquare currentSquare = Board.get(i);
			if(currentSquare.getTileNumber() == 6) {
				Board.get(i).setLadder();
				Board.get(i).setTopLadderVal(73); 
			}
			if(currentSquare.getTileNumber() == 22) {
				Board.get(i).setLadder();
				Board.get(i).setTopLadderVal(60);
			}
			if(currentSquare.getTileNumber() == 43) {
				Board.get(i).setLadder();
				Board.get(i).setTopLadderVal(66);
			}
			if(currentSquare.getTileNumber() == 62) {
				Board.get(i).setLadder();
				Board.get(i).setTopLadderVal(79);
			}
		}
	}

	/**
	 * Sets the squares of where the chutes are going to be 
	 */
	private void setShoots() {
		for(int i=1; i<Board.size(); i++) {
			GameSquare currentSquare = Board.get(i);
			
			if(currentSquare.getTileNumber() == 95) {
				Board.get(i).setTopOfShoot();
				Board.get(i).setBottomShootVal(17);
			}
			if(currentSquare.getTileNumber() == 47) {
				Board.get(i).setTopOfShoot();
				Board.get(i).setBottomShootVal(30);
			}
			if(currentSquare.getTileNumber() == 94) {
				Board.get(i).setTopOfShoot();
				Board.get(i).setBottomShootVal(66);
			}
			if(currentSquare.getTileNumber() == 88) {
				Board.get(i).setTopOfShoot();
				Board.get(i).setBottomShootVal(72);

			}
		}
	}
	/**
	 * Sets the appropriate color to the appropriate square - the colors are just an image with that color 
	 */
	private void setColors() {
		for(int i=0; i<Board.size(); i++) {
			Board.get(i).setGameSquareImageFile(greenSquare);
			Board.get(i).setPointValue(GREEN_VALUE);
		}
		for(int i=1; i<Board.size(); i++) {
			if(i%2 == 0 || i%12 == 0) {
				Board.get(i).setGameSquareImageFile(yellowSquare);
				Board.get(i).setPointValue(YELLOW_VALUE);
			}
			if(i%5 ==0 || i%9 == 0) {
				Board.get(i).setGameSquareImageFile(purpleSquare);
				Board.get(i).setPointValue(PURPLE_VALUE);
				
			}
		}

	}

	/**
	 * gets the created game board - an array list of game squares 
	 * @return ArrayList<GameSquare> - the array list of game squares that make up the game board
	 */
	public ArrayList<GameSquare> getGameSquares() {
		return this.Board;
	}
}
