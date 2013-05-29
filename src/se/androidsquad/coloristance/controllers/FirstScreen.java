package se.androidsquad.coloristance.controllers;

import java.io.IOException;
import se.androidsquad.coloristance.R;
import se.androidsquad.coloristance.R.drawable;
import se.androidsquad.coloristance.models.*;
import se.androidsquad.coloristance.views.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * This class is the main window which the current room is created. 
 * This class is responsible for the majority of the communication with the other classes. This class acts 
 * both as a View and a Controller. 
 * 
 * This class sets the screen to firstscreen.xml. If the orientation is in landscape mode, a landscape version
 * of firstscreen.xml is used. This class sends information to DrawMap to retrieve information from the 
 * database (Levels.java) which level is to be played. This class handles information regarding the room and its
 * related doors. It also handles the color of the inventory stack at the bottom of the screen.
 * 
 * This class handles the logic of how the player is allowed to move between rooms, and also handles the 
 * keys in the player's inventory. This class also contains the code for the music, as well as the two separate 
 * timers running in the game: the first timer counts the total time it takes for a player to finish a whole 
 * level, and the other timer counts down from 10 seconds in each room.
 *   
 */

public class FirstScreen extends Activity {

	MediaPlayer mp, finish_game, doorAccess, doorAccessDenied;
	DoorView doorV;
	KeyView keyV;
	public static InventoryView invV = null;
	MapDrawerView mapV;
	RoomView roomV;
	GameController game;
	MainActivity main;
	ImageButton musicButton;
	KeyModel thisKey;
	Runnable runnable;
	TextView textTimer;		
	CountDown timer, timerRotation; //two separate instances of the private class CountDown,
	public static boolean turn = false;

	//used to handle the count down in each room. 
	//The second variable handles the count down in the case of a change of orientation 
	int visSpeak; //state of the ImageButton musicButton, 0 = not playing, 1 = is playing, 2 = not defined
	protected static int levelCounter = 1; //variable that keeps track of which level is to be played
	int[] invPos = {R.id.invKeyLeft, R.id.invKeyMid, R.id.invKeyRight};
	long startTime, stopTime, playedTime, savedTime; //variables used for counting the total time it takes for a player to finish a level
	long roomStartTime, roomStopTime, roomPlayedTime, roomSavedTime; //variables used for keeping track of the countdown time in each room
	String timeResult; //a String representing the total time for completing a level

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.firstscreen);
		findViewById(R.id.bot_layout).setBackgroundColor(RectModel.BLUE_DARK);
		
		game = new GameController();
		mapV = new MapDrawerView(FirstScreen.this, null);
		doorV = new DoorView(this);
		keyV = new KeyView(this);
		invV = new InventoryView(this, keyV);
		roomV = new RoomView(this);
		
		if (savedInstanceState != null) {
			// Restore value of members from saved state
			turn = true;
			visSpeak = savedInstanceState.getInt("visiblespeaker");
			savedTime = savedInstanceState.getLong("savedtime");
			roomSavedTime =savedInstanceState.getLong("roomsavedtime");
			startTime = System.currentTimeMillis();
			roomV.setRoom();
			keyV.setKeys();
			doorV.setDoors();
			Log.v("FirstScreen","innan if");
			Log.v("FirstScreen",""+MapModel.getMyX()+MapModel.getMyY());
			if((MapModel.getMyX() != 0) || (MapModel.getMyY() != 1)){ //The timer count down is not supposed to start in the first room
				Log.v("FirstScreen","if rad 1");
				roomStartTime = System.currentTimeMillis();
				timerRotation = new CountDown (10000 - (roomSavedTime*1000),1000);
				Log.v("FirstScreen","if rad 2");
				timerRotation.start();
				Log.v("FirstScreen","funkar ej");
				timer = new CountDown(10000,1000);
				Log.v("FirstScreen","Vi räknar ner på nytt");
			}//if 
			else //If the player is in the first room, the instance timerRotation is not needed, since the countdown does not start in the first room
				timer = new CountDown(10000,1000); 
		}//if 
		else {
			visSpeak = 2;
			startTime = System.currentTimeMillis();
			savedTime = 0;
			timer = new CountDown(10000,1000);
		}//else

		textTimer = (TextView) findViewById(R.id.texttime);		

		//Variables used to keep track of the player's position in the map
		int x= MapModel.getMyX();
		int y= MapModel.getMyY();
		if(x==0 && y == 0){
			MapModel.setPos(0, 1);
		}//if
		else {
			MapModel.setPos(x,y);
		}//else

		//Below is needed to get the right room when you start a new level or tilt the screen
		roomV.setRoom();	
		// Below is needed to get the corresponding doors to the right room when a new level is started or tilted
		//		doorV.setDoors();	

		musicButton  = (ImageButton) findViewById(R.id.musicbutton); //graphical representation of the "speaker" in
		//the lower left corner, signalling if music is being player or not		

		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music); 	

		if (visSpeak == 2){
			mp.start();
			mp.setLooping(true);
			Log.v("FirstScreen", "VisSpeak FIRST value is " + visSpeak);
			visSpeak = 1;
			musicButton.setBackgroundResource(drawable.speaker);
		}//if

		else if(visSpeak ==1){
			musicButton.setBackgroundResource(drawable.speaker);
			mp.start();
			mp.setLooping(true);
		}//else if
		else
			musicButton.setBackgroundResource(drawable.mutespeaker);

		musicButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Log.v("FirstScreen", "VisSpeak Value before if " + visSpeak);
				if(visSpeak==0){
					try {
						mp.prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}				 
					mp.start();
					mp.setLooping(true);
					musicButton.setBackgroundResource(drawable.speaker);
					visSpeak = 1;
					Log.v("FirstScreen", "VisSpeak Value AFTER if " + visSpeak);
				}
				else {
					musicButton.setBackgroundResource(drawable.mutespeaker);
					mp.pause();	
					visSpeak = 0;
					Log.v("FirstScreen", "VisSpeak Value AFTER if " + visSpeak);
				}
			}//onClick
		});//musicButton

		keyV.setStartKeys();

		/*
		 * The following four ImageButtons represent our doors that enables a player to move between the
		 * rooms on the map.
		 */
		final ImageButton topDoor = (ImageButton) findViewById(R.id.top_door);
		final ImageButton rightDoor = (ImageButton) findViewById(R.id.right_door);
		final ImageButton botDoor = (ImageButton) findViewById(R.id.bot_door);
		final ImageButton leftDoor = (ImageButton) findViewById(R.id.left_door);

		/*
		 * The following three ImageButtons represent the three positions in the inventory that are available for 
		 * storing keys
		 */
		final ImageButton invLeft = (ImageButton) findViewById(R.id.invKeyLeft);
		final ImageButton invMid = (ImageButton) findViewById(R.id.invKeyMid);
		final ImageButton invRight = (ImageButton) findViewById(R.id.invKeyRight);

		/*
		 * The following five ImageButtons represent the five different colors of keys that are
		 * available in the game
		 */
		final ImageButton keyBlue = (ImageButton) findViewById(R.id.key_button_blue);
		final ImageButton keyGreen = (ImageButton) findViewById(R.id.key_button_green);
		final ImageButton keyOrange = (ImageButton) findViewById(R.id.key_button_orange);
		final ImageButton keyPurple = (ImageButton) findViewById(R.id.key_button_purple);
		final ImageButton keyRed = (ImageButton) findViewById(R.id.key_button_red);

		final View[] keys = {keyBlue, keyGreen, keyOrange, keyPurple, keyRed};
		final View[] inventories = {invLeft, invMid, invRight};
		final View[] doors = {topDoor, rightDoor, botDoor, leftDoor};

		View.OnClickListener doorClick = new View.OnClickListener(){

			@Override
			public void onClick(View v) {

				/*The following four if statements handle if a player is allowed into a new room or not, based
				 *if the player has the necessary keys in the inventory. It also handles the two door sounds acces and access denied depending 
				 *on if the playaer has access to the door or not. 
				 */
				
				
				for(int doorCount = 0; doorCount<4 ;doorCount++){
					boolean granted = false;
					if(doors[doorCount].equals(v)== true){
						for(int i = 0; i<3;i++){
							if((InventoryModel.alloc[i] && DoorModel.getDoorColorNr(doorCount) == GameController.inv.getInv(i))|| DoorModel.getDoorColorNr(doorCount) == 5){
								
								doorAccess = MediaPlayer.create(FirstScreen.this, R.raw.door_access); 	
								
								if(doorCount == 0){
									MapModel.moveUp();
									doorAccess.start();
									Log.v("FirstScreen", "Up");
								}
								else if(doorCount == 1){
									MapModel.moveRight();
									doorAccess.start();
									Log.v("FirstScreen", "Right");
								}
								else if(doorCount == 2){
									MapModel.moveDown();
									doorAccess.start();
									Log.v("FirstScreen", "Down");
								}
								else if(doorCount == 3){
									MapModel.moveLeft();
									doorAccess.start();
									Log.v("FirstScreen", "Left");
								}
								else{
									Log.v("FirstScreen", "No MapModel.moveX request was made");
								}

								if(timerRotation!=null) 	//If the instance timerRotation is used, this instance has 
									timerRotation.cancel();	//to be canceled in order not to cause problems for the regular timer instance
								timer.start();
								Log.v("FirstScreen","New roomtime set");
								roomStartTime = System.currentTimeMillis();
								granted = true;
								break;
							}//if
							else if(granted == false && i == 2){
								doorAccessDenied = MediaPlayer.create(FirstScreen.this, R.raw.door_access_denied); 	
								doorAccessDenied.start();
							}
//							doorAccess.release();
//							doorAccessDenied.release();
									
						}//for
					}//if

					//The room, keys and doors have to be set for the new room
					roomV.setRoom();
					keyV.setKeys();
					doorV.setDoors();

					//If the player is in the last room of the map, the method mapDone() is called which ends the level
					if(MapModel.getRoom()=="70000"){
						Log.v("FirstScreen", "Timer cancled");
						timer.cancel();
						mapDone();
					}
				}//for
			}//onClick
		};//doorClick




		for(int i = 0; i<4; i++){
			doors[i].setOnClickListener(doorClick);
		}//for

		//Handles the clicks on the inventory
		View.OnClickListener inventoryClick = new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				for(int i =0; i<3; i++){
					if(inventories[i].equals(v) == true && i == 0){keyV.dropKey(i);}
					else if(inventories[i].equals(v) == true && i == 1){keyV.dropKey(i);}
					else if(inventories[i].equals(v) == true && i == 2){keyV.dropKey(i);}
				}

			}//onClick
		};

		for(int i = 0; i<3;i++){
			inventories[i].setOnClickListener(inventoryClick);
		}

		//Handles the clicks on the keys 
		View.OnClickListener keyClick = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int clickedKeyColor = 6;
				for(int i = 0; i<5; i++){
					if(keys[i].equals(v) == true){
						clickedKeyColor = i;
					}
				}

				invV.setInventory(clickedKeyColor);
			}//onClick
		};
		for(int i = 0; i<5; i++){
			keys[i].setOnClickListener(keyClick);
		}

	}//onCreate

	/** 
	 * onSaveInstanceState saves valuable information that should not be lost in a screen rotation
	 * */


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("visiblespeaker",visSpeak);
		outState.putLong("savedtime", savedTime);
		outState.putLong("roomsavedtime", roomSavedTime);
		Log.v("FirstScreen","Visible speaker state: " + visSpeak + " saved");
	}//onSaveInstanceState

	/*Log.v("FirstScreen", "input : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());
		Log.v("FirstScreen", "buffer 1: " + buffer[0]+ buffer[1]+ buffer[2]+ buffer[3]+ buffer[4]);
		Log.v("FirstScreen", "InvPos: " + GameController.inv.getInv(keyInvPos));
		Log.v("FirstScreen", "input : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());*/

	/**
	 * This method is called when the player is in the final room of the level. The method first registers 
	 * and counts the time it took for the player to complete the level. Then the method sets the position of the 
	 * player to the first room in the level, to be able to play again or proceed to the next level. The inventory 
	 * is cleaned to show no keys. 
	 * 
	 *  An AlertDialog is used to present the player with two options: to play the next level or to 
	 *  end their game and return to the main menu. The buttons in this AlertDialog are represented
	 *  in an .xml file named "finish".
	 *  
	 *  Also, the music playing during the level is stopped, and a special melody, signalling that the player 
	 *  has completed the level, is initiated
	 */

	protected void mapDone(){
		getPlayedTime();
		showTime();
		MapModel.setPos(0,1); //TODO vad ska TODOas här??
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(this.getText(R.string.finished)+"\t"+" You finished in: "+ timeResult +" seconds" );
		LayoutInflater inflater = this.getLayoutInflater();


		mp.stop();
		finish_game = MediaPlayer.create(FirstScreen.this, R.raw.super_mario_complete);	
		finish_game.start();
		//		finish_game.setLooping(true);

		View dialogView = inflater.inflate(R.layout.finish, null);
		alertDialog.setView(dialogView);

		View playNextLevel= dialogView.findViewById(R.id.playNextLevel);
		playNextLevel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.playNextLevel)
					finish_game.stop();
				playNextLevel();

			}//onClick
		});//playNextLevel


		View closeButton=dialogView.findViewById(R.id.endGame);
		closeButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.endGame){
					finish_game.stop();
					endGame();
				}
			}//onClick	
		});//closeButton


		View retryButton=dialogView.findViewById(R.id.doneRetry);
		retryButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.doneRetry){
					finish_game.stop();
					retryLevel();
				}	
			}//onClick
		});//retryButton

		AlertDialog finishDialog = alertDialog.create();
		finishDialog.setCanceledOnTouchOutside(false);
		finishDialog.show();
	}//mapDone

	/**
	 * This method is called when the CountDownTimer reaches zero, and the player therefore has failed to move
	 * from a room in under 10 seconds. Similar code to the mapDone(). The music from the level is stopped. The 
	 * inventory is cleaned.
	 * 
	 * An AlertDialog is used to present the player with two options: either to retry the current level, or
	 * to return to the main menu. The buttons in the AlertDialog are represented in a .xml file named "lose"
	 */

	protected void gameLost(){ 
		mp.stop();

		AlertDialog.Builder loseDialog = new AlertDialog.Builder(this);
		loseDialog.setTitle(R.string.lost_game);
		LayoutInflater inflater = this.getLayoutInflater();

		View loseView = inflater.inflate(R.layout.lose, null);
		loseDialog.setView(loseView);

		View retryButton=loseView.findViewById(R.id.retry);
		retryButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.retry){
					retryLevel();
				}	
			}//onClick
		});//retryButton

		View playAgainButton=loseView.findViewById(R.id.endGame);
		playAgainButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.endGame){
					endGame();
				}
			}//onClick
		});//playAgainButton
		AlertDialog gameOverDialog = loseDialog.create();
		gameOverDialog.setCanceledOnTouchOutside(false);
		gameOverDialog.show();

	}//gameLost

	/**
	 * This method handles what happens when the user presses the back button. Using an AlertDialog, the user
	 * is presented with three options: to continue playing, to return to the main screen or to restart 
	 * the current level
	 * (non-Javadoc)
	 * @see android.app.Activity#onBackPressed() TODO Vet inte riktigt vad detta innebär, kom automatiskt
	 */

	@Override
	public void onBackPressed() {   
		new AlertDialog.Builder(this)
		.setMessage(this.getText(R.string.back_pressed))
		.setCancelable(true)
		.setNegativeButton("No", null)
		.setNeutralButton(R.string.main_menu, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int i){
				MainActivity.visResume=true;
				endGame();
			}
		})//.setNeutralButton
		.setPositiveButton(R.string.try_again,new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int i){
				retryLevel();
			}
		})//.setPositiveButton
		.show();
	}//onBackPressed

	/**
	 * This method handles what happens if the user presses the button "Play next level". The next level is 
	 * returned with the correspding rooms, doors and keys. A new activity with the next level is started, and 
	 * the current inventory is cleaned.
	 */

	public void playNextLevel() {
		levelCounter++;
		turn = false;
		GameController.setLevel(levelCounter);
		startActivity(new Intent(FirstScreen.this, FirstScreen.class));
		invV.cleanInventory();
	}//playNextLevel

	/**
	 * This method handles a user pressing the button "Main menu". The current activity is finished, the 
	 * variable levelCounter is set to prepare for the first level, and a new activity for the main menu is
	 * processed. 
	 */

	public void endGame() {
		//GameController.setLevel(levelCounter);
		//finish();
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //All previous activities are cleared
		startActivity(intent);
	}//endGame

	/**
	 * This method handles when the user presses the button "Try again". A new activity with the same level is 
	 * initiated.
	 */

	public void retryLevel(){
		turn = false;
		MapModel.setPos(0,1);
		startActivity(new Intent(FirstScreen.this, FirstScreen.class));
		levelCounter = GameController.getLevel();
		invV.cleanInventory();
	}//retryLevel

	/**
	 * Returns the total time for the player to complete a level, in seconds
	 * @return long playedTime 
	 * TODO är detta rätt sätt med Javadoc @param?
	 */

	private long getPlayedTime() {
		stopTime = System.currentTimeMillis();
		Log.v("FirstScreen","" + "stopTime-startTime milli" + (stopTime-startTime));
		Log.v("FirstScreen","savedTime" + savedTime);
		return playedTime = (stopTime - startTime)/1000 + savedTime;
	}//getPlayedTime

	/**
	 * Presents the variable playedTime as a String that can be showed to the user
	 */
	private void showTime(){
		playedTime = getPlayedTime();
		timeResult = Long.toString(playedTime);
	}//showTime


	/**
	 * Returns the time the player has spent in the current room, in seconds
	 * @return long roomPlayedTime
	 */
	private long getRoomPlayedTime() {
		roomStopTime = System.currentTimeMillis();
		return roomPlayedTime = (roomStopTime - roomStartTime)/1000;
	}//getRoomPlayedTime

	@Override
	protected void onResume() {
		super.onResume();
		musicButton = (ImageButton) findViewById(R.id.musicbutton);
	}//onResume

	@Override
	protected void onRestart() {
		super.onRestart();
		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music);
		musicButton = (ImageButton) findViewById(R.id.musicbutton);
		musicButton.setBackgroundResource(drawable.mutespeaker);
	}//onRestart

	protected void onPause() {
		super.onPause();
		mp.release(); 
		if (timerRotation != null) {//Sets timerRotation to cancel its countdown, so not to interfere with the instance 'timer'
			timerRotation.cancel();
		}
		Log.v("FirstScreen","" + savedTime + " innan");
		savedTime = getPlayedTime();
		Log.v("FirstScreen","" + savedTime + " efter");
		roomStopTime = System.currentTimeMillis(); 
		roomSavedTime = getRoomPlayedTime();
		Log.v("FirstScreen","roomsavedtime Šr "+roomSavedTime);
	}//onPause

	protected void onStop() {
		super.onStop();
		mp.release();
		visSpeak = 0;
		timer.cancel();
	}//onStop


	/**
	 * This private class extends the abstract class CountDownTimer, and handles the countdown that is in
	 * place for every room (except for the first room) of each map. This implementation has overwritten 
	 * two of the methods from the super class: onFinish() and onTick().
	 */
	private class CountDown extends CountDownTimer{

		public CountDown(long millisInFuture, long countDownInterval){
			super(millisInFuture,countDownInterval);
		}

		// Sets the text to Game Over in the textTimer TextView, and calls the gameLost() method
		@Override
		public void onFinish() {	
			textTimer.setText("0");
			gameLost();
		}

		//Each second, the text in the textTimer TextView is updated with the next number in the count down
		@Override
		public void onTick(long millisUntilFinished) {
			textTimer.setText((millisUntilFinished/1000)+ "");
		}
	}//CountDown	
}//FirstScreen


