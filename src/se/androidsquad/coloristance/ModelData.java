package se.androidsquad.coloristance;

public class ModelData {

	protected int pos = 0;
	protected int col; // The color could be 0,1,2,3,4.
	protected int size;// The value could be 0 or 1. 0 = map rectangle  1 = room rectangle.

	public ModelData(int a, int b){
		this.setColor(a);
		this.setSize(b);
	}

	// Each value represents a color in a different room
	// int[] level1Color = {0,4,3,4,0,1,2,0,1,3,0,1,3,2,0}; 
	
	
	public void setColor(int i){
		col = i ; // Detta är en tillfällig metod då färgen egentligen ska läsas in från "databasen"
	}
	
	public int getColor(){
		return col; 
	}
	
	public void setSize(int j){ // size could be set to 0 or 1, might be better off as an enum instead of an int
		size = j;
	}
	
	public int getSize(){
		return size; // The view will handle the actual size i pixels
	}
	
}