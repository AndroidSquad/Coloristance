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
	private boolean isVisible;
	private String keys;
	private int keyColor;

	public KeyModel(String room){
		keys = room;
		isVisible = false;
		keyColor = 9;
	}

	public static KeyModel[][] getKeyArray(){
		Log.v("GameController", "Array" +MapModel.getKeys().length+","+MapModel.getKeys()[0].length);
		KeyModel[][] keyArray = new KeyModel[MapModel.getKeys().length][MapModel.getKeys()[0].length]; 

		for(int i = 0; i<MapModel.getKeys().length;i++){
			for(int j = 0; j<MapModel.getKeys()[i].length;j++){
				Log.v("KeyModel", "Key making: " +i+","+j+ "keyCode: "+ MapModel.getKeys()[i][j]);
				keyArray[i][j] = new KeyModel(MapModel.getKeys()[i][j]);
				String room = keyArray[i][j].getKeyString();
				for(int x = 0; x<5; x++){

					if(room.charAt(x) == '1'){ 
						keyArray[i][j].setKeyVisibility(true);
						keyArray[i][j].setKeyImg(x);
						
						Log.v("KeyModel", "Key True: " +i+","+j+"keyCode: "+ MapModel.getKeys()[i][j]);
					}
					else if(room.charAt(x) == '0'){ 
						keyArray[i][j].setKeyVisibility(false);
		
						Log.v("KeyModel", "Key False: " +i+","+j+"keyCode: "+ MapModel.getKeys()[i][j]);
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

	public void setKeyVisibility(boolean visibility){
		/** col takes 1-5 as strings*/ 		
		if(visibility == true){
			isVisible = true;
		}
		else if(visibility == false){
			isVisible = false;
		}
		else{ 
			Log.v("KeyModel", "Something is wrong");
		}
	}
	
	public void setKeyImg(int color){
		if(color<5){
			keyColor = color;
			Log.v("KeyModel", "Declared: "+color);
		}
 
		else{
			keyColor = 666;
			Log.v("KeyModel", "No color was found");
		}		
	}

	public int getImg(){
		return keyColor;
	}
	
	public boolean getVisibility(){
		return isVisible;
	}

	public String getKeyString(){
		return keys;
	}
	
	public void setKeyString(String update){
		keys = update;
	}

}
