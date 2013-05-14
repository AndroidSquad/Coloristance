package se.androidsquad.coloristance;

import java.io.PrintStream;

import android.os.SystemClock;
import android.util.Log;
import android.webkit.ConsoleMessage;

//Metoder att ha
	//L�sa in vilken f�rg varje rektangel har fr�n RectModel, dvs loopa igenom de olika str�ngarna och s�g vilken f�rg

public class MapModel {
	
	static String[][] mapArray;
	static int x,y;

	public static void setMap(String[][] level){
		mapArray = level;
	}

	public static void renderMap(){
		//Skall inneh�lla funktioner som g�r instanser av alla element 
		for(int i = 0; i<mapArray.length;i++){
			for(int j = 0; j<mapArray[i].length;j++){
				
				Log.v("Pos: ", "i:"+i+" j:"+j);
				
				//mapArray[i][j].charAt(0) denna snippet �r char-siffran p� rummet 
				//och skall s�ndas till rederaren av f�rgen tillsammans med positionen som �r i och j 
			}
		}
	}

	protected static void setPos(int a, int b){
		//Dessa skall anropas vid vare tryck p� en d�rr
		x = a;
		y = b;
	}
	
	protected static int getRoomColor(String roomId){
		//Dessa skall anropas vid vare tryck p� en d�rr
		for(int i = 0; i<2;i++){ // //Loopen �r felaktig. 2:a b�r ers�ttas med dynamiskt v�rde
			for(int j = 0; j<2;j++){ //Loopen �r felaktig. 2:a b�r ers�ttas med dynamiskt v�rde
				RectModel.setRectColor(mapArray[x][y]);
			}
		}
	
		return RectModel.getRectColor();
	}
	
	protected String getPos(){
		return mapArray[x][y];
	}

}
