package se.androidsquad.coloristance;

//Metoder att ha
	//L�sa in vilken f�rg varje rektangel har fr�n RectModel, dvs loopa igenom de olika str�ngarna och s�g vilken f�rg
	//

public class MapModel {
	

	
	
	protected int getX(String roomcode){
		return roomcode.charAt(5);
	}
			
	protected int getY(String roomcode){
		return roomcode.charAt(6);
		
	}
}
