package se.androidsquad.coloristance;

import android.app.Activity;
import android.os.Bundle;

public class FirstScreen extends Activity {

	DrawingRect r;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		r = new DrawingRect(FirstScreen.this);
		setContentView(r);
	}	
}
