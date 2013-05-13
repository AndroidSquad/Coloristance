package se.androidsquad.coloristance;

/* 
 * I xml är det nu android:background som man ändrar på dörrarna för att byta deras färg, har fixat färgreferenser i både java och xml 
 * i java är de statics benämnda typ BLUE_LIGHT (se DrawingRect)
 * i xml är nås de genom @color/blue_light
 * För att förändra en dörr anropa findViewById()
 * MainActivity har bra exempel på detta:
 * Button a = (Button) findViewById(R.id.button1);
 * Id:t hittar ni i xml filen
 */

public class DoorModel{

	/** Innehåller information om färgen på dörrarna och dess placering relativt rutan. 
	 */
	
	private static int[] position = {2,2,2,2}; 
		
	public static void setDoor(String pos){
		/** 
		 * DoorModel tar emot ett paint objekt som är dörr färgen och 
		 * en int som representerar dörrarna representeras av pos 1-4
		 * 
		 * Informationen används för att tillskriva rätt dörr rätt färg
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

