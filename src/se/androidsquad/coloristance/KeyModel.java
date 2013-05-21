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
	int isVisible;
	String keys;
	int keyColor;
	
	public KeyModel(String room){
			keys = room;
	}
	
	public static KeyModel[][] getKeyArray(){
		Log.v("GameController", "Array" +MapModel.getKeys().length+","+MapModel.getKeys()[0].length);
		KeyModel[][] keyArray = new KeyModel[MapModel.getKeys().length][MapModel.getKeys()[0].length]; 

		for(int i = 0; i<MapModel.getKeys().length;i++){
			for(int j = 0; j<MapModel.getKeys()[i].length;j++){
				Log.v("KeyModel", "Key making1: " +i+","+j);
					keyArray[i][j] = new KeyModel(MapModel.getKeys()[i][j]);
					Log.v("KeyModel", "Key making2: " +i+","+j);
					String room = keyArray[i][j].keyName();
					Log.v("KeyModel", "Key making3: " +i+","+j);
					for(int x = 0; x<5; x++){

						if(room.charAt(x) == '1'){ 
							keyArray[i][j].setKey(x, true );
							Log.v("KeyModel", "Key making5: " +i+","+j);
						}
						else if(room.charAt(x) == '0'){ 
							keyArray[i][j].setKey(x, false);
							Log.v("KeyModel", "Key making6: " +i+","+j);
						}
						else{
							Log.v("KeyModel", "You got 0, something is wrong");
						}
					}
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
		
		if (col == 0){
			keyColor = drawable.key_blue;
		} 
		else if (col == 1){
			keyColor = drawable.key_green;
		}
		else if (col == 2){
			keyColor = drawable.key_orange;
		} 
		else if (col == 3){
			keyColor = drawable.key_purple;
		} 
		else if (col == 4){
			keyColor = drawable.key_red;
		} 
		else{
			keyColor = Color.BLACK;
			Log.v("KeyModel", "No color was found");
		}
	}

	public int getCol(){
		return keyColor;
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
		return keys;
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
