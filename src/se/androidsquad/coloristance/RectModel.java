package se.androidsquad.coloristance;

public class RectModel {
	
	/* Denna klass i modellen ska innehålla Color och Size. 
	 * Inte bestämma dessa utan bara veta att den har en färg 
	 * och storlek som betsämms av GameController
	 */
	
	int rectCol; //private hides from other classes within the package
	int a;
	
	/* The method setColor takes a number between 1-4 representing colors.
	 * 1=Blue
	 * 2=Green
	 * 3=Yellow
	 * 4=Red
	 * */
	
	protected void setColor(int i){ //set the rectangles color
		rectCol = i;
	}
	
	protected int getColor(){ // get the rectangles color
		return rectCol;
	}
	
	protected void setSize(int x){ // sets the rectangle size, all sides have equall length
		a = x;
	}
	
	protected int getSize(){
		return a;

	}
	
	
}
