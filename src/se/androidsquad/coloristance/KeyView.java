package se.androidsquad.coloristance;

import se.androidsquad.coloristance.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
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
	}

	/**
	 * This method defines the keys within a room as being visible or invisible, depending on what keys
	 * are supposed to be in a room, as defined in the database (Levels.java)
	 */

	protected void setKeys(){
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
		} 
	}

	protected void dropKey(int invPosition){
		//keyInvPos takes 0-2

		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];
		int emptyInventory = keyImg[5];
		char[] buffer = thisKey.getKeyString().toCharArray();
		String newKey = new String(buffer);
		int keyPos = GameController.inv.getInv(invPosition);
		boolean alloc = InventoryModel.alloc[invPosition];
		Log.v("FirstScreen", "DropKey newKey init: " + newKey);
		Log.v("FirstScreen", "Allocations: "+InventoryModel.alloc);


		if(newKey.charAt(keyPos) == '1'){
			//TODO Visa ett snabbt felmeddelande att nyckeln redan finns i rummet
			Log.v("FirstScreen", "The key already exist in the room");
		}
		else if(alloc == true ){
			act.findViewById(invPos[invPosition]).setBackgroundResource(emptyInventory);
			alloc = false;
			if(keyPos != 5){
				buffer[keyPos] = '1';
				Log.v("FirstScreen", "Set 1");

			}

			newKey = new String(buffer);
			thisKey.setKeyString(newKey);
			thisKey.setKeyVisibility(true);
			GameController.inv.setInv(invPosition, 5);

			setKeys();
		}
		else if(alloc== false){
			Log.v("FirstScreen", "The key has been dropped/Was never there: " + invPosition);
		}
		else{ 
			Log.v("FirstScreen", "Something went wrong when calling dropKey");
		}

		InventoryModel.alloc[invPosition] = alloc;

		Log.v("FirstScreen", "DropKey newKey ending: " + newKey);
	}
	
	/**
	 * Sets the inventory on the screen.
	 * @param int keyPos, values between 0 - 4 
	 */

	protected void setInventory(int keyPos){

		//Set the requested drawable key
		int clickedKey = keyImg[keyPos];

		//Inventory position to put key at. If something is not declared, 9 will make it go out of bounds.
		int invPosition = 9;

		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];
		// TODO vad gör egentligen denna?

		boolean placed = false;
		char[] buffer = thisKey.getKeyString().toCharArray();
		String newKey = new String(buffer);
		Log.v("FirstScreen", "setInventory newKey init: " + newKey);

		//AllocatedInv checks the three spots a key could fit in and put it at the first available
		for(int i =0; i<3;i++){
			boolean allocated = InventoryModel.alloc[i];
			if(allocated == false && placed == false){
				act.findViewById(invPos[i]).setBackgroundResource(clickedKey);
				invPosition = i;
				Log.v("FirstScreen", "Key put in: "+i);

				placed =true;
				allocated = true;

				if(keyPos != 5){
					buffer[keyPos] = '0';
					Log.v("FirstScreen", "Set 0");
				}

				newKey = new String(buffer);
				thisKey.setKeyString(newKey);
				thisKey.setKeyVisibility(false);
				act.findViewById(invPos[invPosition]).setBackgroundResource(keyImg[keyPos]);
				GameController.inv.setInv(invPosition, keyPos);

				setKeys();
			}
			else if (allocated == true && placed == false){
				Log.v("FirstScreen", "Spot: "+i+" was full.");
			}
			else{
				Log.v("FirstScreen", "Key has value has been placed");
			}

			InventoryModel.alloc[i] = allocated;
		}

		Log.v("FirstScreen", "setInventory newKey ending: " + newKey);

	}
	
	public void setStartKeys(){

		//Looping what inital keys to show in the inventory
		for(int i= 0; i<3; i++){
			act.findViewById(invPos[i]).setBackgroundResource(keyImg[GameController.inv.getInv()[i]]);
		}
		
	}
	
	/**
	 * Cleans the current inventory, so that no keys are displayed as being the inventory
	 */
	public void cleanInventory(){
		for(int i = 0; i<3;i++){
			dropKey(i);
		}
	}


}
