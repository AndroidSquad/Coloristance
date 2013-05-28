package se.androidsquad.coloristance;

import android.util.Log;

public class RectModel {

	/**
	 * This Model class contains the colors that will be used in the game. This class will not however determine
	 * the colors for the rooms and keys, but just keep the colors.
	 */

	public static final int BLACK 		= 0xFF000000;
	public static final int BLUE_DARK 	= 0xFF0099CC;
	public static final int BLUE_LIGHT 	= 0xFF33B5E5;
	public static final int GREEN_LIGHT	= 0xFF669900;
	public static final int ORANGE_LIGHT= 0xFFFF8800;
	public static final int PURPLE_LIGHT= 0xFF9933CC;
	public static final int RED_LIGHT 	= 0xFFCC0000;
	public static final int WHITE 		= 0xFFFFFFFF;

	protected int pos = 0;
	public static int rectColor; // The color could be 0,1,2,3,4,5,6

	/**
	 * @param roomcode	this string represents the code of the current room
	 */
	public static void setRectColor(String roomcode) { 
		if (roomcode.charAt(0) == '0'){
			rectColor = BLACK;
		} else if (roomcode.charAt(0) == '1') {
			rectColor = BLUE_LIGHT;
		} else if (roomcode.charAt(0) == '2') {
			rectColor = GREEN_LIGHT;
		} else if (roomcode.charAt(0) == '3') {
			rectColor = ORANGE_LIGHT;
		} else if (roomcode.charAt(0) == '4') {
			rectColor = PURPLE_LIGHT;
		} else if (roomcode.charAt(0) == '5') {
			rectColor = RED_LIGHT;
		} else {
			rectColor = WHITE;
		}
	}

	/**
	 * @return	rectColor the color of the the room asked for as an int
	 */
	public static int getRectColor(){
		return rectColor;
	}

	/**
	 * @return color the color of the room as a string needed for the initialization of a paint object in drawMap
	 */
	public static String getRoomColor(){

		String color;
		if(rectColor == BLACK) 	color = "black";
		else if(rectColor == WHITE) color ="white";
		else if(rectColor == BLUE_LIGHT)	color = "bl";
		else if(rectColor == GREEN_LIGHT)	color = "gl";
		else if(rectColor == ORANGE_LIGHT)	color = "ol";
		else if(rectColor == PURPLE_LIGHT)	color = "pl";
		else if(rectColor == RED_LIGHT)		color = "rl";

		else color = "Inget";	
		return color;
	}
}
