package com.mainstreethub.ttt.game;

public class Player{
	private final String name;
	private final int id;
	
	public Player(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
}