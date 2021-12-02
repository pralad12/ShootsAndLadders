package com.mygdx.game;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * MySQL Database class.
 * For each round adds human player and their score to the database.
 * 
 * @author shennessy and @mbehenna
 *
 */
public class DatabaseManager {
	

	public static final String PORT_NUMBER = "8889";
	private final int LEADERBOARD_SIZE = 11;
	private String leaderBoard = "Leaderboard:\n";

	public DatabaseManager() {
		createDatabase();
		createPlayerScoreTable();
	}
	
	/**
	 * Creates a local database that stores all non-computer player scores if the 
	 * database does not already exist on local machine.
	 */
	public void createDatabase() {
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/?serverTimezone=UTC", "root", "root");
				Statement statement = conn.createStatement();
				
				) {
			String newDatabase = "create database if not exists ShootsAndLadders";
			statement.execute(newDatabase);
			
			
		} catch(SQLException ex) {
				ex.printStackTrace();
			}
		

	}
	/**
	 * Creates a table that stores name and total points for each non-computer player, if the table does not already exists. 
	 * Primary key is an entry id since players can have multiple entries and point totals can also be the same.
	 */
	public void createPlayerScoreTable() {
		try (
				Connection tableConn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/ShootsAndLadders?user=root&password=root&serverTimezone=UTC");
				Statement tableStatement = tableConn.createStatement();
				
				) {
			String newPlayerScoreTable = "create table if not exists playerScores ( " +
				"entryID MEDIUMINT NOT NULL AUTO_INCREMENT, " +
				"playerName varchar(50), " +
				"points int, " +
				"primary key (entryID));";
			tableStatement.execute(newPlayerScoreTable);
			
			
		} catch(SQLException ex) {
				ex.printStackTrace();
			}
	}
	
	/**
	 * Given a name and a final score this method adds an entry to the playerScores table in the database. 
	 * The entry id is a auto generated integer that acts as a primary key.
	 * @param name - player name
	 * @param score - total points scored by the player
	 */
	public void addEntry(String name, int score) {
		try (
				Connection entryConn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/ShootsAndLadders?user=root&password=root&serverTimezone=UTC");
				Statement entryStatement = entryConn.createStatement();
				) {
			String addPlayer = "insert into playerScores (playerName, points) " +
				 "values ( '" + name  + "' ," + score + ")";
			entryStatement.execute(addPlayer);
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} 	
	}
	
	public String toString() {
		return "database Manager toString";
	}
	
	/**
	 * Sorts the player scores table by points in descending order so that the top 10 entries can be 
	 * pulled off the top and returned as a string.
	 * This string represents the current leader board for the points strategy game.
	 */
	public String sortRecords() {

		try (
				Connection sortConn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/ShootsAndLadders?user=root&password=root&serverTimezone=UTC");
				Statement sortStatement = sortConn.createStatement();
				) {
			String selection = "Select * from playerScores ORDER by points DESC";
			ResultSet results = sortStatement.executeQuery(selection);
			int i = 1;
			while(results.next() && i < LEADERBOARD_SIZE) {
				int place = i;
				String name = results.getString("playerName");
				int points = results.getInt("points");
				leaderBoard += place + ": " + name + " with " + points + " points\n";
				System.out.println(place + ": " + name + " with " + points + " points");
				i++;
			}
			
			
			
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		return leaderBoard;
		
	}
	
	
	
	
	

}