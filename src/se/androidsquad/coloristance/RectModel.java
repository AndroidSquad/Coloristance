package se.androidsquad.coloristance;

import java.io.File;
public class RectModel {
	
	/* Denna klass i modellen ska innehålla Color och Size. 
	 * Inte bestämma dessa utan bara veta att den har en färg 
	 * och storlek som betsämms av GameController
	 */

	public RectModel(){
	}
	
	public RectModel(String a, int b) {	}
	
	public static final int BLUE_LIGHT 	= 0xFF33B5E5;
	public static final int GREEN_LIGHT	= 0xFF669900;
	public static final int ORANGE_LIGHT= 0xFFFF8800;
	public static final int PURPLE_LIGHT= 0xFF9933CC;
	public static final int RED_LIGHT 	= 0xFFCC0000;

	protected int pos = 0;
	protected static int rectColor; // The color could be 0,1,2,3,4.
	protected static int size;// The value could be 0 or 1. 0 = map rectangle  1 = room rectangle.
	protected String[][] mapArray = new String [2][2];
	protected File filename = new File("/assets/levels/Level1.txt");

	public static void setRectColor(String roomcode) { //Should be a case sats, but that is a problem for future Simon and future Tommy	
		
			if (roomcode.charAt(0) == '1') {
				rectColor = BLUE_LIGHT;
			} else if (roomcode.charAt(0) == '2') {
				rectColor = GREEN_LIGHT;
			} else if (roomcode.charAt(0) == '3') {
				rectColor = ORANGE_LIGHT;
			} else if (roomcode.charAt(0) == '4') {
				rectColor = PURPLE_LIGHT;
			} else if (roomcode.charAt(0) == '5') {
				rectColor = RED_LIGHT;
			}
			else 
				rectColor = 0;
		}
	
	
	public static int getRectColor(){
		return rectColor;
	}		
		
	public void updatePos(){
		/* Denna metod ska meddela RectModelData när position har ändrats så att 
		 * RectModelData vet att den ska förändra rummets färg. Får kanske använda Listeners eller nåt...
		 */
	}	
	
	protected static void setSize(int i){
		size=i;
	}
	
	protected static int getSize(){ // get the rectangles size
		return size;
	}

	public int getRoomSize(){return 1;}
	public int getMapSize(){return 0;}
	

	// Each value represents a color in a different room //This is how we theoretically can insert our database into an array
	// int[] level1Color = {0,4,3,4,0,1,2,0,1,3,0,1,3,2,0}; 
//	public String[][] initLevel() throws FileNotFoundException, IOException{
//		Scanner sc = new Scanner(filename);
//		for (int row = 0; row < mapArray.length; row++){
//			for(int column = 0; column < mapArray[row].length; column++){
//				mapArray[row][column] = sc.next().toString();
//			}
//			
//		} 
//		return mapArray;
//
//	}

	
}
