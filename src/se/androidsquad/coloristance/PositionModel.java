package se.androidsquad.coloristance;

public class PositionModel {
	
	/*Denna modell innehåller information om vart spelaren 
	 * befinner sig så att kartan kan markera den aktuella rutan.
	 * 
	 */
	private int x,y;
	
	protected void setPosition(int x, int y){ //Tar emot de x- och y-värden soom angivits att spelaren står på
		this.x=x;
		this.y=y;
	}
	
	protected int getX(){ //Returnerar x-koordinaten
		return x;
	}
	protected int getY(){ //Returnerar y-koordinaten
		return y;
	}
}
