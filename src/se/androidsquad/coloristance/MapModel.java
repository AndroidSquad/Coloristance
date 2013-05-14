package se.androidsquad.coloristance;

import android.util.Log;

//Metoder att ha
	//L�sa in vilken f�rg varje rektangel har fr�n RectModel, dvs loopa igenom de olika str�ngarna och s�g vilken f�rg

public class MapModel {
	
	static String[][] mapArray;
	private static int x,y,screenWidth,screenHeight;

	public static void setMap(String[][] level){
		mapArray = level;
	}

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

	protected static void setPos(int xPos, int yPos){
		//Dessa skall anropas vid vare tryck p� en d�rr
		x = xPos;
		y = yPos;
	}
	
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
	
	protected String getRoom(){
		return mapArray[x][y];
	}

	
	public static int getMyX(){
		return x;
	}
	
	public static int getMyY(){
		return y;
	}
	
	public static void setMapSize(int sizeX, int sizeY){
		screenWidth 	= sizeX;
		screenHeight 	= sizeY;
	}
	
	public static int getDrawPos(int cornerPos, int multi){
		/** 
		 * Take a doorposition 1-4, ,the corresponding multiplier and the screensize. 
		 * This info is used to return a value corresponding to the screen
		 * */
		
		int answer = 0;
		//Till rektangeln, h�rn 1 - 4
		if		(cornerPos==1) answer = ((multi*8)+2)*(screenWidth/72);
		else if	(cornerPos==2) answer = ((multi*8)+2)*(screenHeight/24);
		else if	(cornerPos==3) answer = ((multi+1)*8)*(screenWidth/72);
		else if	(cornerPos==4) answer = ((multi+1)*8)*(screenHeight/24);
		
		//Till cirkeln
		//Mitt i rectX
		else if (cornerPos==5) answer = ((multi*8)+2)*(screenWidth/72)+((((multi+1)*8)*(screenWidth/72)-((multi*8)+2)*(screenWidth/24))/2);
		//Mitt i rectY
		else if (cornerPos==6) answer = ((multi*8)+2)*(screenHeight/24)+((((multi+1)*8)*(screenHeight/24)-((multi*8)+2)*(screenHeight/24))/2);
		//Radie
		else if (cornerPos==7) answer = ((multi+1)*8)*(screenWidth/72)-((multi*8)+2)*(screenWidth/72);
				
		else answer = 0;
		
		return answer;
	}

}
