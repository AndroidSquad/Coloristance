package se.androidsquad.coloristance;

import android.widget.Button;
import android.app.Activity;
import android.graphics.Paint;

public class DoorModel extends Activity {

	/* Innehåller information om färgen på dörrarna och dess placering relativt rutan. 
	 */
	
	/* 
	 * I xml är det nu android:background som man ändrar på dörrarna för att byta deras färg, har fixat färgreferenser i både java och xml 
	 * i java är de statics benämnda typ BLUE_LIGHT (se DrawingRect)
	 * i xml är nås de genom @color/blue_light
	 * För att förändra en dörr anropa findViewById()
	 * MainActivity har bra exempel på detta:
	 * Button a = (Button) findViewById(R.id.button1);
	 * Id:t hittar ni i xml filen
	 */
	
	Button doorTop = (Button) findViewById(R.id.top_door);
	Button doorBot = (Button) findViewById(R.id.bot_door);
	Button doorRight = (Button) findViewById(R.id.right_door);
	Button doorLeft = (Button) findViewById(R.id.left_door);
	
	DoorModel(){System.out.println("DoorModel need an int and a String");}
	
	DoorModel(int col, String pos){
		/** 
		 * DoorModel tar emot ett paint objekt som är dörr färgen och 
		 * en int som representerar dörrarna representeras av pos 1-4
		 * 
		 * Informationen används för att tillskriva rätt dörr rätt färg
		 */
		
		if(pos == "1") doorTop.setBackgroundColor(col);
		else if(pos == "2") doorTop.setBackgroundColor(col);
		else if(pos == "3") doorTop.setBackgroundColor(col);
		else if(pos == "4") doorTop.setBackgroundColor(col);
		else System.out.println("Faulty string to DoorModel");
		
	}
			
	
}

