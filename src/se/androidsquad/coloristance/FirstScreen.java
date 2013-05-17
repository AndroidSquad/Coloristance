package se.androidsquad.coloristance;

import android.app.Activity;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;


public class FirstScreen extends Activity {

	MediaPlayer mp;
	DrawMap map;
	GameController game = new GameController();

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		map = new DrawMap(FirstScreen.this, null);
		setContentView(R.layout.firstscreen);
					
		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music);	
		mp.start();
		mp.setLooping(true);
		MapModel.setPos(0, 1);

		final View N = findViewById(R.id.top_door);
		final View E = findViewById(R.id.right_door);
		final View S = findViewById(R.id.bot_door);
		final View W = findViewById(R.id.left_door);
	
		
		//the code below in onClick is a test piece
		ImageButton a = (ImageButton) findViewById(R.id.top_door);
		a.setOnClickListener(new View.OnClickListener() {
			//String test = "34001";
			
			@Override
			public void onClick(View v) {
				N.setVisibility(View.VISIBLE);
				E.setVisibility(View.VISIBLE);
				S.setVisibility(View.VISIBLE);
				W.setVisibility(View.VISIBLE);
				MapModel.moveUp();

				game.doorClick();
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());
				N.setBackgroundColor(DoorModel.getDoor('N'));
				if(DoorModel.getDoor('N') == RectModel.BLACK){
					N.setVisibility(View.GONE);
				}
				E.setBackgroundColor(DoorModel.getDoor('E'));
				if(DoorModel.getDoor('E') == RectModel.BLACK){
					E.setVisibility(View.GONE);
				}
				S.setBackgroundColor(DoorModel.getDoor('S'));
				if(DoorModel.getDoor('S') == RectModel.BLACK){
					S.setVisibility(View.GONE);
				}
				W.setBackgroundColor(DoorModel.getDoor('W'));
				if(DoorModel.getDoor('W') == RectModel.BLACK){
					W.setVisibility(View.GONE);
				}
				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());			
			}
		});

		
		// Right door
		ImageButton b = (ImageButton) findViewById(R.id.right_door);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				N.setVisibility(View.VISIBLE);
				E.setVisibility(View.VISIBLE);
				S.setVisibility(View.VISIBLE);
				W.setVisibility(View.VISIBLE);
						
				MapModel.moveRight();
				
				game.doorClick();
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());
				N.setBackgroundColor(DoorModel.getDoor('N'));
				if(DoorModel.getDoor('N') == RectModel.BLACK){
					N.setVisibility(View.GONE);
				}
				E.setBackgroundColor(DoorModel.getDoor('E'));
				if(DoorModel.getDoor('E') == RectModel.BLACK){
					E.setVisibility(View.GONE);
				}
				S.setBackgroundColor(DoorModel.getDoor('S'));
				if(DoorModel.getDoor('S') == RectModel.BLACK){
					S.setVisibility(View.GONE);
				}
				W.setBackgroundColor(DoorModel.getDoor('W'));
				if(DoorModel.getDoor('W') == RectModel.BLACK){
					W.setVisibility(View.GONE);
				}
				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
							
			}
		});
		
		// Bottom door
		ImageButton c = (ImageButton) findViewById(R.id.bot_door);
		c.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				N.setVisibility(View.VISIBLE);
				E.setVisibility(View.VISIBLE);
				S.setVisibility(View.VISIBLE);
				W.setVisibility(View.VISIBLE);
				
				MapModel.moveDown();
				game.doorClick();
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());
				N.setBackgroundColor(DoorModel.getDoor('N'));
				if(DoorModel.getDoor('N') == RectModel.BLACK){
					N.setVisibility(View.GONE);
				}
				E.setBackgroundColor(DoorModel.getDoor('E'));
				if(DoorModel.getDoor('E') == RectModel.BLACK){
					E.setVisibility(View.GONE);
				}
				S.setBackgroundColor(DoorModel.getDoor('S'));
				if(DoorModel.getDoor('S') == RectModel.BLACK){
					S.setVisibility(View.GONE);
				}
				W.setBackgroundColor(DoorModel.getDoor('W'));
				if(DoorModel.getDoor('W') == RectModel.BLACK){
					W.setVisibility(View.GONE);
				}
				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
							
			}
		});
		
		ImageButton d = (ImageButton) findViewById(R.id.left_door);
		d.setOnClickListener(new View.OnClickListener() {

			
			@Override
			public void onClick(View v) {
				N.setVisibility(View.VISIBLE);
				E.setVisibility(View.VISIBLE);
				S.setVisibility(View.VISIBLE);
				W.setVisibility(View.VISIBLE);
				
				MapModel.moveLeft();
				
				game.doorClick();
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());
				N.setBackgroundColor(DoorModel.getDoor('N'));
				if(DoorModel.getDoor('N') == RectModel.BLACK){
					N.setVisibility(View.GONE);
				}
				E.setBackgroundColor(DoorModel.getDoor('E'));
				if(DoorModel.getDoor('E') == RectModel.BLACK){
					E.setVisibility(View.GONE);
				}
				S.setBackgroundColor(DoorModel.getDoor('S'));
				if(DoorModel.getDoor('S') == RectModel.BLACK){
					S.setVisibility(View.GONE);
				}
				W.setBackgroundColor(DoorModel.getDoor('W'));
				if(DoorModel.getDoor('W') == RectModel.BLACK){
					W.setVisibility(View.GONE);
				}
				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
							
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
