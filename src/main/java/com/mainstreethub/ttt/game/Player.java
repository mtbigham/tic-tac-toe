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
	public int getID(){
		return id;
	}
	
	@Override
	public String toString(){
		return "[ name:"+getName()+",id:"+getID()+" ]";
	}
	
	@Override
	//used by the PlayerHealthCheck to compare Player objects
	public boolean equals(Object o){
		if(o instanceof Player){
			Player p = (Player) o;
			if(this.id == p.id && this.name.equals(p.name)){
				return true;
			}
		}
		return false;
	}
}