package se.androidsquad.coloristance;

import android.widget.Button;
import android.app.Activity;
import android.graphics.Paint;

public class DoorModel extends Activity {

	/* Inneh�ller information om f�rgen p� d�rrarna och dess placering relativt rutan. 
	 */
	
	/* 
	 * I xml �r det nu android:background som man �ndrar p� d�rrarna f�r att byta deras f�rg, har fixat f�rgreferenser i b�de java och xml 
	 * i java �r de statics ben�mnda typ BLUE_LIGHT (se DrawingRect)
	 * i xml �r n�s de genom @color/blue_light
	 * F�r att f�r�ndra en d�rr anropa findViewById()
	 * MainActivity har bra exempel p� detta:
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
		 * DoorModel tar emot ett paint objekt som �r d�rr f�rgen och 
		 * en int som representerar d�rrarna representeras av pos 1-4
		 * 
		 * Informationen anv�nds f�r att tillskriva r�tt d�rr r�tt f�rg
		 */
		
		if(pos == "1") doorTop.setBackgroundColor(col);
		else if(pos == "2") doorTop.setBackgroundColor(col);
		else if(pos == "3") doorTop.setBackgroundColor(col);
		else if(pos == "4") doorTop.setBackgroundColor(col);
		else System.out.println("Faulty string to DoorModel");
		
	}
			
	
}

