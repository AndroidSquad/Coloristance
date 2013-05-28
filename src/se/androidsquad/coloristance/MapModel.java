package se.androidsquad.coloristance;

import se.androidsquad.coloristance.database.Levels;
import android.util.Log;


/**
 * This class contains methods which read what color each rectangle has from RectModel, looping the strings
 * and telling which color it has, and also the logic for moving the player
 */


public class MapModel {

	public static String[][] mapArray, keyArray = null;
	private static int x,y,mapWidth,mapHeight,mapTop, mapBot, mapRight, mapLeft;
	private static int leftX, rightX, topY, botY;

	/**
	 * Sets the map that is to be played by retrieving the corresponding array from the database (Levels.java)
	 * @param level an int representing what level is to be played
	 */
	
	public static void setMap(int level){
		if(level == 1) {
			mapArray = Levels.map_1;
		}
		else if(level == 2){
			mapArray = Levels.map_2;
		} 
		else if(level == 3){
			mapArray = Levels.map_3;
		}
		else{
			Log.v("MapModel", "The map-level doesn't exist");
		}
		
		setKeys(level);
	}
	
	/**
	 * Sets the keys to the corresponding map by retrieving the corresponding array for keys from the
	 * database (Levels.java)
	 * @param level the same int as previously defining what level is to be played, this time also
	 * defining what keys are associated with that level
	 */
	private static void setKeys(int level){
		if(level == 1){
			keyArray = Levels.keys_1;
		}
		else if(level == 2){
			keyArray = Levels.keys_2;
		}
		else if(level== 3){
			keyArray = Levels.keys_3;
		}
		else{
			Log.v("MapModel", "The key-level doesn't exist");
		}
	}


	/**
	 * 
	 * @return mapArray the array containing information about the rooms in a certain level, and the doors in each room
	 */
	
	public static String[][] getMap(){
		return mapArray;		
	}
	
	/**
	 * @return keyArray the array containing the keys for the specific level
	 */
	
	public static String[][] getKeys(){
		return keyArray;
	}

	/**
	 * 
	 * @param xPos an int describing the position of the player on the x-axis
	 * @param yPos an int describing the position of the player on the y-axis
	 */
	
	public static void setPos(int xPos, int yPos){
		x = xPos;
		y = yPos;
	}

	
	/**
	 * Checks to see if the new value is acceptable, if it is, it removes one from the y-position.
	 * it is called whenever the "North" door is clicked.
	 */

	public static void moveUp(){
		if(y-1 < 0 || mapArray[x][y-1].charAt(0)=='0'){
			y=y; //You get the point... Don't move.
			Log.v("MapModel", "Moving out of bounds" + mapArray[x][y]);
		}
		else
			y=y-1;
	}

	/**
	 * Checks to see if the new value is acceptable, if it is, it adds one to the x-position.
	 * it is called whenever the "East" door is clicked.
	 */

	public static void moveRight(){
		if(x+1 >= mapArray.length || mapArray[x+1][y].charAt(0)=='0'){
			x=x; //You get the point... Don't move.
			Log.v("MapModel", "Moving out of bounds" + mapArray[x][y]);
		}
		else
			x=x+1;
	}


	/**
	 * Checks to see if the new value is acceptable, if it is, it adds one to the y-pos.
	 * it is called whenever the "South" door is clicked.
	 */

	public static void moveDown(){
		if(y+1>=mapArray[0].length || mapArray[x][y+1].charAt(0)=='0'){
			y=y;
			//Log.v("MapModel", "Moving out of bounds" + mapArray[x][y]);
		}
		else
			y=y+1;
	}


	/**
	 * Checks to see if the new value is acceptable, if it is, it removes one from the x-pos.
	 * it is called whenever the "West" door is clicked.
	 */
	protected static void moveLeft(){
		if(x-1 < 0 || mapArray[x-1][y].charAt(0)=='0'){
			x=x; //You get the point... Don't move.
			Log.v("MapModel", "Moving out of bounds" + mapArray[x][y]);
		}
		else
			x=x-1;
	}


	/**
	 * @return the current position in the array
	 */

	public static String getRoom(){
		return mapArray[x][y];
	}

	/**
	 * 
	 * @return the current position on the x-axis
	 */
	public static int getMyX(){
		return x;
	}

	/**
	 * 
	 * @return the current position on the y-axis
	 */
	public static int getMyY(){
		return y;
	}

	/**
	 * Sets the values for the map, in order for the map to be able to be painted
	 * @param sizeX an int describing the width of the map
	 * @param sizeY an int describing the height of the map
	 * @param top an int representing the topmost position in the graphical representation of the map
	 * @param right an int representing the rightmost position in the graphical representation of the map
	 * @param bot an int representing the position in the bottom of the graphical representation of the map
	 * @param left an int representing the leftmost position in the graphical representation of the map
	 */
	public static void setMap(int sizeX, int sizeY, int top, int right,int bot, int left){
		mapWidth 	= sizeX;
		mapHeight 	= sizeY;
		mapTop 		= top;
		mapRight 	= right;
		mapBot 		= bot;
		mapLeft 	= left;
	}

	/** Receives a doorposition, 1-4,the corresponding multiplier.
	 * This info is used to return a value corresponding to the screen.
	 * @param cornerPos the int is used to determine where the door is intended to be placed
	 * @param multi this int is used to determine which rect is intended. The multi currently ranges from 0 to map.array.length 
	 * @return an int which is used to determine where the rect should be
	 */

	public static int getRectPos(int cornerPos, int multi){
		/** 
		 * Take a doorposition 1-4, ,the corresponding multiplier and the screensize. 
		 * This info is used to return a value corresponding to the screen
		 * */

		int answer = 0;
		if		(cornerPos==1){ answer = (multi)*(mapWidth/(mapArray.length))+(mapWidth/(mapArray.length*20))		;
		Log.v("1:",""+answer);
		leftX = answer;
		}
		else if	(cornerPos==2){ 
			answer = (multi)*(mapHeight/(mapArray[0].length))+(mapHeight/(mapArray[0].length*20))	;
			Log.v("2:",""+answer);
			topY = answer;
		}
		else if	(cornerPos==3){ 
			answer = ((multi+1)*(mapWidth/(mapArray.length)))- (mapWidth/(mapArray.length*20));
			Log.v("3:",""+answer); 
			rightX = answer;
		}
		else if	(cornerPos==4){ 
			answer = ((multi+1)*(mapHeight/(mapArray[0].length)))-(mapHeight/(mapArray[0].length*20));
			Log.v("4:", ""+answer);
			botY = answer;
		}
		else answer = 0;

		Log.v("MapModel","x: "+mapArray.length+" y: "+mapArray[0].length);

		return answer;
	}

	/**
	 * Receives a doorposition, 1-4,the corresponding multiplier.
	 * This info is used to return a value corresponding to the screen.
	 * @param value the int is used to determine where the circle is
	 * @param multi this int is used to determine which rect is intended to 
	 * 		contain the circle. The multi currently ranges from 0 to map.array.length 
	 * @return an int which is used to determine where the circle should be
	 */

	public static int getCircPos(int value, int multi){
		/** 
		 * Take a doorposition 1-4, ,the corresponding multiplier and the screensize. 
		 * This info is used to return a value corresponding to the screen
		 * */
		int answer = 0;
		//places the circle in the middle of the rect corresponding to the center x-position
		if (value==1) {
			answer = ((rightX-leftX)/2)+(multi)*(mapWidth/(mapArray.length))+(mapWidth/(mapArray.length*20)) ;
			Log.v("CircX:", ""+answer);

		}
		//places the circle in the middle of the rect corresponding to the center y-position
		else if (value==2) {
			answer = ((botY-topY)/2)+(multi)*(mapHeight/(mapArray[0].length))+(mapHeight/(mapArray[0].length*20)) ;

			Log.v("CircY:", ""+answer);
		}
		//Radius
		else if (value==3) {
			answer = (botY-topY)/2;
			Log.v("Rad:", ""+answer);
		}
		else if(value==4){
			answer = (rightX-leftX)/2;
		}
		
		else answer = 0;

		return answer;
	}
}
