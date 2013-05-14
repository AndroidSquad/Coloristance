package se.androidsquad.coloristance;

import java.io.PrintStream;

import android.os.SystemClock;
import android.util.Log;
import android.webkit.ConsoleMessage;

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
				
				Log.v("Pos: ", "i:"+i+" j:"+j);
				
				//mapArray[i][j].charAt(0) denna snippet är char-siffran på rummet 
				//och skall sändas till rederaren av färgen tillsammans med positionen som är i och j 
			}
		}
	}

	protected static void setPos(int a, int b){
		//Dessa skall anropas vid vare tryck på en dörr
		x = a;
		y = b;
	}
	
	protected static int getRoomColor(String roomId){
		//Dessa skall anropas vid vare tryck på en dšrr
		for(int i = 0; i<2;i++){ // //Loopen Šr felaktig. 2:a bšr ersŠttas med dynamiskt vŠrde
			for(int j = 0; j<2;j++){ //Loopen Šr felaktig. 2:a bšr ersŠttas med dynamiskt vŠrde
				RectModel.setRectColor(mapArray[x][y]);
			}
		}
	
		return RectModel.getRectColor();
	}
	
	protected String getPos(){
		return mapArray[x][y];
	}

}
