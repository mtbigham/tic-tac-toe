package com.mainstreethub.ttt.game;

import java.util.*;

public class Game{
	private static final int rows = 3;
	private static final int cols = 3;
	
	//board cell array
	private Cell[][] cells;
	
	//game properties
	private int id;
	private GameStatus status;
	
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
		
		//set status to playing
		status = GameStatus.PLAYING;
	}
	
	/*
	GAME ACTIONS
	Actions which have an effect upon the game object, but not the state of play
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
	
	public List<Player> getPlayers(){
		return playerList;
	}
	
	/*
	@Override
    public String toString() {
        return "[id="+id+", status="+status+"]";
    }
	*/
	
	/*
	BOARD ACTIONS
	Actions which have an effect upon the state of play
	*/
	
	//
}