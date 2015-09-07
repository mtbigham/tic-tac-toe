package com.mainstreethub.ttt.game;

public class Board{
	public static final int rows = 3;
	public static final int cols = 3;
	
	//board cell array
	Cell[][] cells;
	
	//initialization
	public Board(){
		cells = new Cell[rows][cols];
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				cells[i][j] = new Cell(i,j);
			}
		}
	}
}