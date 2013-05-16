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


/** 
 * DoorModel tar emot ett paint objekt som är dörr färgen och 
 * en int som representerar dörrarna representeras av pos 1-4
 * 
 * Informationen används för att tillskriva rätt dörr rätt färg
 */

public class DoorModel{
	

	
	private static int[] position = {0,1,2,3,4,5,6}; 
		
	public static void setDoor(String pos){

		
		for(int i = 0; i<4; i++){
			if(pos.charAt(i+1)== '0') position[i] = RectModel.BLACK;
			else if(pos.charAt(i+1)== '1') position[i] = RectModel.BLUE_LIGHT;
			else if(pos.charAt(i+1)== '2') position[i] = RectModel.GREEN_LIGHT;
			else if(pos.charAt(i+1)== '3') position[i] = RectModel.ORANGE_LIGHT;
			else if(pos.charAt(i+1)== '4') position[i] = RectModel.PURPLE_LIGHT;
			else if(pos.charAt(i+1)== '5') position[i] = RectModel.RED_LIGHT;
			else if(pos.charAt(i+1)== '6') position[i] = RectModel.GOLD;
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

