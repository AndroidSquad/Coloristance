package se.androidsquad.coloristance;

import se.androidsquad.coloristance.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
/*
 * This class is the main window which the current room is created. It sets the screen to firstscreen.xml,
 * contains the code for the music, and the information about the doors that the different rooms should contain and
 * the color of the doors.
 */

public class FirstScreen extends Activity {

	MediaPlayer mp;
	DrawMap map;
	GameController game = new GameController();

	int[] door = {R.id.top_door, R.id.right_door, R.id.bot_door,  R.id.left_door};
	int[] keyNames = {R.id.key_button_blue, R.id.key_button_green, R.id.key_button_orange, R.id.key_button_purple, R.id.key_button_red};
	int[] keyImg = {drawable.key_blue, drawable.key_green, drawable.key_orange, drawable.key_purple, drawable.key_red, drawable.key_empty};
	char[] pos = {'N','E','S','W'};

	int[] invPos = {R.id.invKey1, R.id.invKey2, R.id.invKey3};

	private Activity context;	//N�dv�ndig??
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		map = new DrawMap(FirstScreen.this, null);
		setContentView(R.layout.firstscreen);

		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music);	
		mp.start();
		mp.setLooping(true);
		MapModel.setPos(0, 1);

		//Looping what inital keys to show in the inventory
		for(int i= 0; i<3; i++){
			findViewById(invPos[i]).setBackgroundResource(keyImg[GameController.inv.getInv()[i]]);

		}

		/*
		 * the following four ImageButtons represent our doors that enables a player to move between the
		 * rooms on the map.
		 */

		ImageButton topDoor = (ImageButton) findViewById(R.id.top_door);
		ImageButton rightDoor = (ImageButton) findViewById(R.id.right_door);
		ImageButton botDoor = (ImageButton) findViewById(R.id.bot_door);
		ImageButton leftDoor = (ImageButton) findViewById(R.id.left_door);

		topDoor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				MapModel.moveUp();
				//game.doorClick(); //Check need
				setRoom();
				setKeys();
				setDoors();
				setInventory();
			}

		});


		// Right door

		rightDoor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				MapModel.moveRight();

				//game.doorClick(); //Check need
				setRoom();
				setKeys();
				setDoors();
				setInventory();
							
				if(MapModel.getRoom()=="70000"){
					mapDone();
				}
			}
		});

		// Bottom door

		botDoor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				MapModel.moveDown();
				//game.doorClick(); //Check need
				setRoom();
				setKeys();
				setDoors();
				setInventory();
			}
		});


		leftDoor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				MapModel.moveLeft();
				//game.doorClick(); //Check need

				setRoom();
				setKeys();
				setDoors();
				setInventory();
			}

		});

	}

	@Override
	protected void onPause() {
		super.onPause();
		mp.release();

	}

	public void setRoom(){
		DoorModel.setDoor(MapModel.getRoom());
		RectModel.setRectColor(MapModel.getRoom());
		findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
	}

	public void setDoors(){
		for(int z = 0; z<4; z++){
			findViewById(door[z]).setVisibility(View.VISIBLE);
			findViewById(door[z]).setBackgroundColor(DoorModel.getDoor(pos[z]));
			if(DoorModel.getDoor(pos[z]) == RectModel.BLACK){
				findViewById(door[z]).setVisibility(View.GONE);
			}
		}	
	}

	public void setKeys(){
		for(int i = 0; i<5; i++){
			if(GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString().charAt(i) == '1'){
				Log.v("Firstscreen", "Its visible:" + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getImg());
				findViewById(keyNames[GameController.key[MapModel.getMyX()][MapModel.getMyY()].getImg()]).setVisibility(View.VISIBLE);
			}
			else if(GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString().charAt(i) == '0'){
				Log.v("Firstscreen", "Instansiering:" + "Nope, nothing");
				findViewById(keyNames[i]).setVisibility(View.GONE);
			}
			else Log.v("Firstscreen", "Faulty input");
		}	
	}

	public void setInventory(){

	}
	
	public void mapDone(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle(context.getText(R.string.finished));
		LayoutInflater inflater = context.getLayoutInflater();
		View view = inflater.inflate(R.layout.finish, null);
		alertDialog.setView(view);
//		View closeButton=view.findViewById(R.id.closeGame);
//		closeButton.setOnClickListener(new OnClickListener() {
//		
//			public void onClick(View clicked){
//				if(clicked.getId() == R.id.closeGame)
//					
//			}
//		});
		AlertDialog finishDialog = alertDialog.create();
		finishDialog.show();
	}



}
