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
	 * drawMap(Grid) tar rutnŠt frŒn modell - here(getPosition) tar emot
	 * position frŒn PositionModel - Neighbours(Door, Connect) tar emot dšrrar
	 * och de olika dšrrarnas relationer till varandra //ANVÄNDS EJ JUST NU -
	 * Rects(color) HŠmtar info frŒn modellen om vilka fŠrger rektanglarna ska
	 * ha i kartan
	 * 
	 * drawRoom() - drawRect(color) tar info om vilken fŠrg rummets ruta ska
	 * mŒlas i - drawDoor(position) tar info om vart dšrrarna ska placeras i
	 * rummet
	 */

	/** För att veta vad funktionen gör och framför allt vad den tar 
	 * emot/skickar tillbaka är dessa kommentarer utmärkta verktyg!
	 * 
	 *  Annat tips är: Ctr+Space när man inte vet vad som finns att välja på
	 *  kommandot plockar upp alternativa sätt att avsluta det du skrivit. 
	 *  Pröva ex. att i en xml-fil skriva android: och sedan trycka Ctrl+Space
	 *  
	 *  Om du får ett fel kan du trycka på det och därefter Ctrl+1 för förlag på lösningar*/
	
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
