package se.androidsquad.coloristance;

import java.io.IOException;

import se.androidsquad.coloristance.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/*
 * This class is the main window which the current room is created. It sets the screen to firstscreen.xml,
 * contains the code for the music, and the information about the doors that the different rooms should contain and
 * the color of the doors.
 */


public class FirstScreen extends Activity {

	MediaPlayer mp, finish_game;
	DrawMap map;
	DrawKeys drawKeys;
	GameController game;
	//	ToggleButton musicSwitchGame;
	ImageButton musicButton;
	KeyModel thisKey;
	boolean visSpeak; //state of the ImageButton musicButton
	Runnable runnable;
	protected int levelCounter = 1;
	CountDownTimer countDown;

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

		final TextView textTimer = (TextView) findViewById(R.id.texttime);		
		class CountDown extends CountDownTimer {
			public CountDown(long millisInFuture, long countDownInt){
				super(millisInFuture, countDownInt);
			}

			@Override
			public void onFinish() {
				textTimer.setText("GAME OVER");
				gameLost();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				textTimer.setText((millisUntilFinished/1000)+ "");
			}
		}
		
		final CountDown timer = new CountDown(10000,1000);
		
		musicButton  = (ImageButton) findViewById(R.id.musicbutton);
		Log.v("MainActivity","value 1: " + musicButton);
		visSpeak = true;
		musicButton.setBackgroundResource(drawable.speaker);
		musicButton.setOnClickListener(new View.OnClickListener(){
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

		final ImageButton topDoor = (ImageButton) findViewById(R.id.top_door);
		final ImageButton rightDoor = (ImageButton) findViewById(R.id.right_door);
		final ImageButton botDoor = (ImageButton) findViewById(R.id.bot_door);
		final ImageButton leftDoor = (ImageButton) findViewById(R.id.left_door);
		final ImageButton invLeft = (ImageButton) findViewById(R.id.invKeyLeft);
		final ImageButton invMid = (ImageButton) findViewById(R.id.invKeyMid);
		final ImageButton invRight = (ImageButton) findViewById(R.id.invKeyRight);
		final ImageButton keyBlue = (ImageButton) findViewById(R.id.key_button_blue);
		final ImageButton keyGreen = (ImageButton) findViewById(R.id.key_button_green);
		final ImageButton keyOrange = (ImageButton) findViewById(R.id.key_button_orange);
		final ImageButton keyPurple = (ImageButton) findViewById(R.id.key_button_purple);
		final ImageButton keyRed = (ImageButton) findViewById(R.id.key_button_red);
		final View[] keys = {keyBlue, keyGreen, keyOrange, keyPurple, keyRed};
		final View[] inventories = {invLeft, invMid, invRight};
		final String[] whatKey = {"Left was clicked","Mid as clicked","Right was clicked"}; 



		//Top door
		topDoor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
	
				MapModel.moveUp();
				//game.doorClick(); //Check need
				setRoom();
				setKeys();
				setDoors();
				timer.start();
				
				if(MapModel.getRoom()=="70000"){
					timer.cancel();
					mapDone();
				}
			}
		});

		// Right door
		rightDoor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				MapModel.moveRight();

				setRoom();
				setKeys();
				setDoors();
				timer.start();
				
				if(MapModel.getRoom()=="90000"){
					timer.cancel();
					mapDone();
					
				}
			}
		});

		// Bottom door
		botDoor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				MapModel.moveDown();
		
				setRoom();
				setKeys();
				setDoors();
				timer.start();
			
				if(MapModel.getRoom()=="90000"){
					timer.cancel();
					mapDone();
				}
			}
		});

		//Left door
		leftDoor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			
				MapModel.moveLeft();
			
				setRoom();
				setKeys();
				setDoors();
				timer.start();
			}
		});

//		invRight.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//				Log.v("FirstScreen", "Right was clicked");
//				dropKey(2);
//				
//
//			}
//		});
//
//		invLeft.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//
//				Log.v("FirstScreen", "Left was clicked");
//				dropKey(0);
//			
//
//			}
//		});

		View.OnClickListener inventoryClick = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				for(int i =0; i<3; i++){
				if(inventories[i].equals(v) == true && i == 0){Log.v("FirstScreen", whatKey[i]);}
				else if(inventories[i].equals(v) == true && i == 1){Log.v("FirstScreen", whatKey[i]);}
				else if(inventories[i].equals(v) == true && i == 2){Log.v("FirstScreen", whatKey[i]);}
				
					dropKey(i);
				}
					
			

			}
		};
		
		for(int i = 0; i<3;i++){
			inventories[i].setOnClickListener(inventoryClick);
		}

		
		View.OnClickListener keyClick = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int clickedKeyColor = 6;
				for(int i = 0; i<5; i++){
					if(keys[i].equals(v) == true){clickedKeyColor = i;}
				}
				setInventory(clickedKeyColor);
				

			}
		};
		
		for(int i = 0; i<5; i++){
			keys[i].setOnClickListener(keyClick);
		}

	}

	protected void setInventory(int keyPos){
		//Set inventory takes 0-4, in the variable keyPos
		
		//Set the requested drawable key
		int clickedKey = keyImg[keyPos];
		
		//Inventory position to put key at, if something isn't declared 9 will make it go out of bounds.
		int invPosition = 9;
		
		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];
		
		boolean placed = false;
		char[] buffer = thisKey.getKeyString().toCharArray();
		String newKey = new String(buffer);
		Log.v("FirstScreen", "setInventory newKey init: " + newKey);

		//AllocatedInv checks the three spots a key could fit in and put it at the first available
		for(int i =0; i<3;i++){
			if(allocatedInv[i] == false && placed == false){
				findViewById(invPos[i]).setBackgroundResource(clickedKey);
				invPosition = i;
				Log.v("FirstScreen", "Key put in: "+i);
				
				placed =true;
				allocatedInv[i] = true;
				
				if(keyPos != 5){
					buffer[keyPos] = '0';
					Log.v("FirstScreen", "Tried to set 0");
				}
				
				newKey = new String(buffer);
				thisKey.setKeyString(newKey);
				thisKey.setKeyVisibility(false);
				findViewById(invPos[invPosition]).setBackgroundResource(keyImg[keyPos]);
				GameController.inv.setInv(invPosition, keyPos);
				
				setKeys();
			}
			else if (allocatedInv[i] == true && placed == false){
				Log.v("FirstScreen", "Spot: "+i+" was full.");
			}
			else Log.v("FirstScreen", "Key has value has been placed");

		}

		Log.v("FirstScreen", "setInventory newKey ending: " + newKey);
		
/*		Log.v("FirstScreen", "input setInventory : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());
		Log.v("FirstScreen", "buffer setInventory 1: " + buffer[0]+ buffer[1]+ buffer[2]+ buffer[3]+ buffer[4]);
		Log.v("FirstScreen", "set InvPos: " + GameController.inv.getInv(putAtPosition));
		Log.v("FirstScreen", "newKey: " + newKey);*/

	}

	protected void dropKey(int invPosition){
		//keyInvPos takes 0-2
		
		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];

		int emptyInventory = keyImg[5];
		char[] buffer = thisKey.getKeyString().toCharArray();
		String newKey = new String(buffer);
		Log.v("FirstScreen", "DropKey newKey init: " + newKey);
					
		for(int i =0; i<3; i++){
			if(invPosition == i && allocatedInv[i] == true){
				findViewById(invPos[i]).setBackgroundResource(emptyInventory);
				allocatedInv[i] = false;
				if(GameController.inv.getInv(invPosition)!= 5){
					buffer[GameController.inv.getInv(invPosition)] = '1';
					Log.v("FirstScreen", "Tried to set 1");
				}

				newKey = new String(buffer);
				thisKey.setKeyString(newKey);
				thisKey.setKeyVisibility(true);
				GameController.inv.setInv(invPosition, 5);
				
				setKeys();
			}
			else if(invPosition == i && allocatedInv[i] == false){
				Log.v("FirstScreen", "The key has been dropped/Was never there: " + i);
			}
			else Log.v("FirstScreen", "Something went wrong");
		}
		
		Log.v("FirstScreen", "DropKey newKey ending: " + newKey);
		
		/*Log.v("FirstScreen", "input : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());
		Log.v("FirstScreen", "buffer 1: " + buffer[0]+ buffer[1]+ buffer[2]+ buffer[3]+ buffer[4]);
		Log.v("FirstScreen", "InvPos: " + GameController.inv.getInv(keyInvPos));
		Log.v("FirstScreen", "input : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());*/

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
				currentView.setVisibility(View.INVISIBLE);
			}
		}	
	}

	protected void setKeys(){
		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];
		
		for(int i = 0; i<5; i++){
			if(thisKey.getKeyString().charAt(i) == '1'){
				Log.v("Firstscreen", i+ " Its visible:" + thisKey.getImg());
				findViewById(keyNames[thisKey.getImg()]).setVisibility(View.VISIBLE);
			}
			else if(thisKey.getKeyString().charAt(i) == '0'){
				Log.v("Firstscreen", i+ " Instansiering:" + "Nope, nothing");
				findViewById(keyNames[i]).setVisibility(View.INVISIBLE);
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

	// The method that is called when you fail to move from a room in under 10 seconds.
	// Currently some very similar code as the method above, as they treat similar scenarios
	protected void gameLost(){ 
		mp.stop();
		stopTime();
		AlertDialog.Builder loseDialog = new AlertDialog.Builder(this);
		loseDialog.setTitle(R.string.lost_game);
		LayoutInflater inflater = this.getLayoutInflater();
		
		View loseView = inflater.inflate(R.layout.lose, null);
		loseDialog.setView(loseView);
		
		View retryButton=loseView.findViewById(R.id.retry);
		retryButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.retry)
					startActivity(new Intent(FirstScreen.this, FirstScreen.class));
			}
		});

		View playAgainButton=loseView.findViewById(R.id.endGame);
		playAgainButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.endGame)
					startActivity(new Intent(FirstScreen.this, MainActivity.class));
			}
		});
		AlertDialog gameOverDialog = loseDialog.create();
		gameOverDialog.show();
		
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


