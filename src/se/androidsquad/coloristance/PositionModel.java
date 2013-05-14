package se.androidsquad.coloristance;

public class PositionModel {
	
	/*
	 * This model contain the information about the players position
	 * so the map is able to mark the right rectangle on the map.
	 */
	private int x,y;
	
	protected void setPosition(int x, int y){ //receives x- and y- values which notes where the player is standing at the moment
		this.x=x;
		this.y=y;
	}
	
	protected int getX(){ //return the x-coordinate
		return x;
	}
	protected int getY(){ //return the y-coordinate
		return y;
	}
}
