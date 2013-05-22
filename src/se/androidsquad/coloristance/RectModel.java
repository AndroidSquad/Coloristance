package se.androidsquad.coloristance;

import android.util.Log;

public class RectModel {

	/* 
	 * This model shall contain the Color that will be used in the game
	 * and not determine the color, this will be determined by GameController
	 */

	public static final int BLACK 		= 0xFF000000;
	public static final int BLUE_LIGHT 	= 0xFF33B5E5;
	public static final int GREEN_LIGHT	= 0xFF669900;
	public static final int ORANGE_LIGHT= 0xFFFF8800;
	public static final int PURPLE_LIGHT= 0xFF9933CC;
	public static final int RED_LIGHT 	= 0xFFCC0000;
	public static final int WHITE 		= 0xFFFFFFFF;

	protected int pos = 0;
	public static int rectColor; // The color could be 0,1,2,3,4,5,6

	/**
	 * @param roomcode	this string is the code of the current room
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
	 * @return	the color of the the room asked for as an int
	 */
	public static int getRectColor(){
		return rectColor;
	}

	/**
	 * @return the color of the room as a string needed for the initialization of a paint object in drawMap
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

	public void updatePos(){
		/* Denna metod ska meddela RectModelData när position har ändrats så att 
		 * RectModelData vet att den ska förändra rummets färg. Får kanske använda Listeners eller nåt...
		 */
	}	

}
