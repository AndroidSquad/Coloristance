package se.androidsquad.coloristance;

import android.graphics.Paint;

public class ModelData {

	public static final int BLUE_LIGHT 	= 0xFF33B5E5;
	public static final int GREEN_LIGHT	= 0xFF669900;
	public static final int ORANGE_LIGHT= 0xFFFF8800;
	public static final int PURPLE_LIGHT= 0xFF9933CC;
	public static final int RED_LIGHT 	= 0xFFCC0000;
	
	protected int pos = 0;
	protected int roomColor, doorEastColor, doorSouthColor, doorWestColor, doorNorthColor; // The color could be 0,1,2,3,4.
	protected int size;// The value could be 0 or 1. 0 = map rectangle  1 = room rectangle.

	public ModelData(String a, int b){ //ÄNDRA
		this.setColor(a);
		this.setSize(b);
	}

	// Each value represents a color in a different room
	// int[] level1Color = {0,4,3,4,0,1,2,0,1,3,0,1,3,2,0}; 
	
	
	
	
	public void setColor(String roomcode) {
		for (int i = 0; i < roomcode.length(); i++)
			if (roomcode.charAt(i) == 1) {
				roomColor = BLUE_LIGHT;
			} else if (roomcode.charAt(i) == 2) {
				roomColor = GREEN_LIGHT;
			} else if (roomcode.charAt(0) == 3) {
				roomColor = ORANGE_LIGHT;
			} else if (roomcode.charAt(0) == 4) {
				roomColor = PURPLE_LIGHT;
			} else if (roomcode.charAt(0) == 5) {
				roomColor = RED_LIGHT;
			}
	}

	public int getColor(){
		return roomColor;
	}
	
	
	public void setSize(int j){ // size could be set to 0 or 1, might be better off as an enum instead of an int
		size = j;
	}
	
	public int getSize(){
		return size; // The view will handle the actual size i pixels
	}
	
}