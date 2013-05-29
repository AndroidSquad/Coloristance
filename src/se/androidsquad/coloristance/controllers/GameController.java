package se.androidsquad.coloristance.controllers;

import se.androidsquad.coloristance.models.DoorModel;
import se.androidsquad.coloristance.models.InventoryModel;
import se.androidsquad.coloristance.models.KeyModel;
import se.androidsquad.coloristance.models.MapModel;
import se.androidsquad.coloristance.models.RectModel;
import android.util.Log;

public class GameController {
	
	public static boolean turned = false;
	protected RectModel rect;
	protected DoorModel door;
	protected String roomcode;
	public static KeyModel[][] key;
	public static InventoryModel inv = new InventoryModel();

	// The first level is always "map_1", why the variable level is initially defined as 1
	public static int level = 1; 

	/*
	 * The empty constructor of GameController whcih creates an object of 
	 * each of the three models, RectModel, DoorModel and Roomcode.
	 */

	public GameController(){ // Creates an object of each of the Models
		if(turned == false){
			MapModel.setMap(level);
			key = KeyModel.getKeyArray();
		}
	
		this.rect = new RectModel();
		this.door = new DoorModel();

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
