package se.androidsquad.coloristance;

import android.graphics.Paint;

public class RectModel {
	
	/* Denna klass i modellen ska innehålla Color och Size. 
	 * Inte bestämma dessa utan bara veta att den har en färg 
	 * och storlek som betsämms av GameController
	 */
	ModelData data;
	
	
	public RectModel(String a, int b) {
		this.data = new ModelData(a,b); // 3 stands for the a color and 1 for the room rectangle
	}
	
	/* The method setColor takes a number between 1-4 representing colors.
	 * 1=Blue
	 * 2=Green
	 * 3=Yellow
	 * 4=Red
	 * */
	
	
	public void updatePos(){
		/* Denna metod ska meddela RectModelData när position har ändrats så att 
		 * RectModelData vet att den ska förändra rummets färg. Får kanske använda Listeners eller nåt...
		 */
	}
	
//	protected int getColor(){ // get the rectangles color
//		return data.getColor();
//	}
	
	protected void setSize(int i){
		data.setSize(i);
	}
	
	protected int getSize(){ // get the rectangles size
		return data.getSize();

	}
	
}
