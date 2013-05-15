package se.androidsquad.coloristance;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;

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
		setContentView(R.layout.firstscreen);
					
		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music);	
		mp.start();
		mp.setLooping(true);
		MapModel.setPos(0, 1);

		
		//the code below in onClick is a test piece
		ImageButton a = (ImageButton) findViewById(R.id.top_door);
		a.setOnClickListener(new View.OnClickListener() {
			//String test = "34001";
			
			@Override
			public void onClick(View v) {
					
				MapModel.moveUp();

//				if(test == "34001"){ 
//					test = "14234";
//				}
//				
//				else if(test == "14234")
//					test = "23141";
//				
//				else{ 
//					test = "34001";
//				};
				
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());
				findViewById(R.id.top_door).setBackgroundColor(DoorModel.getDoor('N'));
				findViewById(R.id.right_door).setBackgroundColor(DoorModel.getDoor('E'));
				findViewById(R.id.bot_door).setBackgroundColor(DoorModel.getDoor('S'));
				findViewById(R.id.left_door).setBackgroundColor(DoorModel.getDoor('W'));
				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
												
				//MapModel.setMap(Levels.Level1);	//Testar MapModel
				//MapModel.renderMap();			//Testar MapModel
							
			}
		});

		
		// Right door
		ImageButton b = (ImageButton) findViewById(R.id.right_door);
		b.setOnClickListener(new View.OnClickListener() {
			String test = "34001";
			
			@Override
			public void onClick(View v) {
						
				MapModel.moveRight();
//
//				if(test == "34001"){ 
//					test = "14234";
//				}
//				
//				else if(test == "14234")
//					test = "23141";
//				
//				else{ 
//					test = "34001";
//				};
				
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());
				findViewById(R.id.top_door).setBackgroundColor(DoorModel.getDoor('N'));
				findViewById(R.id.right_door).setBackgroundColor(DoorModel.getDoor('E'));
				findViewById(R.id.bot_door).setBackgroundColor(DoorModel.getDoor('S'));
				findViewById(R.id.left_door).setBackgroundColor(DoorModel.getDoor('W'));
				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
												
				//MapModel.setMap(Levels.Level1);	//Testar MapModel
				//MapModel.renderMap();			//Testar MapModel
							
			}
		});
		
		// Bottom door
		ImageButton c = (ImageButton) findViewById(R.id.bot_door);
		c.setOnClickListener(new View.OnClickListener() {
			String test = "34001";
			
			@Override
			public void onClick(View v) {
											
				MapModel.moveDown();

//				if(test == "34001"){ 
//					test = "14234";
//				}
//				
//				else if(test == "14234")
//					test = "23141";
//				
//				else{ 
//					test = "34001";
//				};
//				
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());
				findViewById(R.id.top_door).setBackgroundColor(DoorModel.getDoor('N'));
				findViewById(R.id.right_door).setBackgroundColor(DoorModel.getDoor('E'));
				findViewById(R.id.bot_door).setBackgroundColor(DoorModel.getDoor('S'));
				findViewById(R.id.left_door).setBackgroundColor(DoorModel.getDoor('W'));
				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
												
				//MapModel.setMap(Levels.Level1);	//Testar MapModel
				//MapModel.renderMap();			//Testar MapModel
							
			}
		});
		
		ImageButton d = (ImageButton) findViewById(R.id.left_door);
		d.setOnClickListener(new View.OnClickListener() {

			
			@Override
			public void onClick(View v) {
											
				MapModel.moveLeft();

//				if(test == "34001"){ 
//					test = "14234";
//				}
//				
//				else if(test == "14234")
//					test = "23141";
//				
//				else{ 
//					test = "34001";
//				};
				
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());
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
