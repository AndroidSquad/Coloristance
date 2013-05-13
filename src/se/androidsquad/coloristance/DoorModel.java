package se.androidsquad.coloristance;

/* 
 * I xml �r det nu android:background som man �ndrar p� d�rrarna f�r att byta deras f�rg, har fixat f�rgreferenser i b�de java och xml 
 * i java �r de statics ben�mnda typ BLUE_LIGHT (se DrawingRect)
 * i xml �r n�s de genom @color/blue_light
 * F�r att f�r�ndra en d�rr anropa findViewById()
 * MainActivity har bra exempel p� detta:
 * Button a = (Button) findViewById(R.id.button1);
 * Id:t hittar ni i xml filen
 */

public class DoorModel{

	/** Inneh�ller information om f�rgen p� d�rrarna och dess placering relativt rutan. 
	 */
	
	private static int[] position = {2,2,2,2}; 
		
	public static void setDoor(String pos){
		/** 
		 * DoorModel tar emot ett paint objekt som �r d�rr f�rgen och 
		 * en int som representerar d�rrarna representeras av pos 1-4
		 * 
		 * Informationen anv�nds f�r att tillskriva r�tt d�rr r�tt f�rg
		 */
		for(int i = 1; i<4; i++){
			switch(pos.charAt(i)){
			case '1': position[i] = DrawingRect.BLUE_LIGHT;
			case '2': position[i] = DrawingRect.GREEN_LIGHT;
			case '3': position[i] = DrawingRect.PURPLE_LIGHT;
			case '4': position[i] = DrawingRect.ORANGE_LIGHT;
			case '5': position[i] = DrawingRect.RED_LIGHT;		
			}
		}
	}
	
	public static int getDoor(int loc){
		return position[loc];
	}
	
}

