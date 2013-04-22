package se.androidsquad.coloristance;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class FirstScreen extends Activity {

	DrawingRect r;
	MediaPlayer mp;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//r = new DrawingRect(FirstScreen.this);
		setContentView(R.layout.firstscreen);
		mp = MediaPlayer.create(FirstScreen.this, R.raw.music);	
		mp.start();
		mp.setLooping(true);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mp.release();

	}
}
