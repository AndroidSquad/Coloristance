package se.androidsquad.coloristance;

public class RectModel {
	
	/* Denna klass i modellen ska innehålla Color och Size. 
	 * Inte bestämma dessa utan bara veta att den har en färg 
	 * och storlek som betsämms av GameController
	 */
	
	
	private RectModelData data = new RectModelData(3,1); // 3 stands for the a color and 1 for the room rectangle
	
	/* The method setColor takes a number between 1-4 representing colors.
	 * 1=Blue
	 * 2=Green
	 * 3=Yellow
	 * 4=Red
	 * */
	
	protected void setColor(int i){ //set the rectangles color
		data.col = i; // Change the variable col in the RectModelData object named data
	}
	
	protected int getColor(){ // get the rectangles color
		return data.col;
	}
	
	protected void setSize(int x){ // sets the rectangle size, all sides have equal length
		data.size = x; // Change the variable size in the RectModelData object named data
	}
	
	protected int getSize(){ // get the rectangles size
		return data.size;

	}
	
	
}
