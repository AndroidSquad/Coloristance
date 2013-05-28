package se.androidsquad.coloristance;

import android.util.Log;

public class InventoryModel{

	public static int[] invKey = new int[3];
	public static boolean[] alloc = new boolean[3];
	//int[] img = {drawable.key_blue, drawable.key_green, drawable.key_orange, drawable.key_purple, drawable.key_red};

	InventoryModel(){
		//Initalize inventory with three empty slots
		for(int i=0; i<3; i++){
			invKey[i] = 5;
		}
		setAllocations();

	}

	InventoryModel(int[] prevInvKeys){
		invKey = prevInvKeys;
		setAllocations();
	}

	private void setAllocations(){
		for(int i=0; i<3; i++){
			if(invKey[i] !=5 && invKey[i] <6){
				alloc[i] = true;
			}
			else if (invKey[i] == 5){
				alloc[i] = false;
			}
			else{
				Log.v("InventoryModel","Incorrect value: " + invKey[i]);
			}
		}
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
