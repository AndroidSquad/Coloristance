package se.androidsquad.coloristance;

public class RectModel {
	
	/* Denna klass i modellen ska inneh�lla Color och Size. 
	 * Inte best�mma dessa utan bara veta att den har en f�rg 
	 * och storlek som bets�mms av GameController
	 */
	RectModelData data;
	
	public RectModel() {
		this.data = new RectModelData(); 
	}
	
	/* The method setColor takes a number between 1-4 representing colors.
	 * 1=Blue
	 * 2=Green
	 * 3=Yellow
	 * 4=Red
	 * */
	
	protected int roomSize;// The value could be 0 or 1. 0 = map rectangle  1 = room rectangle.
	protected int mapSize;// The value could be 0 or 1. 0 = map rectangle  1 = room rectangle.
	
	public void updatePos(){
		/* Denna metod ska meddela RectModelData n�r position har �ndrats s� att 
		 * RectModelData vet att den ska f�r�ndra rummets f�rg. F�r kanske anv�nda Listeners eller n�t...
		 */
	}
	
	protected int getColor(){ // get the rectangles color
		return data.getColor();
	}
	
	
	protected int getRoomSize(){ // get the rectangles size
		return data.getRoomSize();
	}
	
	protected int getMapSize(){ // get the rectangles size
		return data.getMapSize();
	}
	
}
