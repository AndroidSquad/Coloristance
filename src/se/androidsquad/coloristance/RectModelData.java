package se.androidsquad.coloristance;

public class RectModelData {

	protected int pos = 0;
	protected int col = 5; // The color could be 0,1,2,3,4.col ska läsas in och inte fördefineras som i nuläget
	protected int size; 
	

	public RectModelData(){
		col = this.getColor();
		size = this.getRoomSize();
	}

	// Each value represents a color in a different room
	// int[] level1Color = {0,4,3,4,0,1,2,0,1,3,0,1,3,2,0}; 
	 
	
	public void setColor(int i){
		col = i ; // Detta är en tillfällig metod då färgen egentligen ska läsas in från "databasen"
	}
	
	public int getColor(){
		return col; 
	}
	
	/*public void setSize(int j){ // size could be set to 0 or 1, might be better off as an enum instead of an int
		size = j; // Dont know if we need this method
	}*/
	
	public int getRoomSize(){
		return 1; // The view will handle the actual size i pixels
	}
	
	public int getMapSize(){
		return 0; // The view will handle the actual size i pixels
	}
	
}