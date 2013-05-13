package se.androidsquad.coloristance;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class GameController {

	/*
	 * Koordinerar mellan View och Model.B�r inneh�lla metoderna:
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
	protected RectModelData data;
	protected ModelData modData;
	protected Levels level;
	protected int col = 3; // Dessa ska vara flexibla och g� att �ndra sedan
	protected int size = 1;// Dessa ska vara flexibla och g� att �ndra sedan
	protected String roomcode;
	
	
	public GameController(){ // Creates an object of each of the Models
		this.pos = new PositionModel();
		this.rect = new RectModel();
		this.door = new DoorModel();
		roomcode = level.Level1[0][0]; //retrieving the room from the database "Levels"
		RectModel.setRectColor(roomcode);
		DoorModel.setDoor(roomcode);

	}
	
	
	/* Do we really need this constructor?
	 * 
	 * public GameController(PositionModel pos, Levels level, RectModel rect, DoorModel door) {
		this.pos = pos;
		this.level = level;
		this.rect = rect;
		this.door = door;
	} */

	protected void drawMap() {
		int posX, posY;
		pos = new PositionModel();
		posX = pos.getX(); // ska skickas til view att i denna positionen i rutn�tet ska en cirkel ritas ut
		posY = pos.getY();

	}

	protected void drawRoom() {
		
	}

	protected void doorClick() {

	}
	
	

	
}