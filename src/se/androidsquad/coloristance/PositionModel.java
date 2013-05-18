package se.androidsquad.coloristance;

public class PositionModel {
	
	/*
	 * This model contain the information about the players position
	 * so the map is able to mark the right rectangle on the map.
	 */
	private int x,y;
	
	/**
	 * @param x	this variable represent the x coordinate where the player stand at the moment
	 * @param y	this variable represent the y coordinate where the player stand at the moment
	 */
	protected void setPosition(int x, int y){ 
		this.x=x;
		this.y=y;
	}
	
	/**
	 * @return	 the x coordinate where the player stands at the moment
	 */
	protected int getX(){ //return the x-coordinate
		return x;
	}
	
	/**
	 * @return the y coordinate where the player stands at the moment
	 */
	protected int getY(){ 
		return y;
	}
}
