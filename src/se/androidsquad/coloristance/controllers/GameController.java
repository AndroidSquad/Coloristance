package se.androidsquad.coloristance.controllers;

import se.androidsquad.coloristance.models.DoorModel;
import se.androidsquad.coloristance.models.InventoryModel;
import se.androidsquad.coloristance.models.KeyModel;
import se.androidsquad.coloristance.models.MapModel;
import se.androidsquad.coloristance.models.RectModel;
import android.util.Log;

public class GameController {

	/*
	 * Koordinerar mellan View och Model.Should contain methods:
	 * 
	 * doorClick() - changeRoom() - changeMapLocation() - animateDoor()
	 * 
	 * drawMap(Grid) tar rutn�t fr�n modell - here(getPosition) tar emot
	 * position fr�n PositionModel - Neighbours(Door, Connect) tar emot d�rrar
	 * och de olika d�rrarnas relationer till varandra //ANV�NDS EJ JUST NU -
	 * Rects(color) H�mtar info fr�n modellen om vilka f�rger rektanglarna ska
	 * ha i kartan
	 * 
	 * drawRoom() - drawRect(color) tar info om vilken f�rg rummets ruta ska
	 * m�las i - drawDoor(position) tar info om vart d�rrarna ska placeras i
	 * rummet
	 */

	/** F�r att veta vad funktionen g�r och framf�r allt vad den tar 
	 * emot/skickar tillbaka �r dessa kommentarer utm�rkta verktyg!
	 * 
	 *  Annat tips �r: Ctr+Space n�r man inte vet vad som finns att v�lja p�
	 *  kommandot plockar upp alternativa s�tt att avsluta det du skrivit. 
	 *  Pr�va ex. att i en xml-fil skriva android: och sedan trycka Ctrl+Space
	 *  
	 *  Om du f�r ett fel kan du trycka p� det och d�refter Ctrl+1 f�r f�rlag p� l�sningar*/
	
//	public static boolean turned = false;
	protected static RectModel rect;
	protected static DoorModel door;
	protected String roomcode;
	public static KeyModel[][] key;
	public static InventoryModel inv = new InventoryModel();

	// The first level is always "map_1", why the variable level is initally defined 1
	public static int level = 1; 

	/*
	 * The empty constructor of GameController whcih creates an object of 
	 * each of the three models, RectModel, DoorModel and Roomcode.
	 */

	public GameController(){ // Creates an object of each of the Models
		
			MapModel.setMap(level);
			if(FirstScreen.turn == false){
				key = KeyModel.getKeyArray();
			}
			rect = new RectModel();
			door = new DoorModel();

	}
	//investigates which level you are on ,  initially the level is set to 1
	public static void setLevel(int lvl){
		if(1 <= lvl)
			level = lvl;
		else
			Log.v("GameController","The level couldn't be set to "+lvl);

	}
	
	/**
	 * @return The level that you currently are playing
	 */
	public static int getLevel(){
		Log.v("GameController","The level returned was: "+level);
		return level;	
	}	
}
