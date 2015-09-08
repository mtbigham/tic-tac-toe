package com.mainstreethub.ttt.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class Move{
	//move can be created with any row/col value
	//out of bounds throws an exception when attempting to place move
	private int row;
	private int col;
	
	@NotEmpty
	private String name;
	
	private Move(){
		//Jackson deserialization
	}
	
	public Move(int row, int col, String name){
		this.row = row;
		this.col = col;
		this.name = name;
	}
	
	@JsonProperty
	public int getRow(){
		return row;
	}
	
	@JsonProperty
	public void setRow(int row){
		this.row = row;
	}
	
	@JsonProperty
	public int getCol(){
		return col;
	}
	
	@JsonProperty
	public void setCol(int col){
		this.col = col;
	}
	
	@JsonProperty
	public String getName(){
		return name;
	}
	
	@JsonProperty
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return "[ row:"+getRow()+",col:"+getCol()+",name:"+getName()+" ]";
	}
	
	@Override
	//used by the MoveDeserializationTest to compare Move objects
	public boolean equals(Object o){
		if(o instanceof Move){
			Move m = (Move) o;
			if(this.row == m.row && this.col == m.col && this.name.equals(m.name)){
				return true;
			}
		}
		return false;
	}
}