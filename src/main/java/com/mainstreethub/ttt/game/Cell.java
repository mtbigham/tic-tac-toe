package com.mainstreethub.ttt.game;

public enum Cell{
	EMPTY, X, O;
	
	public String toString() {
        return this == EMPTY ? " " : name();
    }
}