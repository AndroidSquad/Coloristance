package se.androidsquad.coloristance;

import android.util.Log;

//Metoder att ha
	//Läsa in vilken färg varje rektangel har från RectModel, dvs loopa igenom de olika strängarna och säg vilken färg

public class MapModel {
	
	static String[][] mapArray;
	static int x,y;

	public static void setMap(String[][] level){
		mapArray = level;
	}

	public static void renderMap(){
		//Skall innehålla funktioner som gör instanser av alla element 
		for(int i = 0; i<mapArray.length;i++){
			for(int j = 0; j<mapArray[i].length;j++){
				
				Log.v(mapArray[i][j], "i:"+i+" j:"+j);
				
				//mapArray[i][j].charAt(0) denna snippet är char-siffran på rummet 
				//och skall sändas till rederaren av färgen tillsammans med positionen som är i och j 
			}
		}
	}

	protected static void setPos(int xPos, int yPos){
		//Dessa skall anropas vid vare tryck på en dörr
		x = xPos;
		y = yPos;
	}
	
	protected static int getRoomColor(String roomId){
		//Dessa skall anropas vid vare tryck på en dšrr
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
	
	public static int getDrawPos(int pos, int multi, int size){
		/** 
		 * Take a doorposition 1-4, ,the corresponding multiplier and the screensize. 
		 * This info is used to return a value corresponding to the screen
		 * */
		
		int answer = 0;
		
		if(pos==1) 		answer = ((multi*8)+2)*(size/72);
		else if(pos==2) answer = ((multi*8)+2)*(size/24);
		else if(pos==3) answer = ((multi+1)*8)*(size/72);
		else if(pos==4)	answer = ((multi+1)*8)*(size/24);
		else answer = 0;
		
		return answer;
	}

}
