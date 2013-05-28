package se.androidsquad.coloristance.views;

import se.androidsquad.coloristance.R;
import se.androidsquad.coloristance.R.drawable;
import se.androidsquad.coloristance.controllers.GameController;
import se.androidsquad.coloristance.models.InventoryModel;
import se.androidsquad.coloristance.models.KeyModel;
import se.androidsquad.coloristance.models.MapModel;
import android.app.Activity;
import android.util.Log;

public class InventoryView{

	static int[] keyNames = {R.id.key_button_blue, R.id.key_button_green, R.id.key_button_orange, R.id.key_button_purple, R.id.key_button_red};
	static int[] keyImg = {drawable.key_blue, drawable.key_green, drawable.key_orange, drawable.key_purple, drawable.key_red, drawable.key_empty};
	static int[] invPos = {R.id.invKeyLeft, R.id.invKeyMid, R.id.invKeyRight};
	
	KeyModel thisKey;
	KeyView  keyV;
	public Activity act;

	public InventoryView(Activity current, KeyView keyV) {
		this.act = current;
		this.keyV = keyV;
	}
	
	/**
	 * Sets the inventory on the screen.
	 * @param int keyPos, values between 0 - 4 
	 */

	public void setInventory(int keyPos){

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
				}//if

				newKey = new String(buffer);
				thisKey.setKeyString(newKey);
				thisKey.setKeyVisibility(false);
				act.findViewById(invPos[invPosition]).setBackgroundResource(keyImg[keyPos]);
				GameController.inv.setInv(invPosition, keyPos);

				keyV.setKeys();
			}//if
			else if (allocated == true && placed == false){
				Log.v("FirstScreen", "Spot: "+i+" was full.");
			}
			else{
				Log.v("FirstScreen", "Key has value has been placed");
			}

			InventoryModel.alloc[i] = allocated;
		}//for

		Log.v("FirstScreen", "setInventory newKey ending: " + newKey);

		/*		Log.v("FirstScreen", "input setInventory : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());
		Log.v("FirstScreen", "buffer setInventory 1: " + buffer[0]+ buffer[1]+ buffer[2]+ buffer[3]+ buffer[4]);
		Log.v("FirstScreen", "set InvPos: " + GameController.inv.getInv(putAtPosition));
		Log.v("FirstScreen", "newKey: " + newKey);*/

	}//setInventory
	
		
	/**
	 * Cleans the current inventory, so that no keys are displayed as being the inventory
	 */
	public void cleanInventory(){
		for(int i = 0; i<3;i++){
			keyV.dropKey(i);
		}
	}//cleanInventory


}
