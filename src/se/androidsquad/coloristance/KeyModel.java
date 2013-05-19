package se.androidsquad.coloristance;

import se.androidsquad.coloristance.R.drawable;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

public class KeyModel {

	//Nytt key_object för varjerum. Läs in från levels 
	// Skall länkas till rummen 'Antal rum'
	// Skall relatera till färger 1-5 och en empty 6
	// Skall ha en position i rummet 1-5, färg fristående - relaterar till skärm

	//position: Talar om var i rummet nyckeln befinner sig
	//color: anger nycklens färg
	int position, color;
	int isVisible;
	String key;

	public KeyModel(){
		position = 0;
		color = 0;
		isVisible = View.GONE; 
	}
	
	public KeyModel(String room){
		position = Integer.parseInt(""+room.charAt(3));
		color = room.charAt(3);
	}
	
	public static KeyModel[][] getKeyArray(){
		Log.v("GameController", "Array" +MapModel.getKeys().length+","+MapModel.getKeys()[0].length);
		KeyModel[][] keyArray = new KeyModel[MapModel.getKeys().length][MapModel.getKeys()[0].length]; 

		for(int i = 0; i<MapModel.getKeys().length;i++){
			for(int j = 0; j<MapModel.getKeys()[0].length;j++){
				Log.v("GameController", "Key making" +i+","+j);
					keyArray[i][j] = new KeyModel();
					String room = MapModel.keyArray[i][j];
								
					if(room.charAt(j) == '1') keyArray[i][j].setKey(j, true ); 
					else keyArray[i][j].setKey(0, false);
			}
		}
		
		
		return keyArray;
	}

	//TODO Set positions related to screen

	public void setKey(int col, boolean isVisible){
		/** col takes 1-5 as strings*/ 		
		if(isVisible == true){
			this.isVisible = View.VISIBLE;
		}
		else{ 
			this.isVisible = View.GONE;
		}
		/*RectModel.setRectColor(col);
		this.color = RectModel.getRectColor();
		key = col;*/
		
		if (col == 1){
			color = drawable.key_blue;
		} 
		else if (col == 2){
			color = drawable.key_green;
		}
		else if (col == 3){
			color = drawable.key_orange;
		} 
		else if (col == 4){
			color = drawable.key_purple;
		} 
		else if (col == 5){
			color = drawable.key_red;
		} 
		else{
			color = Color.BLACK;
			Log.v("KeyModel", "No color was found");
		}
	}

	public int getCol(){
		return color;
	}
/*
	public int getPos(){
		return position;
	}
*/

	public void setVisibility(boolean isVisible){
		if(isVisible == true){
			this.isVisible = View.VISIBLE;
		}
		else{ 
			this.isVisible = View.GONE;
		}
	}

	public String keyName(){
		return key;
	}
	/*
	public void setKeys(KeyModel key[][]){
		for(int i = 0; i<key.length;i++){
			for(int j = 0; j<key[i].length;j++){
				if(MapModel.mapArray[i][j].charAt(0) == 1){
					key[i][j].setKey("blue", true);
					key[i][j].setKey("green", false);
					key[i][j].setKey("orange", false);
					key[i][j].setKey("purple", false);
					key[i][j].setKey("red", true);
				}
			}
		}*/

/*
public int getColorFromChar(char col){

		if (col == '1'){
			return RectModel.BLUE_LIGHT;
		} 
		else if (col == '2'){
			return RectModel.GREEN_LIGHT;
		}
		else if (col == '3'){
			return RectModel.ORANGE_LIGHT;
		} 
		else if (col == '4'){
			return RectModel.PURPLE_LIGHT;
		} 
		else if (col == '5'){
			return RectModel.RED_LIGHT;
		} 
		else{ 
			Log.v("KeyModel", "No color was found");
			return 666;
		}

	}*/
}
