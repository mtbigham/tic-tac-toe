package com.mainstreethub.ttt.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class Player{
	@NotEmpty
	private final String name;
	
	private final int id;
	
	public Player(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	@JsonProperty
	public String getName(){
		return name;
	}
	
	@JsonProperty
	public String getID(){
		return id;
	}
}