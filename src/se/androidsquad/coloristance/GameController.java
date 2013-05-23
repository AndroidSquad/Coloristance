package se.androidsquad.coloristance;

import android.util.Log;
import se.androidsquad.coloristance.database.Levels;

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

	protected PositionModel pos;
	protected RectModel rect;
	protected DoorModel door;
	protected String roomcode;
	
	public static KeyModel[][] key;
	public static InventoryModel inv = new InventoryModel();
	
	/*
	 * The empty constructor of GameController whcih creates an object of 
	 * each of the different models created
	 */
	public GameController(){ // Creates an object of each of the Models
	
			MapModel.setMap("map_1");
			key = KeyModel.getKeyArray();	

		
//		for(int i = 0; i<key.length;i++){
//			for(int j = 0; j<key[i].length;j++){
//				Log.v("GameController",i +","+ j +":"+ key[i][j].getKeyString());
//			}
//		}

		this.pos = new PositionModel();
		this.rect = new RectModel();
		this.door = new DoorModel();

//		doorClick();
//		Levels.initLevel();

	}
	
	
	/* Do we really need this constructor?
	 * 
	 * public GameController(PositionModel pos, Levels level, RectModel rect, DoorModel door) {
		this.pos = pos;
		this.level = level;
		this.rect = rect;
		this.door = door;
	} */

//	protected void drawMap() {
//		int posX, posY;
//		pos = new PositionModel();
//		posX = pos.getX(); // ska skickas til view att i denna positionen i rutn�tet ska en cirkel ritas ut
//		posY = pos.getY();
//
//	}

//	protected void drawRoom() {
//		
//	}
//	 this model is only called once and not as it supposed more often as I understand it.............................................................
//	protected void doorClick() {
//		roomcode = Levels.mapArray[0][0]; //retrieving the room from the database "Levels"
//		RectModel.setRectColor(roomcode);
//		DoorModel.setDoor(roomcode);
//	}

	
	

	
}