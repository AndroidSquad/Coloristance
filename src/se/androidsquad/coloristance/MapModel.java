package se.androidsquad.coloristance;

import java.util.StringTokenizer;

//Metoder att ha
	//Läsa in vilken färg varje rektangel har från RectModel, dvs loopa igenom de olika strängarna och säg vilken färg

public class MapModel {
	
	static String[][] mapArray;
	static int x,y;

	public static void setMap(String[][] level){
		mapArray = level;
	}

	private void renderMap(){
		//Skall innehålla funktioner som gör instanser av alla element 
		for(int i = 0; i<mapArray.length;i++){
			for(int j = 0; j<mapArray[i].length;j++){
				//mapArray[i][j].charAt(0) denna snippet är char-siffran på rummet 
				//och skall sändas till rederaren av färgen tillsammans med positionen som är i och j 
			}
		}
	}

	protected void setPos(int x, int y){
		//Dessa skall anropas vid vare tryck på en dörr
		this.x = x;
		this.y = y;
	}
	
	protected static int getRoomColor(String roomId){
		//Dessa skall anropas vid vare tryck på en dörr
		for(int i = 0; i<mapArray.length;i++){
			for(int j = 0; j<mapArray[i].length;j++){
				RectModel.setRectColor(mapArray[x][y]);
			}
		}
	
		return RectModel.getRectColor();
	}
	
	protected String getPos(){
		return mapArray[x][y];
	}

}
