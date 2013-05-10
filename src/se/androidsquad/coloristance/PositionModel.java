package se.androidsquad.coloristance;

public class PositionModel {
	
	/*Denna modell inneh�ller information om vart spelaren 
	 * befinner sig s� att kartan kan markera den aktuella rutan.
	 * 
	 */
	private int x,y;
	
	protected void setPosition(int x, int y){ //Tar emot de x- och y-v�rden soom angivits att spelaren st�r p�
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
