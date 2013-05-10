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

	private PositionModel pos;
	private Levels level = new Levels();
	private RectModel rect;
	private DoorModel door;

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