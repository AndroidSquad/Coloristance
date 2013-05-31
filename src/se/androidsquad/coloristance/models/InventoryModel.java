package se.androidsquad.coloristance.models;

import android.util.Log;

public class InventoryModel{

	public static int[] invKey = new int[3];
	public static boolean[] alloc = new boolean[3];

	public InventoryModel(){
		//Initialize inventory with three empty slots
		for(int i=0; i<3; i++){
			invKey[i] = 5;
		}
		setAllocations();

	}//Constructor

	InventoryModel(int[] prevInvKeys){
		invKey = prevInvKeys;
		setAllocations();
	}//InventoryModel

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
		}//for
	}//setAllocations

	public int getInv(int pos){
		if(invKey[pos]<5)
			return invKey[pos];
		else{ 
			Log.v("InventoryModel", "Couldn't return value: " + invKey[pos]);
			return 0;
		}
	}//getInv

	public int[] getInv(){
		return invKey;
	}//getInv

	public void setInv(int pos, int img){
		invKey[pos] = img;
	}//setInv
}//InventoryModel
