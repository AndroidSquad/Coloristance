package se.androidsquad.coloristance;

import se.androidsquad.coloristance.database.Levels;
import android.util.Log;

//Metoder att ha
	//L�sa in vilken f�rg varje rektangel har fr�n RectModel, dvs loopa igenom de olika str�ngarna och s�g vilken f�rg

public class MapModel {
	
	static String[][] mapArray;
	private static int x,y,mapWidth,mapHeight,mapTop, mapBot, mapRight, mapLeft;
	private static int leftX, rightX, topY, botY;
	

	/** Receives a String, which is set to the level to use
	 * @param level The name of the level to use 
	 */
	public static void setMap(String level){
		if(level == "lvl_1")mapArray = Levels.mapArray;		
	}
	
	/**
	 * @return The mapArray which is to be used for the current game
	 */
	public static String[][] getMap(){
		return mapArray;		
	}

	/**
	 * This method is currently not being used
	 */
	public static void renderMap(){
		//Skall inneh�lla funktioner som g�r instanser av alla element 
		for(int i = 0; i<mapArray.length;i++){
			for(int j = 0; j<mapArray[i].length;j++){
				
				Log.v(mapArray[i][j], "i:"+i+" j:"+j);
				
				//mapArray[i][j].charAt(0) denna snippet �r char-siffran p� rummet 
				//och skall s�ndas till rederaren av f�rgen tillsammans med positionen som �r i och j 
			}
		}
	}

	/** This method is called when a door is pressed to set the new position
	 * @param xPos the int sets our x to xPos' value 
	 * @param yPos the int sets our y to yPos' value
	 */
	protected static void setPos(int xPos, int yPos){
		//Dessa skall anropas vid vare tryck p� en d�rr
		x = xPos;
		y = yPos;
	}
	
	/**
	 * Checks to see if the new value is acceptable, if it is, it changes the y-pos one step.
	 */
	protected static void moveUp(){
		//Ska anropas n�r den norra d�rren klickas
		y -= 1;
		if (y < 0)
			y=0;	
	}

	/**
	 * Checks to see if the new value is acceptable, if it is, it changes the x-pos one step.
	 */
	public static void moveRight(){
		if(x+1 >= mapArray.length)
			x=mapArray.length-1;
		else
			x=x+1;
	}
	
	/**
	 * Checks to see if the new value is acceptable, if it is, it changes the y-pos one step.
	 */
	public static void moveDown(){
		if(y+1>=mapArray[0].length)
			y=mapArray[0].length-1;
		else
			y=y+1;
	}
	
	/**
	 * Checks to see if the new value is acceptable, if it is, it changes the x-pos.
	 */
	protected static void moveLeft(){
		//Ska anropas n�r den v�stra d�rren klickas
		x -= 1;
		if (x < 0)
			x=0;
	}
	
	/**
	 * The method takes a String, goes through the array to match the correct coordinates, and returns the correct color
	 * @param roomId the String which checks the color to paint the room in
	 * @return the correct color for the coordinates
	 */
	protected static int getRoomColor(String roomId){
		//Dessa skall anropas vid vare tryck p� en d�rr
		for(int i = 0; i<mapArray.length;i++){
			for(int j = 0; j<mapArray[i].length;j++){
					if(roomId == mapArray[i][j]){
						RectModel.setRectColor(mapArray[x][y]);
					}
			}
		}
		return RectModel.getRectColor();
	}
	
	/**
	 * @return the current position in the array
	 */
	public static String getRoom(){
		return mapArray[x][y];
	}

	
	/**
	 * @return the current x-position
	 */
	public static int getMyX(){
		return x;
	}
	/**
	 * @return the current y-position
	 */
	public static int getMyY(){
		return y;
	}
	
	/**
	 * @param sizeX sets the mapWidth to this int
	 * @param sizeY sets the mapHeight to this int
	 * @param top Currently not used
	 * @param right Currently not used
	 * @param bot Currently not used
	 * @param left Currently not used
	 */
	public static void setMap(int sizeX, int sizeY, int top, int right,int bot, int left){
		mapWidth 	= sizeX;
		mapHeight 	= sizeY;
		mapTop 		= top;
		mapRight 	= right;
		mapBot 		= bot;
		mapLeft 	= left;
	}
	
	/** Takes a doorposition 1-4, ,the corresponding multiplier and the screensize. 
	 * This info is used to return a value corresponding to the screen
	 * @param cornerPos the int is used to determine where the door is intended to be placed
	 * @param multi this int is used to determine which rect is intended. The multi currently ranges from 0-map.array.length 
	 * @return an int which is used to determine where the rect should be
	 */
	public static int getRectPos(int cornerPos, int multi){

		int answer = 0;
		//Till rektangeln, h�rn 1 - 4
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
	 * Take a doorposition 1-4, ,the corresponding multiplier and the screensize. 
	 * This info is used to return a value corresponding to the screen
	 * @param value the int is used to determine where the circle is
	 * @param multi this int is used to determine which rect is intended to contain the circle. The multi currently ranges from 0-map.array.length 
	 * @return an int which is used to determine where the circle should be
	 */
	public static int getCircPos(int value, int multi){

		int answer = 0;
		//Till cirkeln
		//Mitt i rectX
		if (value==1) {
			answer = ((rightX-leftX)/2)+(multi)*(mapWidth/(mapArray.length))+(mapWidth/(mapArray.length*20)) ;
			Log.v("CircX:", ""+answer);
			}
		//Mitt i rectY
		else if (value==2) {
			answer = ((botY-topY)/2)+(multi)*(mapHeight/(mapArray[0].length))+(mapHeight/(mapArray[0].length*20)) ;
			Log.v("CircY:", ""+answer);
			}
		//Radie
		else if (value==3) {
			answer = (botY-topY)/2;
			Log.v("Rad:", ""+answer);
			}

		else answer = 0;
		
		return answer;
	}
	
	
	
	
	

}
