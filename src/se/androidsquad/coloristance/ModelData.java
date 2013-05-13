package se.androidsquad.coloristance;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import android.widget.TextView;

public class ModelData {

	public static final int BLUE_LIGHT 	= 0xFF33B5E5;
	public static final int GREEN_LIGHT	= 0xFF669900;
	public static final int ORANGE_LIGHT= 0xFFFF8800;
	public static final int PURPLE_LIGHT= 0xFF9933CC;
	public static final int RED_LIGHT 	= 0xFFCC0000;

	protected int pos = 0;
	protected int roomColor, doorEastColor, doorSouthColor, doorWestColor, doorNorthColor; // The color could be 0,1,2,3,4.
	protected int size;// The value could be 0 or 1. 0 = map rectangle  1 = room rectangle.
	protected String[][] mapArray = new String [2][2];

	File filename = new File("/assets/levels/Level1.txt");
	public ModelData(String a, int b){ 
		this.setColor(a);
		this.setSize(b);
	}


	// Each value represents a color in a different room
	// int[] level1Color = {0,4,3,4,0,1,2,0,1,3,0,1,3,2,0}; 
//	public String[][] initLevel() throws FileNotFoundException, IOException{
//		Scanner sc = new Scanner(filename);
//		for (int row = 0; row < mapArray.length; row++){
//			for(int column = 0; column < mapArray[row].length; column++){
//				mapArray[row][column] = sc.next().toString();
//			}
//			
//		} 
//		return mapArray;
//
//	}

	public void setColor(String roomcode) { //Should be a case sats, but that is a problem for future Simon and future Tommy	
		for(int i=0;i<roomcode.length();i++){	
			if (roomcode.charAt(0) == '1') {
				roomColor = BLUE_LIGHT;
			} else if (roomcode.charAt(0) == '2') {
				roomColor = GREEN_LIGHT;
			} else if (roomcode.charAt(0) == '3') {
				roomColor = ORANGE_LIGHT;
			} else if (roomcode.charAt(0) == '4') {
				roomColor = PURPLE_LIGHT;
			} else if (roomcode.charAt(0) == '5') {
				roomColor = RED_LIGHT;
			}
			else 
				roomColor = 0;
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