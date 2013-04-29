package se.androidsquad.coloristance;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;

public class FirstScreen extends Activity {

	DrawingRect r;
	MediaPlayer mp;
	DrawMapRect mapRect;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		r = new DrawingRect(FirstScreen.this, null);
		mapRect = new DrawMapRect(FirstScreen.this, null);
		//setContentView(R.id.room_layout);
		setContentView(R.layout.firstscreen);
		
		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music);	
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
