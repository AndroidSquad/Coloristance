package se.androidsquad.coloristance;

public class RectModelData {

	protected int pos = 0;
	protected int col; // The color could be 0,1,2,3,4.
	protected int size;// The value could be 0 or 1. 0 = map rectangle  1 = room rectangle.

	public RectModelData(int a, int b){

	}

	// Each value represents a color in a different room
	int[] level1Color = {0,4,3,4,0,1,2,0,1,3,0,1,3,2,0};

	public int getColor(){
		return level1Color[pos]; // Reads the value from the Array level1Color

	}
	
	public int getSize(){
		return 1; // 1 is the size of the room rectangle. The view will handle the actual size i pixels
	}
	
}