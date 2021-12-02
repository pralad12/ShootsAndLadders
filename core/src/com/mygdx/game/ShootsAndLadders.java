package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Shoots and Ladders runs the game, first by creating all of the objects that will appear on the screen,
 * then by rendering the game. Then, when the window is closed, it disposes of the objects
 * 
 * Authors: @mhunter, @shennessy, @mbehenna, @pmishra
 */
public class ShootsAndLadders extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture smileyImage;
	private Texture purpleSmileyImage;
	private Texture outlineImage;
	private Texture ladder1;
	private Texture ladder2;
	private Texture ladder3;
	private Texture ladder4;
	private Texture slide1;
	private Texture slide2;
	private Texture slide3;
	private Texture slide4;
	private Button diceButton;
	private Button classicStrategyButton;
	private Button pointsStrategyButton;
	private Button startButton;
	private Button doneButton;
	private ArrayList<GameSquare> gameBoard = new ArrayList<GameSquare>();
	private Stage stage;
	private Player human;
	private Player computer;
	private boolean humanTurn;
	private boolean clicked = false;
	private GameBoard salBoard;
	private BitmapFont bitmapFont;
	private BitmapFont errorMessages;
	private BitmapFont startMessage;
	private int lastDiceRoll = -1;
	private int lastComputerDiceRoll = -1;
	private GameStrategy strategy;
	private boolean gameplay;
	private boolean classicStrategyClicked;
	private boolean pointsStrategyClicked;
	private boolean nameDoneClicked;
	private boolean startClicked;
	private boolean nameErrorMessage;
	private boolean strategyErrorMessage;
	private boolean gameOver;
	private int addDataEntry;
	private int winner = 0;
	private boolean setUp;
	private String user;
	private TextField userName;

	private DatabaseManager databaseManager;
	private ImageButton undoButton;
	private boolean undoClicked = false;
	private Stack<Command> stack = new Stack<Command>();

	private String currentStrategy;
	private boolean rollingForFirst = true;
	private String leaderBoard;


	public final static int WIDTH = 800;
	public final static int HEIGHT = 480;
	public final static int GAME_BOARD_SIZE = 10; // the game board is 10 by 10

	/**
	 * Creates all of the objects that will go on the board, including their "textures" which are
	 * the images that will appear on the board
	 */
	@Override
	public void create () {
		//Initialize the gameBoard and populate it with the tiles
		salBoard = new GameBoard();
		gameBoard = salBoard.createBoard();
		//Set the stage for the buttons and board, and set the error message fields
		stage = new Stage();
		bitmapFont = new BitmapFont();
		errorMessages = new BitmapFont();
		startMessage = new BitmapFont();
		startMessage.getData().setScale(2);
		errorMessages.setColor(Color.RED);
		startMessage.setColor(Color.BLACK);
		gameOver = false;
		//boolean for beginning of the game to print the proper window
		setUp = false;
		//boolean to set strategy
		classicStrategyClicked = false;

		gameplay = false;
		addDataEntry = 0;

		//Input images for players, ladders, slides, and tiles
		smileyImage = new Texture(Gdx.files.internal("smiley.png"));
		purpleSmileyImage = new Texture(Gdx.files.internal("purpleSmiley.png"));
		outlineImage = new Texture(Gdx.files.internal("blackRectangle.jpeg"));

		ladder1 = new Texture(Gdx.files.internal("ladder1.png"));
		ladder2 = new Texture(Gdx.files.internal("ladder2.png"));
		ladder3 = new Texture(Gdx.files.internal("ladder3.png"));
		ladder4 = new Texture(Gdx.files.internal("ladder4.png"));

		slide1 = new Texture(Gdx.files.internal("Slide1.png"));
		slide2 = new Texture(Gdx.files.internal("Slide2.png"));
		slide3 = new Texture(Gdx.files.internal("Slide3.png"));
		slide4 = new Texture(Gdx.files.internal("Slide4.png"));
		purpleSmileyImage = new Texture(Gdx.files.internal("purpleSmiley.png"));

		//Set image and position for dice button and add it to the stage
		Texture diceImage = new Texture(Gdx.files.internal("rollDiceButton.jpg"));
		Texture diceDownImage = new Texture(Gdx.files.internal("rollDiceButtonDown.jpg"));
		Sprite diceSprite = new Sprite(diceImage);
		Sprite diceDownSprite = new Sprite(diceDownImage);
		SpriteDrawable dice = new SpriteDrawable(diceSprite);
		SpriteDrawable diceDown = new SpriteDrawable(diceDownSprite);
		diceButton = new ImageButton(dice, diceDown);
		diceButton.setPosition(540, 200);
		Gdx.input.setInputProcessor(stage);

		//Set image and position for undo button and add it to the stage
		Texture undoImage = new Texture(Gdx.files.internal("undoButton.png"));
		Texture undoDownImage = new Texture(Gdx.files.internal("undoButtonDown.png"));
		Sprite undoSprite = new Sprite(undoImage);
		Sprite undoDownSprite = new Sprite(undoDownImage);
		SpriteDrawable undo = new SpriteDrawable(undoSprite);
		SpriteDrawable undoDown = new SpriteDrawable(undoDownSprite);
		undoButton = new ImageButton(undo, undoDown);
		undoButton.setPosition(680, 20);
		stage.addActor(undoButton);
		Gdx.input.setInputProcessor(stage);

		//Set the image and position for the classicStrategy button and add it to the stage
		Texture classicStrategyImage = new Texture(Gdx.files.internal("classicStrategy.png"));
		Texture classicStrategyDownImage = new Texture(Gdx.files.internal("classicStrategyDown.png"));
		Sprite classicStrategySprite = new Sprite(classicStrategyImage);
		Sprite classicStrategyDownSprite = new Sprite(classicStrategyDownImage);
		SpriteDrawable classicStrategySpriteDrawable = new SpriteDrawable(classicStrategySprite);
		SpriteDrawable classicStrategyDownSpriteDrawable = new SpriteDrawable(classicStrategyDownSprite);
		classicStrategyButton = new ImageButton(classicStrategySpriteDrawable, classicStrategyDownSpriteDrawable);
		classicStrategyButton.setPosition(170, 150);

		//Set the image and position for the pointsStrategy button and add it to the stage
		Texture pointsStrategyImage = new Texture(Gdx.files.internal("pointsStrategy.png"));
		Texture pointsStrategyDownImage = new Texture(Gdx.files.internal("pointsStrategyDown.png"));
		Sprite pointsStrategySprite = new Sprite(pointsStrategyImage);
		Sprite pointsStrategyDownSprite = new Sprite(pointsStrategyDownImage);
		SpriteDrawable pointsStrategySpriteDrawable = new SpriteDrawable(pointsStrategySprite);
		SpriteDrawable pointsStrategyDownSpriteDrawable = new SpriteDrawable(pointsStrategyDownSprite);
		pointsStrategyButton = new ImageButton(pointsStrategySpriteDrawable, pointsStrategyDownSpriteDrawable);
		pointsStrategyButton.setPosition(420, 150);

		//Set the image and position for the done button and add it to the stage
		Texture doneImage = new Texture(Gdx.files.internal("doneButton.png"));
		Texture doneDownImage = new Texture(Gdx.files.internal("doneDownButton.png"));
		Sprite doneSprite = new Sprite(doneImage);
		Sprite doneDownSprite = new Sprite(doneDownImage);
		SpriteDrawable doneSpriteDrawable = new SpriteDrawable(doneSprite);
		SpriteDrawable doneDownSpriteDrawable = new SpriteDrawable(doneDownSprite);
		doneButton = new ImageButton(doneSpriteDrawable, doneDownSpriteDrawable);
		doneButton.setPosition(490, 300);

		//Set the image and position for the start button and add it to the stage
		Texture startImage = new Texture(Gdx.files.internal("startButton.png"));
		Texture startDownImage = new Texture(Gdx.files.internal("startDownButton.png"));
		Sprite startSprite = new Sprite(startImage);
		Sprite startDownSprite = new Sprite(startDownImage);
		SpriteDrawable startSpriteDrawable = new SpriteDrawable(startSprite);
		SpriteDrawable startDownSpriteDrawable = new SpriteDrawable(startDownSprite);
		startButton = new ImageButton(startSpriteDrawable, startDownSpriteDrawable);
		startButton.setPosition(320, 30);

		//Default libGDX skin field needed
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		userName = new TextField("Please enter your name: ", skin);
		userName.setWidth(250);
		userName.setPosition(350, 300);

		//human and computer player
		human = new Player("Human");
		computer = new Player("Computer");

		//Class that keeps track of the database contents
		databaseManager = new DatabaseManager();

		batch = new SpriteBatch();
	}

	/**
	 * renders the game, and essentially works as a while loop. We tried to put a lot of this code
	 * in a separate playGame() class, but the program will not run when there is a while loop that is still running,
	 * since this class also works as a while loop. Draws all of the objects and also runs the game, moving forward
	 * the player and running the commands. At the end, uses the strategy to check for the winner. 
	 */
	@Override
	public void render () {
		//Default libGDX command to clear screenUtils
		ScreenUtils.clear(240/255f, 234/255f, 214/255f, 1);

		//Begins the SpriteBatch in order to draw all contents
		batch.begin();

		ClassicStrategy classic = new ClassicStrategy();
		PointsStrategy pointsStrategy = new PointsStrategy();

		/* 
		 * SetUp only happens at the beginning of the game for the main window. Therefore,
		 * if the setup is false, the startup window is shown  
		 */
		if( setUp == false ) {
			stage.addActor(classicStrategyButton);
			stage.addActor(pointsStrategyButton);
			stage.addActor(doneButton);
			stage.addActor(startButton);
			stage.addActor(userName);

			//error message if name not selected
			if(nameErrorMessage) {
				errorMessages.draw(batch, "Please input your name, then press DONE.", 240, 380);
			}

			//error message if strategy not selected
			if(strategyErrorMessage) {
				errorMessages.draw(batch, "You must choose a strategy to start: ", 240, 380);
			}
			//draw and set positions for name input and directions to start the game
			userName.setPosition(200, 300);
			userName.draw(batch, 1);
			startMessage.draw(batch, "To Start, Enter Your Name and Press Done", 120, 450);
			startMessage.draw(batch, "Then, Choose a Strategy. Then Press Start!", 118, 420);
			classicStrategyButton.draw(batch, 1);

			/* 
			 * This reads the mouseClick input for the classicStrategy button and sets the strategy
			 * If the button is clicked, the boolean "classicStrategyClicked" that keeps track of whether it was 
			 * clicked is set to true 
			 * */ 
			classicStrategyButton.addListener( new ClickListener() {
				@Override 
				public void clicked(InputEvent event, float x, float y) {
					if(classicStrategyClicked == false) {
						classicStrategyClicked = true;
					}
				} } );
			pointsStrategyButton.draw(batch, 1);

			/*
			 * Adds the listener for for the pointsStrategy button and sets
			 * its boolean to true if the button was pressed
			 */
			pointsStrategyButton.addListener( new ClickListener() {
				@Override 
				public void clicked(InputEvent event, float x, float y) {
					if(pointsStrategyClicked == false) {
						pointsStrategyClicked = true;
					}
				} } );
			doneButton.draw(batch, 1);
			/*
			 * Adds the listener for the done button and sets its boolean to true
			 * if button was pressed
			 */
			doneButton.addListener( new ClickListener() {
				@Override 
				public void clicked(InputEvent event, float x, float y) {
					if(nameDoneClicked == false) {
						nameDoneClicked = true;
					}
				} } );
			startButton.draw(batch, 1);
			startButton.addListener( new ClickListener() {
				@Override 
				public void clicked(InputEvent event, float x, float y) {
					if(startClicked == false) {
						startClicked = true;
					}
				} } );

			//Handle the error messages
			if( startClicked == true) {
				if( nameDoneClicked == true ) {
					user = userName.getText();
					if(checkIfUserInputted()) {
						nameErrorMessage = false;
						human.setName(user);
						if(classicStrategyClicked == true ) {
							strategyErrorMessage = false;
							strategy = new ClassicStrategy();
							setUp = true;
							gameplay = true;
						} else if(pointsStrategyClicked == true ) {
							strategyErrorMessage = false;
							strategy = new PointsStrategy();
							setUp = true;
							gameplay = true;
						} else {
							strategyErrorMessage = true;
						}
					} else {
						nameErrorMessage = true;
						nameDoneClicked = false;
					}
				} else {
					nameErrorMessage = true;
				}
			}


		}
		//rest of the functionalities if startup is true
		else if( gameplay == true ) {
			stage.addActor(diceButton);
			//Add the gameSquare, dice button, and draw the squares
			for(GameSquare square: gameBoard) {
				Texture gameSquareImage = new Texture(Gdx.files.internal(square.getSquareImageFile()));
				batch.draw(gameSquareImage, square.getXPosition(), square.getYPosition());
				bitmapFont.setColor(Color.BLACK);
				bitmapFont.draw(batch, ""+square.getTileNumber(), square.getXPosition()+27, square.getYPosition()+15);
			}

			//draw and render all the game contents
			//rendering 4 game slides
			batch.draw(slide1, 300, 45); //slide from 47 to 30
			batch.draw(slide2, 200, 300); //slide from 94 to 66
			batch.draw(slide3, 313, 320); //slide from 88 to 72
			batch.draw(slide4, 29, 31); //slide from 95 to 17

			//render 4 game ladders
			batch.draw(ladder1, -8, 290); //ladder from 63 to 78
			batch.draw(ladder3, 161, 4); // ladder from 6 to 73
			batch.draw(ladder2, -7, 78); //ladder from 22 to 60 
			batch.draw(ladder4, 100, 109); //ladder from 43 to 66
			batch.draw(smileyImage, human.getLocation().getXPosition(), human.getLocation().getYPosition() );
			batch.draw(purpleSmileyImage, computer.getLocation().getXPosition(), computer.getLocation().getYPosition() + 24);
			batch.draw(outlineImage, 538, 198);
			diceButton.draw(batch, 1);
			undoButton.draw(batch, 1);
			bitmapFont.setColor(Color.FOREST);
			bitmapFont.draw(batch, "Last Dice Roll: " + lastDiceRoll, 580, 380);
			bitmapFont.draw(batch, "Last Dice Roll by Computer: " + lastComputerDiceRoll, 540, 400);


			batch.draw(smileyImage, 490, 75);
			batch.draw(purpleSmileyImage, 490, 50);

			if( strategy.getClass() == classic.getClass() ) {
				bitmapFont.draw(batch, human.getName(), 520, 90);
				bitmapFont.draw(batch, computer.getName(), 520, 65);
			} else {
				bitmapFont.draw(batch, "" + human.toString(), 520, 90);
				bitmapFont.draw(batch, "" + computer.toString(), 520, 65);
			}

			//Prints which strategy is being used
			if(strategy.getClass() == ClassicStrategy.class) {
				currentStrategy = "Classic Strategy";
			}
			else {
				currentStrategy = "Points Strategy";
			}

			bitmapFont.draw(batch, "Current Strategy: "+currentStrategy, 525, 430);


			//set human turn
			humanTurn = true;


			//if human moves first then set humanTurn to true and false otherwise

			decideFirstMover(human, computer);


			//String rollsToPrint = decideFirstMover(human, computer);
			//bitmapFont.draw(batch, rollsToPrint, 600, 170);



			//Add the listener for the undo button and sets the boolean accordingly
			undoButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (undoClicked == false && gameOver == false) {
						undoClicked = true;
					}
				}
			} );

			//If the undo button is clicked and it is not game over, run the undo method by popping the stack
			if (winner == 0 && humanTurn) {
				if (undoClicked == true) {
					if (stack.size() >= 4) {

						undoClicked = false;
						Command undoPlayer = stack.pop();
						Command undoComputer = stack.pop();
						Command undoPlayerDice = stack.pop();
						Command undoComputerDice = stack.pop();
						undoPlayer.undoCommand();
						undoComputer.undoCommand();
						undoPlayerDice.undoCommand();
						undoComputerDice.undoCommand();
						lastDiceRoll = 0;
						lastComputerDiceRoll = 0;
	
					}	
				}
			} 

			//Add the listener for the dice button
			diceButton.addListener( new ClickListener() {
				@Override 
				public void clicked(InputEvent event, float x, float y) {
					if(clicked == false && gameOver == false) {
						clicked = true;
					}
				} } );

			//run the functionalities for the human if there is no winner yet and push the commands onto the stack
			if( humanTurn && winner == 0 ) {
				if( clicked == true ) {
					int diceRoll = 0;
					RollDiceCommand rollDice = new RollDiceCommand(human);
					diceRoll = rollDice.doCommand();
					MovePlayerCommand movePlayer = new MovePlayerCommand(human, diceRoll, salBoard);
					movePlayer.doCommand();
					stack.push(movePlayer);
					stack.push(rollDice);
					lastDiceRoll = diceRoll;
					humanTurn = false;
					clicked = false;
					winner = strategy.checkWinner(human, computer);
				}
			}

			//run the functionalities for the computer if there is no winner yet and push the commands onto the stack
			if( !humanTurn && winner == 0 ) {
				int computerDiceRoll;
				RollDiceCommand rollDice = new RollDiceCommand(computer);
				computerDiceRoll = rollDice.doCommand();
				MovePlayerCommand movePlayer = new MovePlayerCommand(computer, computerDiceRoll, salBoard);
				movePlayer.doCommand();
				stack.push(movePlayer);
				stack.push(rollDice);
				lastComputerDiceRoll = computerDiceRoll;
				humanTurn = true;
				clicked = false;
				winner = strategy.checkWinner(human, computer);
			}

			//check the winner based on the strategy and print out the correct message
			winner = strategy.checkWinner(human, computer);
			if( winner == -1 ) {
				bitmapFont.draw(batch, "The Winner is the Computer!", 545, 470);
				gameOver = true;
				gameplay = false;
				addDataEntry++;
			} else if ( winner == 1 ) {
				bitmapFont.draw(batch, "The Winner is " + human.getName() + "!", 550, 470);
				gameOver = true;
				gameplay = false;
				addDataEntry++;
			} else if ( winner == 2 ) {
				bitmapFont.draw(batch, "The Game Was a Tie!", 555, 470);
				gameOver = true;
				gameplay = false;
				addDataEntry++;
			}


			/**
			 * If the current strategy is the Point Strategy and the game is over then we add the current human player
			 * and their final point total to the database. 
			 * Then the sortRecord is called in order to return a string representing the current top 10 leaderboard.
			 */
			if(addDataEntry==1 && strategy.getClass() == PointsStrategy.class) {
				databaseManager.addEntry(human.getName(), human.getTotalPoints());
				addDataEntry++;
				leaderBoard = databaseManager.sortRecords();

			}

			/*
			 * If the strategy is the classic strategy, we don't need to print the 
			 * functionalities for the points strategy
			 */
		} else {
			if( strategy.getClass() == ClassicStrategy.class ) {
				if( winner == -1 ) {
					startMessage.draw(batch, "The Winner is the Computer!", 220, 280);
				} else if ( winner == 1 ) {
					startMessage.draw(batch, "The Winner is " + human.getName() + "!", 220, 280);
				} else if ( winner == 2 ) {
					startMessage.draw(batch, "The Game Was a Tie!", 220, 280);
				}
			} else if ( strategy.getClass() == PointsStrategy.class ) {
				bitmapFont.draw(batch, "Points Scored:", 350, 350);
				bitmapFont.draw(batch, human.getName() + " scored " + human.getTotalPoints() + " points.", 330, 330);
				bitmapFont.draw(batch, "The Computer scored " + computer.getTotalPoints() + " points.", 300, 310);
				
				if( winner == -1 ) {
					bitmapFont.draw(batch, "The Winner is the Computer!", 300, 450);
				} else if ( winner == 1 ) {
					bitmapFont.draw(batch, "The Winner is " + human.getName() + "!", 300, 450);
				} else if ( winner == 2 ) {
					bitmapFont.draw(batch, "The Game Was a Tie!", 300, 450);
				}
				bitmapFont.draw(batch, leaderBoard, 300, 250);
			}

		}
		//end the sprite batch
		batch.end();
	}


	/**
	 * when the window closes, disposes the objects
	 */
	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();
		bitmapFont.dispose();
	}
	/**
	 * Decides who will move first in the game - both players roll a die, the player with the highest roll goes first
	 * @param human - human player
	 * @param computer - computer player 
	 * @throws InterruptedException 
	 */
	private void decideFirstMover(Player human, Player computer) {

		if (rollingForFirst) {
			Random rand = new Random();
			int humanRoll = (rand.nextInt(6)+1) + (rand.nextInt(6)+1);
			int computerRoll = (rand.nextInt(6)+1) + (rand.nextInt(6)+1);
			System.out.println("Both players are rolling to decide who goes first: ");
			System.out.println(human.getName()+" rolled: "+humanRoll);
			System.out.println(computer.getName()+" rolled: "+computerRoll);
			if(humanRoll > computerRoll) {
				humanTurn = true;
				System.out.println(human.getName()+" rolled the higher number and will go first");
			}
			if(humanRoll < computerRoll) {
				humanTurn = false;
				System.out.println(computer.getName()+" rolled the higher number and will go first!");
			}
			if(humanRoll == computerRoll) {
				System.out.println("Both of the players rolled the same number! Rolling again...");
				decideFirstMover(human, computer);
			}
			rollingForFirst = false;
		}
	}



	/**
	 * Method that checks to see if name is inputed correctly. If the user typed in their name after the prompt,
	 * strips the prompt from the name
	 * @return a boolean true if the name has been inputed, and false if not 
	 */
	private boolean checkIfUserInputted() {
		if( user.startsWith("Please enter your name: ")) {
			user = user.substring(24);
			return true;
		} else if( user.startsWith("Please enter your name:")) {
			user = user.substring(23);
			return true;
		}
		if( user.equals("Please enter your name: ") || user.equals("Please enter your name:")) {
			return false;
		}else {
			return true;
		}
	}

	//Stops the default libGDX error from printing
	public static void disableWarning() {
		System.err.close();
	}


}