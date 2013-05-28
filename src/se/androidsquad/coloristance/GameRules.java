package se.androidsquad.coloristance;

import se.androidsquad.coloristance.R;
import android.app.Activity;
import android.os.Bundle;

/**
 * This class is an activity that initiates the gamerules.xml which can be accessed from the main screen
 */
public class GameRules extends Activity{

	
	/**
	 * When the class is called the onCreate sets the view to gamerules.xml
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.gamerules);
	}
}
