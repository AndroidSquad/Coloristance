package se.androidsquad.coloristance.views;

import se.androidsquad.coloristance.R;
import se.androidsquad.coloristance.R.drawable;
import se.androidsquad.coloristance.controllers.FirstScreen;
import se.androidsquad.coloristance.controllers.GameController;
import se.androidsquad.coloristance.models.InventoryModel;
import se.androidsquad.coloristance.models.KeyModel;
import se.androidsquad.coloristance.models.MapModel;
import android.app.Activity;
import android.util.Log;
import android.view.View;

public class KeyView{

	static int[] keyNames = {R.id.key_button_blue, R.id.key_button_green, R.id.key_button_orange, R.id.key_button_purple, R.id.key_button_red};
	static int[] keyImg = {drawable.key_blue, drawable.key_green, drawable.key_orange, drawable.key_purple, drawable.key_red, drawable.key_empty};
	static int[] invPos = {R.id.invKeyLeft, R.id.invKeyMid, R.id.invKeyRight};
	KeyModel thisKey;
	public Activity act;

	public KeyView(Activity current) {
		this.act = current;
	}//Constructor

	/**
	 * This method defines the keys within a room as being visible or invisible, depending on what keys
	 * are supposed to be in a room, as defined in the database (Levels.java)
	 */

	public void setKeys(){
		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];

		for(int i = 0; i<5; i++){
			if(thisKey.getKeyString().charAt(i) == '1'){
				thisKey.setKeyImg(i);
				Log.v("Firstscreen", i+ " Its visible:" + thisKey.getImg());
				act.findViewById(keyNames[thisKey.getImg()]).setVisibility(View.VISIBLE);
			}
			else if(thisKey.getKeyString().charAt(i) == '0'){
				Log.v("Firstscreen","Instansiering:" + "Nope, nothing" + i);
				act.findViewById(keyNames[i]).setVisibility(View.INVISIBLE);
			}
			else Log.v("Firstscreen", "Incorrect input");
		}//for	
	}//setKeys


	public void dropKey(int invPosition){
		//keyInvPos takes 0-2

		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];
		int emptyInventory = keyImg[5];
		char[] buffer = thisKey.getKeyString().toCharArray();
		String newKey = new String(buffer);
		int keyPos = GameController.inv.getInv(invPosition);
		boolean alloc = InventoryModel.alloc[invPosition];
		Log.d("FirstScreen", "DropKey newKey init: " + newKey);
		Log.d("FirstScreen", "Allocations: "+InventoryModel.alloc);


		//		if(newKey.charAt(keyPos) == '1' && FirstScreen.reset == false){
		//			//TODO Visa ett snabbt felmeddelande att nyckeln redan finns i rummet
		//			Log.v("FirstScreen", "The key already exist in the room");
		//		}
		if(alloc == true && newKey.charAt(keyPos) != '1' || FirstScreen.reset == true ){
			act.findViewById(invPos[invPosition]).setBackgroundResource(emptyInventory);
			alloc = false;
			if(keyPos != 5){
				buffer[keyPos] = '1';
				Log.v("FirstScreen", "Set 1");

			}//if

			newKey = new String(buffer);
			thisKey.setKeyString(newKey);
			thisKey.setKeyVisibility(true);
			GameController.inv.setInv(invPosition, 5);

			setKeys();
		}//else if(alloc==true)
		else if(alloc== false){
			Log.v("FirstScreen", "The key has been dropped/Was never there: " + invPosition);
		}
		else{ 
			Log.v("FirstScreen", "Something went wrong when calling dropKey");
		}

		InventoryModel.alloc[invPosition] = alloc;

		Log.v("FirstScreen", "DropKey newKey ending: " + newKey);
	}//dropKey

	public void setStartKeys(){

		//Looping what inital keys to show in the inventory
		for(int i= 0; i<3; i++){
			act.findViewById(invPos[i]).setBackgroundResource(keyImg[GameController.inv.getInv()[i]]);
		}		
	}//setStartKeys
}//KeyView
