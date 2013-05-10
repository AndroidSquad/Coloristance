package se.androidsquad.coloristance;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class GameController {

	/*
	 * Koordinerar mellan View och Model.Bör innehålla metoderna:
	 * 
	 * doorClick() - changeRoom() - changeMapLocation() - animateDoor()
	 * 
	 * drawMap(Grid) tar rutnät från modell - here(getPosition) tar emot
	 * position från PositionModel - Neighbours(Door, Connect) tar emot dörrar
	 * och de olika dörrarnas relationer till varandra //ANVƒNDS EJ JUST NU -
	 * Rects(color) Hämtar info från modellen om vilka färger rektanglarna ska
	 * ha i kartan
	 * 
	 * drawRoom() - drawRect(color) tar info om vilken färg rummets ruta ska
	 * målas i - drawDoor(position) tar info om vart dörrarna ska placeras i
	 * rummet
	 */

	private PositionModel pos;
	private Levels level = new Levels();
	private RectModel rect;
	private DoorModel door;

	protected void drawMap() {
		int posX, posY;
		pos = new PositionModel();
		posX = pos.getX(); // ska skickas til view att i denna positionen i rutn‰tet ska en cirkel ritas ut
		posY = pos.getY();
		

	}

	protected void drawRoom() {

	}

	protected void doorClick() {

	}
}