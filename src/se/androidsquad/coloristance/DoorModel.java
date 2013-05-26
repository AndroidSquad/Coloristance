package se.androidsquad.coloristance;

/* 
 * 
 * I xml är det nu android:background som man ändrar på dörrarna för att byta deras färg, har fixat färgreferenser i både java och xml 
 * i java är de statics benämnda typ BLUE_LIGHT (se DrawingRect)
 * i xml är nås de genom @color/blue_light
 * För att förändra en dörr anropa findViewById()
 * MainActivity har bra exempel på detta:
 * Button a = (Button) findViewById(R.id.button1);
 * Id:t hittar ni i xml filen
 */

/** 
 * DoorModel receives a Paint object which is the door color,
 * and an int which represent the door position. the doors position 
 * can have the value 1-4.
 * 
 * This information is used in order to provide the door with the right
 * position and color.
 */

public class DoorModel{
	

	
	private static int[] position = {0,1,2,3};
	private static int[] colors = {RectModel.BLACK,RectModel.BLUE_LIGHT, RectModel.GREEN_LIGHT, RectModel.ORANGE_LIGHT, RectModel.PURPLE_LIGHT, RectModel.RED_LIGHT, RectModel.WHITE};
	
	/**
	 * @param pos is a String which contain 5 different numbers
	 */
	public static void setDoor(String pos){

		 
		for(int i = 0; i<4; i++){
			if(pos.charAt(i+1)== '0') position[i] = colors[0];
			else if(pos.charAt(i+1)== '1') position[i] = colors[1];
			else if(pos.charAt(i+1)== '2') position[i] = colors[2];
			else if(pos.charAt(i+1)== '3') position[i] = colors[3];
			else if(pos.charAt(i+1)== '4') position[i] = colors[4];
			else if(pos.charAt(i+1)== '5') position[i] = colors[5];
			else if(pos.charAt(i+1)== '7') position[i] = colors[6];
		}
	}
	
	/**
	 * @param loc a char which represent the door and its position
	 * @return the color of the door
	 */
	public static int getDoor(char loc){
		
		if (loc == 'N') return position[0];
		else if (loc == 'E') return position[1];
		else if (loc == 'S') return position[2];
		else if (loc == 'W') return position[3];
		else return 0;
		
	}
	
	public static int getDoorColorNr(int pos){
		
		int doorNr = 9;
		for(int i = 0; i<7;i++){
		if(position[pos] == colors[i]){
			doorNr = i;
		}
		}
		return doorNr-1;
		
	}
	
}

