package se.androidsquad.coloristance;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class GameController {

	/*
	 * Koordinerar mellan View och Model.Bšr innehŒlla metoderna:
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

	protected PositionModel pos;
	protected RectModel rect;
	protected DoorModel door;
	protected RectModelData data;
	protected ModelData modData;
	protected Levels level;
	protected int col = 3; // Dessa ska vara flexibla och gŒ att Šndra sedan
	protected int size = 1;// Dessa ska vara flexibla och gŒ att Šndra sedan
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
		posX = pos.getX(); // ska skickas til view att i denna positionen i rutnätet ska en cirkel ritas ut
		posY = pos.getY();

	}

	protected void drawRoom() {
		
	}

	protected void doorClick() {

	}
	
	

	
}