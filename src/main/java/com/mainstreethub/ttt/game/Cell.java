package com.mainstreethub.ttt.game;

public class Cell{
	CellStatus status;
	int row, col;
	
	public Cell(int row, int col){
		this.row = row;
		this.col = col;
		this.status = CellStatus.EMPTY;
	}
	
	public void fill(){
		System.out.print(status == CellStatus.EMPTY ? " " : status.name());
	}
}