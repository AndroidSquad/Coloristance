package se.androidsquad.coloristance;

import android.graphics.Color;

/* 
 * I xml �r det nu android:background som man �ndrar p� d�rrarna f�r att byta deras f�rg, har fixat f�rgreferenser i b�de java och xml 
 * i java �r de statics ben�mnda typ BLUE_LIGHT (se DrawingRect)
 * i xml �r n�s de genom @color/blue_light
 * F�r att f�r�ndra en d�rr anropa findViewById()
 * MainActivity har bra exempel p� detta:
 * Button a = (Button) findViewById(R.id.button1);
 * Id:t hittar ni i xml filen
 */

/** 
 * DoorModel tar emot ett paint objekt som �r d�rr f�rgen och 
 * en int som representerar d�rrarna representeras av pos 1-4
 * 
 * Informationen anv�nds f�r att tillskriva r�tt d�rr r�tt f�rg
 */

/** Inneh�ller information om f�rgen p� d�rrarna och dess placering relativt rutan. 
 */

public class DoorModel{
	
	private static int[] position = {1,2,3,4}; 
		
	public static void setDoor(String pos){
		for(int i = 0; i<4; i++){
			
			if(pos.charAt(i+1)== '1') position[i] = ModelData.BLUE_LIGHT;
			else if(pos.charAt(i+1)== '2') position[i] = ModelData.GREEN_LIGHT;
			else if(pos.charAt(i+1)== '3') position[i] = ModelData.PURPLE_LIGHT;
			else if(pos.charAt(i+1)== '4') position[i] = ModelData.ORANGE_LIGHT;
			else if(pos.charAt(i+1)== '5') position[i] = ModelData.RED_LIGHT;	
			else position[i+1] = 0;	
			
		}
	}
	
	public static int getDoor(char loc){
		
		if (loc == 'N') return position[0];
		else if (loc == 'E') return position[1];
		else if (loc == 'S') return position[2];
		else if (loc == 'W') return position[3];
		else return 0;
		
	}
	
}

