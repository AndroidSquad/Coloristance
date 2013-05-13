package se.androidsquad.coloristance;

import java.util.StringTokenizer;

//Metoder att ha
	//L�sa in vilken f�rg varje rektangel har fr�n RectModel, dvs loopa igenom de olika str�ngarna och s�g vilken f�rg

public class MapModel {
	
	String[][] mapArray;
	int x,y;

	public MapModel(String[][] mapArray){
		this.mapArray = mapArray;
		renderMap();
	}

	private void renderMap(){
		//Skall inneh�lla funktioner som g�r instanser av alla element 
		for(int i = 0; i<mapArray.length;i++){
			for(int j = 0; j<mapArray[i].length;j++){
				//mapArray[i][j].charAt(0) denna snippet �r char-siffran p� rummet 
				//och skall s�ndas till rederaren av f�rgen tillsammans med positionen som �r i och j 
			}
		}
	}

	protected void setPos(int x, int y){
		//Dessa skall anropas vid vare tryck p� en d�rr
		this.x = x;
		this.y = y;
	}
	
	protected String getPos(){
		return mapArray[x][y];
	}

}
