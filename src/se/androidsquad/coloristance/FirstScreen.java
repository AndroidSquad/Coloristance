package se.androidsquad.coloristance;

import android.app.Activity;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class FirstScreen extends Activity {

	MediaPlayer mp;
	DrawMap map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		map = new DrawMap(FirstScreen.this, null);
		//setContentView(R.id.room_layout);
		setContentView(R.layout.firstscreen);
		
		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music);	
		mp.start();
		mp.setLooping(true);
		
		//Nedan onClick är teststycke
		ImageButton a = (ImageButton) findViewById(R.id.top_door);
		a.setOnClickListener(new View.OnClickListener() {
			String test = "31423";	
			@Override
			public void onClick(View v) {
							
				if(test == "14234"){ 
					test = "31223";
				}
				else if(test == "31223"){
					test = "24123";
				}
				else test = "14234";
				
				DoorModel.setDoor(test);
				RectModel.setRectColor(test);
				findViewById(R.id.top_door).setBackgroundColor(DoorModel.getDoor('N'));
				findViewById(R.id.right_door).setBackgroundColor(DoorModel.getDoor('E'));
				findViewById(R.id.bot_door).setBackgroundColor(DoorModel.getDoor('S'));
				findViewById(R.id.left_door).setBackgroundColor(DoorModel.getDoor('W'));
				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
				
				//MapModel.setMap(Levels.Level1);	//Testar MapModel
				//MapModel.renderMap();			//Testar MapModel
												
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
