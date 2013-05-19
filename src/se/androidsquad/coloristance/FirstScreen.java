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

	int[] door = {R.id.top_door, R.id.right_door, R.id.bot_door,  R.id.left_door};
	int[] keyNames = {R.id.key_button_blue, R.id.key_button_green, R.id.key_button_orange, R.id.key_button_purple, R.id.key_button_red};
	char[] pos = {'N','E','S','W'};
	int index;


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

		/*
		 * the following four ImageButtons represent our doors that enables a player to move between the
		 * rooms on the map.
		 */

		ImageButton a = (ImageButton) findViewById(R.id.top_door);
		a.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				MapModel.moveUp();
				//game.doorClick(); At the moment we are not using this code line :)
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());

				index = Integer.parseInt(""+MapModel.getRoom().charAt(0));
				findViewById(keyNames[index]).setVisibility(GameController.key[MapModel.getMyX()][MapModel.getMyY()].isVisible);
				findViewById(keyNames[index]).setBackgroundColor(GameController.key[MapModel.getMyX()][MapModel.getMyY()].color);

				for(int z = 0; z<4; z++){
					findViewById(door[z]).setVisibility(View.VISIBLE); //sets the visibility of the door to VISIBLE when it is initialized
					findViewById(door[z]).setBackgroundColor(DoorModel.getDoor(pos[z]));//sets the door color to the color of the room it is connected to
					if(DoorModel.getDoor(pos[z]) == RectModel.BLACK){ // if the room has no connection defined by no room or a blach rectangle this code sets the visibility to GONE.
						findViewById(door[z]).setVisibility(View.GONE);
					}
				}

				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
			}



		});


		// Right door
		ImageButton b = (ImageButton) findViewById(R.id.right_door);
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				MapModel.moveRight();
				//game.doorClick();At the moment we are not using this code line :)
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());

				for(int z = 0; z<4; z++){
					findViewById(door[z]).setVisibility(View.VISIBLE);
					findViewById(door[z]).setBackgroundColor(DoorModel.getDoor(pos[z]));
					if(DoorModel.getDoor(pos[z]) == RectModel.BLACK){
						findViewById(door[z]).setVisibility(View.GONE);
					}
				}

				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());

			}
		});

		// Bottom door
		ImageButton c = (ImageButton) findViewById(R.id.bot_door);
		c.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				MapModel.moveDown();
				//game.doorClick();At the moment we are not using this code line :)
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());

				for(int z = 0; z<4; z++){
					findViewById(door[z]).setVisibility(View.VISIBLE);
					findViewById(door[z]).setBackgroundColor(DoorModel.getDoor(pos[z]));
					if(DoorModel.getDoor(pos[z]) == RectModel.BLACK){
						findViewById(door[z]).setVisibility(View.GONE);
					}
				}

				findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
			}
		});

		ImageButton d = (ImageButton) findViewById(R.id.left_door);
		d.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {

				MapModel.moveLeft();
				//game.doorClick();At the moment we are not using this code line :)
				DoorModel.setDoor(MapModel.getRoom());
				RectModel.setRectColor(MapModel.getRoom());

				for(int z = 0; z<4; z++){
					findViewById(door[z]).setVisibility(View.VISIBLE);
					findViewById(door[z]).setBackgroundColor(DoorModel.getDoor(pos[z]));
					if(DoorModel.getDoor(pos[z]) == RectModel.BLACK){
						findViewById(door[z]).setVisibility(View.GONE);
					}
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
