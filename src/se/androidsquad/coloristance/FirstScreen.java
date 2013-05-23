package se.androidsquad.coloristance;

import se.androidsquad.coloristance.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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
	GameController game;
	protected int levelCounter = 1;

	int[] door = {R.id.top_door, R.id.right_door, R.id.bot_door,  R.id.left_door};
	int[] keyNames = {R.id.key_button_blue, R.id.key_button_green, R.id.key_button_orange, R.id.key_button_purple, R.id.key_button_red};
	int[] keyImg = {drawable.key_blue, drawable.key_green, drawable.key_orange, drawable.key_purple, drawable.key_red, drawable.key_empty};
	char[] pos = {'N','E','S','W'};
	int[] invPos = {R.id.invKeyLeft, R.id.invKeyMid, R.id.invKeyRight};



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		game = new GameController();
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
		ImageButton invLeft = (ImageButton) findViewById(R.id.invKeyLeft);
		ImageButton invMid = (ImageButton) findViewById(R.id.invKeyMid);
		ImageButton invRight = (ImageButton) findViewById(R.id.invKeyRight);

		//Top door
		topDoor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//				
				//				currentKey =  GameController.key[currentX][currentY];
				//				currentKeyColorPos = currentKey.getImg();
				//				currentKeyImgId = keyNames[currentKeyColorPos];

				MapModel.moveUp();
				//game.doorClick(); //Check need
				setRoom();
				setKeys();
				setDoors();

				if(MapModel.getRoom()=="70000"){
					mapDone();
				}
			}

		});




		// Right door
		rightDoor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				//				currentKey =  GameController.key[currentX][currentY];
				//				currentKeyColorPos = currentKey.getImg();
				//				currentKeyImgId = keyNames[currentKeyColorPos];

				MapModel.moveRight();

				//game.doorClick(); //Check need
				setRoom();
				setKeys();
				setDoors();


				if(MapModel.getRoom()=="70000"){
					mapDone();
				}
			}
		});

		// Bottom door
		botDoor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				//				currentKey =  GameController.key[currentX][currentY];
				//				currentKeyColorPos = currentKey.getImg();
				//				currentKeyImgId = keyNames[currentKeyColorPos];

				MapModel.moveDown();
				//game.doorClick(); //Check need
				setRoom();
				setKeys();
				setDoors();

				if(MapModel.getRoom()=="70000"){
					mapDone();
				}

			}
		});

		//Left door
		leftDoor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				//				currentKey =  GameController.key[currentX][currentY];
				//				currentKeyColorPos = currentKey.getImg();
				//				currentKeyImgId = keyNames[currentKeyColorPos];
				//				
				MapModel.moveLeft();
				//game.doorClick(); //Check need
				setRoom();
				setKeys();
				setDoors();
			}
		});


		invRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				Log.v("FirstScreen", "Right was clicked");
				dropKey("right");
				setKeys();

			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		mp.release();

	}

	protected void dropKey(String keyInvPos){

		int emptyInventory = keyImg[5];
		int pos;

		if(keyInvPos == "right"){

			findViewById(invPos[2]).setBackgroundResource(emptyInventory);
			pos = 0;
		}
		else if(keyInvPos == "left"){
			findViewById(invPos[1]).setBackgroundResource(emptyInventory);
			pos = 1;
		}
		else if(keyInvPos == "mid"){
			findViewById(invPos[0]).setBackgroundResource(emptyInventory);
			pos = 2;
		}
		else{
			Log.v("FirstScreen", "The key couldn't be dropped");
		}


		//		else{
		//			Log.v("FirstScreen", "The inventory spot was empty");
		//		}

		String updateKeys = GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString();
		Log.v("FirstScreen", "updateKeys: " + updateKeys);
		char[] bufferForKey = new char[7];


		updateKeys.getChars(0, 4, bufferForKey, 0);
		Log.v("FirstScreen", "chars: " + bufferForKey.toString());
		bufferForKey[GameController.key[MapModel.getMyX()][MapModel.getMyY()].getImg()] = 1;
		Log.v("FirstScreen", "chars after: " + bufferForKey.toString());
		GameController.key[MapModel.getMyX()][MapModel.getMyY()].setKeyString(bufferForKey.toString());

	}


	protected void setRoom(){
		DoorModel.setDoor(MapModel.getRoom());
		RectModel.setRectColor(MapModel.getRoom());
		findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
	}

	protected void setDoors(){
		for(int z = 0; z<4; z++){
			View currentView = findViewById(door[z]);

			currentView.setVisibility(View.VISIBLE);
			currentView.setBackgroundColor(DoorModel.getDoor(pos[z]));
			if(DoorModel.getDoor(pos[z]) == RectModel.BLACK){
				currentView.setVisibility(View.GONE);
			}
		}	
	}

	protected void setKeys(){
		for(int i = 0; i<5; i++){
			if(GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString().charAt(i) == '1'){
				Log.v("Firstscreen", i+ " Its visible:" + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getImg());
				findViewById(keyNames[GameController.key[MapModel.getMyX()][MapModel.getMyY()].getImg()]).setVisibility(View.VISIBLE);
			}
			else if(GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString().charAt(i) == '0'){
				Log.v("Firstscreen", i+ " Instansiering:" + "Nope, nothing");
				findViewById(keyNames[i]).setVisibility(View.GONE);
			}
			else Log.v("Firstscreen", "Incorrect input");
		}	
	}

	protected void mapDone(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(this.getText(R.string.finished));
		LayoutInflater inflater = this.getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.finish, null);
		alertDialog.setView(dialogView);

		View closeButton=dialogView.findViewById(R.id.endGame);
		closeButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.endGame)
					startActivity(new Intent(FirstScreen.this, MainActivity.class));
			}
		});

		View playNextLevel= dialogView.findViewById(R.id.playNextLevel);
		playNextLevel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.playNextLevel)
					levelCounter++;
				GameController.setLevel(levelCounter);
				startActivity(new Intent(FirstScreen.this, FirstScreen.class));
			}
		});
		AlertDialog finishDialog = alertDialog.create();
		finishDialog.show();
	}



}
