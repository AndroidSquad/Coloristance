package se.androidsquad.coloristance;

import android.app.Activity;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


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
		
		
		
		
		ImageButton a = (ImageButton) findViewById(R.id.top_door);
		a.setOnClickListener(new View.OnClickListener() {
			String test;	
			@Override
			public void onClick(View v) {
							
				if(test == "22222") test = "11111"; 
				else test = "22222";
				
				DoorModel.setDoor(test);
				findViewById(R.id.bot_door).setBackgroundColor(DoorModel.getDoor(2));

				
			}
		});
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mp.release();

	}
	
	
}
