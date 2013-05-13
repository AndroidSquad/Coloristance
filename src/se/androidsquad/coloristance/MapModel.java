package se.androidsquad.coloristance;

//Metoder att ha
	//Läsa in vilken färg varje rektangel har från RectModel, dvs loopa igenom de olika strängarna och säg vilken färg
	//

public class MapModel {
	

	
	
	protected int getX(String roomcode){
		return roomcode.charAt(5);
	}
			
	protected int getY(String roomcode){
		return roomcode.charAt(6);
		
	}
}
