package se.androidsquad.coloristance;

public class RectModel {
	
	/* Denna klass i modellen ska innehålla Color och Size. 
	 * Inte bestämma dessa utan bara veta att den har en färg 
	 * och storlek som betsämms av GameController
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
		/* Denna metod ska meddela RectModelData när position har ändrats så att 
		 * RectModelData vet att den ska förändra rummets färg. Får kanske använda Listeners eller nåt...
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
