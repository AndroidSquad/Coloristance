package se.androidsquad.coloristance;

import se.androidsquad.coloristance.R.drawable;

public class InventoryModel {
	
	static int[] invKey = new int[3];
	//int[] img = {drawable.key_blue, drawable.key_green, drawable.key_orange, drawable.key_purple, drawable.key_red};
	
	InventoryModel(){
		//Initalize inventory with three empty slots
		invKey[0] = 3;
		invKey[1] = 2;
		invKey[2] = 1;
	}
	
	public int getInv(int pos){
		return invKey[pos];
	}
	public int[] getInv(){
		return invKey;
	}
	public void setInv(int pos, int img){
		invKey[pos] = img;
	}
}
