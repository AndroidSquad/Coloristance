package se.androidsquad.coloristance;

import android.graphics.Canvas;

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
	protected Levels level;
	protected RectModel rectMod;
	protected DoorModel door;
	protected DrawingRect drawRect;
	int size;
	protected Canvas canvas;

	public GameController(DrawingRect rectview){ // Creates an object of each of the Models
		this.pos = new PositionModel();
		this.level = new Levels();
		this.rectMod = new RectModel();
		this.door = new DoorModel();
		this.drawRect = rectview;
	}

	/* Do we really need this constructor?
	 * 
	 * public GameController(PositionModel pos, Levels level, RectModel rect, DoorModel door) {
		this.pos = pos;
		this.level = level;
		this.rect = rect;
		this.door = door;
	}*/

	/*protected createMapRect(position where to draw, drawingRectName){
		col = rect.getColor();
		size = rect.getMapSize();
		DrawingRect drawingRectName = new DrawingRect(position where to draw);
	}*/

	protected void drawPosition() {
		int posX, posY;
		pos = new PositionModel();
		posX = pos.getX(); // ska skickas til view att i denna positionen i rutnätet ska en cirkel ritas ut
		posY = pos.getY();
	}

	protected void drawMap() {
		//mŒlar kartan som ska fyllas med rektanglar

	}

	protected void drawRoom() {

	}

	protected void doorClick() {

	}

}