package com.mainstreethub.ttt.game;

import com.mainstreethub.ttt.exceptions.*;

import java.util.*;

public class Game{
	private static final int rows = 3;
	private static final int cols = 3;
	
	//board cell array
	private Cell[][] cells;
	
	//game properties
	private int id;
	private GameStatus status;
	private int numMoves;
	
	//player list
	private List<Player> playerList;
	
	//initialization
	public Game(int id){
		//ID this game
		this.id = id;
		
		//fill board's cell array with empty cells
		cells = new Cell[rows][cols];
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				cells[i][j] = Cell.EMPTY;
			}
		}
		
		//initialize player list
		//Dropwizard resource classes are used by multiple threads
		//so we should use a thread-safe collection to save game data!
		playerList = Collections.synchronizedList(new ArrayList<Player>());
		
		this.numMoves = 0;
		
		//set status to playing
		status = GameStatus.PLAYING;
	}
	
	/*
	GAME ACTIONS
	Actions which have an rely upon the game object, but do not affect the state of play
	*/
	
	public int getID(){
		return id;
	}
	
	public Cell[][] getBoard(){
		return cells;
	}
	
	public Cell getCell(int i, int j){
		return cells[i][j];
	}
	
	public GameStatus getStatus(){
		return status;
	}
	
	//used to test working change of status
	public void setStatus(GameStatus status){
		this.status = status;
	}
	
	public List<Player> getPlayers(){
		return playerList;
	}
	
	//used to test working addition of players
	public void addPlayer(Player player){
		if(playerList.size() < 2){
			playerList.add(player);
		}
	}
	
	public int getNumMoves(){
		return numMoves;
	}
	
	//figure out which player goes next
	//(Also prevents 1-player games!)
	public int getNextPlayer(){
		//TODO: RETURN -1 IF < 2 PLAYERS
		if(playerList.size() == 0){
			return -1;
		}
		else{
			return numMoves % 2;
		}
	}
	
	//Check if a cell has already been played upon
	public boolean unavailable(int row, int col){
		if(cells[row][col] != Cell.EMPTY){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Check if a cell is out of outOfBounds
	public boolean outOfBounds(int row, int col){
		if(row >= rows || row < 0 || col >= cols || col < 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	BOARD ACTIONS
	Actions which have an effect upon the state of play
	*/
	
	//validate and place player moves
	public void placeMove(int row, int col, String name){
		if(getStatus() != GameStatus.PLAYING){
			//Game is over!
			throw new gameOverException(getStatus().name());
		}
		if(getNextPlayer() == -1){
			//No players added to the game
			throw new noPlayersException();
		}
		if(!playerList.get(getNextPlayer()).getName().equals(name)){
			//Not your turn!
			throw new wrongPlayerException();
		}
		if(outOfBounds(row, col)){
			//That cell is out of bounds
			throw new outOfBoundsException();
		}
		if(unavailable(row, col)){
			//That cell is set OR invalid
			throw new unavailablePlacementException();
		}
		
		//we made it!
		
		Cell type;
		//check which type to place
		if(getNextPlayer() == 0){
			type = Cell.X;
		}
		else{
			type = Cell.O;
		}
		
		//place move
		cells[row][col] = type;
		
		numMoves++;
		
		checkStatus();
	}
	
	//check the status of the game (Playing, X win, O win, Draw)
	public void checkStatus(){
		if(checkCell(Cell.X)){
			status = GameStatus.X_WIN;
		}
		else if(checkCell(Cell.O)){
			status = GameStatus.O_WIN;
		}
		else if(checkDraw()){
			status = GameStatus.DRAW;
		}
		else{
			status = GameStatus.PLAYING;
		}
	}
	
	public boolean checkCell(Cell cell){
		return (checkRows(cell) || checkCols(cell) || checkDiag(cell));
	}
	
	public boolean checkRows(Cell cell){
		for(int i = 0; i < rows; i++){
			if(cell == cells[i][0] &&
				cells[i][0] == cells[i][1] &&
				cells[i][1] == cells[i][2]){
					
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCols(Cell cell){
		for(int i = 0; i < cols; i++){
			if(cell == cells[0][i] &&
				cells[0][i] == cells[1][i] &&
				cells[1][i] == cells[2][i]){
					
				return true;
			}
		}
		return false;
	}
	
	public boolean checkDiag(Cell cell){
		if(cell == cells[0][0] &&
			cells[0][0] == cells[1][1] &&
			cells[1][1] == cells[2][2]){
			return true;
		}
		else if(cell == cells[0][2] &&
			cells[0][2] == cells[1][1] &&
			cells[1][1] == cells[2][0]){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean checkDraw(){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(cells[i][j] == Cell.EMPTY){
					return false;
				}
			}
		}
		return true;
	}
}