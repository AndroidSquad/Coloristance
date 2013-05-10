package se.androidsquad.coloristance;

import android.widget.Button;

public class DoorModel {

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
}
