package se.androidsquad.coloristance;

import java.io.File;
import java.util.HashMap;

import android.graphics.Paint;
import android.util.Log;

public class RectModel {
	
	/* Denna klass i modellen ska inneh�lla Color och Size. 
	 * Inte best�mma dessa utan bara veta att den har en f�rg 
	 * och storlek som bets�mms av GameController
	 */

	public RectModel(){
	}
	
	public RectModel(String a, int b) {	}

	public static final int BLACK 		= 0xFF000000;
	public static final int BLUE_LIGHT 	= 0xFF33B5E5;
	public static final int GREEN_LIGHT	= 0xFF669900;
	public static final int ORANGE_LIGHT= 0xFFFF8800;
	public static final int PURPLE_LIGHT= 0xFF9933CC;
	public static final int RED_LIGHT 	= 0xFFCC0000;

	protected int pos = 0;
	protected static int rectColor; // The color could be 0,1,2,3,4,5.
	protected static int size;// The value could be 0 or 1. 0 = map rectangle  1 = room rectangle.

	public static void setRectColor(String roomcode) { //Should be a case sats, but that is a problem for future Simon and future Tommy	
		
			
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
			}
			else{ 
				Log.v("RectModel", "No color was found");
				rectColor = BLACK;
			}
				
			
		}
	
	
	public static int getRectColor(){
		
	//	if(rectColor == 0) Log.v("RectModel", "No color was sent");
		return rectColor;
		
		
	}
	
	public static String getRoomColor(){

		String color;
		if(rectColor == BLACK) 				color = "black";
		else if(rectColor == BLUE_LIGHT)	color = "bl";
		else if(rectColor == GREEN_LIGHT)	color = "gl";
		else if(rectColor == ORANGE_LIGHT)	color = "ol";
		else if(rectColor == PURPLE_LIGHT)	color = "pl";
		else if(rectColor == RED_LIGHT)		color = "rl";
		
		else color = "Inget";
		Log.v("RectModel.getRectColor", "Skickar: "+color);	
		return color;
	}
		
	public void updatePos(){
		/* Denna metod ska meddela RectModelData n�r position har �ndrats s� att 
		 * RectModelData vet att den ska f�r�ndra rummets f�rg. F�r kanske anv�nda Listeners eller n�t...
		 */
	}	
	
	protected static void setSize(int i){
		size=i;
	}
	
	protected static int getSize(){ // get the rectangles size
		return size;
	}

	public int getRoomSize(){return 1;}
	public int getMapSize(){return 0;}
	


	
}
