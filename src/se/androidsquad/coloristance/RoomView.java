package se.androidsquad.coloristance;

import android.app.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;


public class RoomView extends Activity {

	DrawingRect r;
	MediaPlayer mp;
	DrawMap mapRect;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		r = new DrawingRect(RoomView.this, null);
		mapRect = new DrawMap(RoomView.this, null);
		//setContentView(R.id.room_layout);
		setContentView(R.layout.room_view);
		
		mp = MediaPlayer.create(RoomView.this, R.raw.house_music);	
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
