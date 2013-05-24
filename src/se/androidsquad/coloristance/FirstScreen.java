package se.androidsquad.coloristance;

import java.io.IOException;

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
<<<<<<< HEAD
=======

>>>>>>> cc22c18e612c3a310646285b2b0cf333fafbdffc

public class FirstScreen extends Activity {

	MediaPlayer mp, finish_game;
	DrawMap map;
	DrawKeys drawKeys;
	GameController game;
	//	ToggleButton musicSwitchGame;
	ImageButton musicButton;
	boolean visSpeak; //state of the ImageButton musicButton

	protected int levelCounter = 1;

	int[] door = {R.id.top_door, R.id.right_door, R.id.bot_door,  R.id.left_door};
	int[] keyNames = {R.id.key_button_blue, R.id.key_button_green, R.id.key_button_orange, R.id.key_button_purple, R.id.key_button_red};
	int[] keyImg = {drawable.key_blue, drawable.key_green, drawable.key_orange, drawable.key_purple, drawable.key_red, drawable.key_empty};
	char[] pos = {'N','E','S','W'};
	boolean allocatedInv[] = {false,false,false};

	long startTime = 0;
	long stopTime = 0;

	String timeResult;
	int[] invPos = {R.id.invKeyLeft, R.id.invKeyMid, R.id.invKeyRight};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstscreen);
		startTime();
		
		game = new GameController();
		map = new DrawMap(FirstScreen.this, null);
		drawKeys = new DrawKeys(FirstScreen.this, null);
		

		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music);	
		mp.start();
		mp.setLooping(true);
		MapModel.setPos(0, 1);

		musicButton  = (ImageButton) findViewById(R.id.musicbutton);
		Log.v("MainActivity","value 1: " + musicButton);
		visSpeak = true;
		musicButton.setBackgroundResource(drawable.speaker);
		musicButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(!visSpeak){
					musicButton.setBackgroundResource(drawable.speaker);
					try {
						mp.prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}				 
					mp.start();
					mp.setLooping(true);
					visSpeak = true;

				}else{
					musicButton.setBackgroundResource(drawable.mutespeaker);
					try {
						mp.prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}				 
					mp.start();
					mp.pause();	
					visSpeak = false;
				}

			}
		});

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
		ImageButton keyBlue = (ImageButton) findViewById(R.id.key_button_blue);
		ImageButton keyGreen = (ImageButton) findViewById(R.id.key_button_green);
		ImageButton keyOrange = (ImageButton) findViewById(R.id.key_button_orange);
		ImageButton keyPurple = (ImageButton) findViewById(R.id.key_button_purple);
		ImageButton keyRed = (ImageButton) findViewById(R.id.key_button_red);



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

				if(MapModel.getRoom()=="90000"){
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
				dropKey(2);
				setKeys();

			}
		});

		invLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				Log.v("FirstScreen", "Left was clicked");
				dropKey(0);
				setKeys();

			}
		});
		invMid.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				Log.v("FirstScreen", "Mid was clicked");
				dropKey(1);
				setKeys();

			}
		});

		keyBlue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setInventory(0);
				setKeys();
			}
		});
		keyGreen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setInventory(1);
				setKeys();
			}
		});
		keyOrange.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setInventory(2);
				setKeys();

			}
		});
		keyPurple.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setInventory(3);
				setKeys();

			}
		});
		keyRed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setInventory(4);
				setKeys();

			}
		});

	}

	protected void setInventory(int pos){


		//Set the requested drawable key
		int clickedKey = keyImg[pos];

		//Inventory position to put key at
		int putAtPosition = 0;

		boolean placed = false;

		//AllocatedInv checks the three spots a key could fit in and put it at the first available
		for(int i =0; i<3;i++){
			if(allocatedInv[i] == false && placed == false){
				findViewById(invPos[i]).setBackgroundResource(clickedKey);
				putAtPosition = i;
				Log.v("FirstScreen", "Key put in: "+i);
				placed =true;
				allocatedInv[i] = true;
			}
			else if (allocatedInv[i] == true && placed == false){
				Log.v("FirstScreen", "Spot: "+i+" was full.");
			}
			else Log.v("FirstScreen", "Key has values: "+allocatedInv[i]+" and "+placed);

		}

		char[] buffer = GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString().toCharArray();
		Log.v("FirstScreen", "input setInventory : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());
		Log.v("FirstScreen", "buffer setInventory 1: " + buffer[0]+ buffer[1]+ buffer[2]+ buffer[3]+ buffer[4]);
		Log.v("FirstScreen", "set InvPos: " + GameController.inv.getInv(putAtPosition));
		buffer[GameController.inv.getInv(putAtPosition)] = '0';
		String newKey = new String(buffer);
		GameController.key[MapModel.getMyX()][MapModel.getMyY()].setKeyString(newKey);
		findViewById(invPos[putAtPosition]).setBackgroundResource(keyImg[pos]);
		GameController.inv.setInv(putAtPosition, pos);

		Log.v("FirstScreen", "newKey: " + newKey);
	}

	protected void dropKey(int keyInvPos){

		int emptyInventory = keyImg[5];
		//int pos = 9;
		boolean droped = false;

		for(int i =0; i<3; i++){
			if(keyInvPos == i && droped == false){
				findViewById(invPos[i]).setBackgroundResource(emptyInventory);
				//pos = i;
				droped = true;
			}
			else if(keyInvPos == i && droped == true){
				Log.v("FirstScreen", "The key has been dropped: " + i);
			}
			else Log.v("FirstScreen", "Something went wrong");

		}

		allocatedInv[keyInvPos] = false;

		char[] buffer = GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString().toCharArray();
		Log.v("FirstScreen", "input : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());
		Log.v("FirstScreen", "buffer 1: " + buffer[0]+ buffer[1]+ buffer[2]+ buffer[3]+ buffer[4]);
		Log.v("FirstScreen", "InvPos: " + GameController.inv.getInv(keyInvPos));
		buffer[GameController.inv.getInv(keyInvPos)] = '1';
		String newKey = new String(buffer);
		GameController.key[MapModel.getMyX()][MapModel.getMyY()].setKeyString(newKey);
		findViewById(keyNames[GameController.inv.getInv(keyInvPos)]).setVisibility(View.VISIBLE);
		GameController.inv.setInv(keyInvPos, 5);
		Log.v("FirstScreen", "input : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());

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
		stopTime();
		showTime();
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(this.getText(R.string.finished)+"\t"+"You finished in: "+ timeResult +" seconds");
		//alertDialog.setTitle(showTime());
		LayoutInflater inflater = this.getLayoutInflater();

		mp.stop();
		finish_game = MediaPlayer.create(FirstScreen.this, R.raw.super_mario_complete);	
		finish_game.start();
		//		finish_game.setLooping(true);

		View dialogView = inflater.inflate(R.layout.finish, null);
		alertDialog.setView(dialogView);

		View closeButton=dialogView.findViewById(R.id.endGame);

		closeButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.endGame)
					startActivity(new Intent(FirstScreen.this, MainActivity.class));
				finish_game.stop();
			}
		});

		View playNextLevel= dialogView.findViewById(R.id.playNextLevel);
		playNextLevel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.playNextLevel)
					levelCounter++;
				GameController.setLevel(levelCounter);
				startActivity(new Intent(FirstScreen.this, FirstScreen.class));
				finish_game.stop();
			}
		});

		AlertDialog finishDialog = alertDialog.create();
		finishDialog.show();
	}

	//	int i = showTime();
	private void startTime() {
		startTime = System.currentTimeMillis();		
	}

	private void stopTime() {
		stopTime = System.currentTimeMillis();		
	}

	private void showTime(){
		long totalTime = (stopTime - startTime)/1000;
		timeResult = Long.toString(totalTime);
		//		int i = (int) totalTime/1000;
		//		String s = Integer.toString(i);
		//		showTime=s;
		//		return timeResult;
	}



	@Override
	protected void onResume() {
		super.onResume();
		musicButton = (ImageButton) findViewById(R.id.musicbutton);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music);

		musicButton = (ImageButton) findViewById(R.id.musicbutton);
		musicButton.setBackgroundResource(drawable.mutespeaker);


	}

	protected void onPause() {
		super.onPause();
		mp.release();
	}

	protected void onStop() {
		super.onStop();
		mp.release();
		visSpeak = false;
	}
}


