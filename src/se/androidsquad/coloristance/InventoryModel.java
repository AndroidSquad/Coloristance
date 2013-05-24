package se.androidsquad.coloristance;

import android.util.Log;
import se.androidsquad.coloristance.R.drawable;

public class InventoryModel {

	static int[] invKey = new int[3];
	//int[] img = {drawable.key_blue, drawable.key_green, drawable.key_orange, drawable.key_purple, drawable.key_red};

	InventoryModel(){
		//Initalize inventory with three empty slots
		invKey[0] = 5;
		invKey[1] = 5;
		invKey[2] = 5;
	}

	public int getInv(int pos){
		if(invKey[pos]<5)
			return invKey[pos];
		else{ 
			Log.v("InventoryModel", "Couldn't return value: " + invKey[pos]);
			return 0;
		}
	}
	public int[] getInv(){
		return invKey;
	}
	public void setInv(int pos, int img){
		invKey[pos] = img;
	}
}
