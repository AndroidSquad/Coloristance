package se.androidsquad.coloristance;

import java.io.IOException;

import se.androidsquad.coloristance.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
 * This class sets the screen to firstscreen.xml. If the orienation is in landscape mode, a landscape version
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

	MediaPlayer mp, finish_game;
	DrawMap map;
	DrawKeys drawKeys;
	GameController game;
	MainActivity main;
	ImageButton musicButton;
	KeyModel thisKey;
	int visSpeak; //state of the ImageButton musicButton, 0 = not playing, 1 = is playing, 2 = not defined
	Runnable runnable;
	protected static int levelCounter = 1; //variable that keeps track of which level is to be played

	int[] door = {R.id.top_door, R.id.right_door, R.id.bot_door,  R.id.left_door}; 
	int[] keyNames = {R.id.key_button_blue, R.id.key_button_green, R.id.key_button_orange, R.id.key_button_purple, R.id.key_button_red};
	int[] keyImg = {drawable.key_blue, drawable.key_green, drawable.key_orange, drawable.key_purple, drawable.key_red, drawable.key_empty};

	char[] pos = {'N','E','S','W'};
//	boolean allocatedInv[] = {false,false,false};

	long startTime, stopTime, playedTime, savedTime; //variables used for counting the total time it takes for a player to finish a level
	long roomStartTime, roomStopTime, roomPlayedTime, roomSavedTime; //variables used for keeping track of the countdown time in each room
	String timeResult; //a String representing the total time for completing a level

	int[] invPos = {R.id.invKeyLeft, R.id.invKeyMid, R.id.invKeyRight};

	TextView textTimer;		
	CountDown timer, timerRotation; //two separate instances of the private class CountDown, 
	//used to handle the count down in each room. 
	//The second variable handles the count down in the case of a change of orientation 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
			// Restore value of members from saved state
			visSpeak = savedInstanceState.getInt("visiblespeaker");
			savedTime = savedInstanceState.getLong("savedtime");
			roomSavedTime =savedInstanceState.getLong("roomsavedtime");
			startTime = System.currentTimeMillis();
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
			} else //If the player is in the first room, the other instance of CountDown is not needed
				timer = new CountDown(10000,1000); 
		} else {
			visSpeak = 2;
			startTime = System.currentTimeMillis();
			savedTime = 0;
			timer = new CountDown(10000,1000);
		}

		setContentView(R.layout.firstscreen);
		findViewById(R.id.bot_layout).setBackgroundColor(RectModel.BLUE_DARK);

		textTimer = (TextView) findViewById(R.id.texttime);		

		game = new GameController();
		map = new DrawMap(FirstScreen.this, null);
		drawKeys = new DrawKeys(FirstScreen.this, null);

		//Variables used to keep track of the player's position in the map
		int x= MapModel.getMyX();
		int y= MapModel.getMyY();
		if(x==0 && y == 0){
			MapModel.setPos(0, 1);
		}
		else {
			MapModel.setPos(x,y);
		}

		setRoom();	//is needed to get the right room when you start a new level or tilt the screen
		setDoors();	// is needed to get the corresponding doors to the right room when a new level is started 
		//or screen is tilted

		musicButton  = (ImageButton) findViewById(R.id.musicbutton); //graphical representation of the "speaker" in
		//the lower left corner, signalling if music is being player or not		

		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music); 	

		if (visSpeak == 2){
			mp.start();
			mp.setLooping(true);
			Log.v("Mafi", "VisSpeak FIRST value is " + visSpeak);
			visSpeak = 1;
			musicButton.setBackgroundResource(drawable.speaker);
		}

		else if(visSpeak ==1){
			musicButton.setBackgroundResource(drawable.speaker);
			mp.start();
			mp.setLooping(true);
		}
		else
			musicButton.setBackgroundResource(drawable.mutespeaker);

		musicButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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

				}else{
					musicButton.setBackgroundResource(drawable.mutespeaker);
					mp.pause();	
					visSpeak = 0;
					Log.v("FirstScreen", "VisSpeak Value AFTER if " + visSpeak);

				}

			}
		});

		//Looping what inital keys to show in the inventory
		for(int i= 0; i<3; i++){
			findViewById(invPos[i]).setBackgroundResource(keyImg[GameController.inv.getInv()[i]]);
		}

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
		final String[] whatKey = {"Left was clicked","Mid was clicked","Right was clicked"}; 
		//		TODO Används denna idag?

		View.OnClickListener doorClick = new View.OnClickListener(){

			@Override
			public void onClick(View v) {

				/*The following four if statements handle if a player is allowed into a new room or not, based
				 *if the player has the necessary keys in the inventory
				 */
				for(int doorCount = 0; doorCount<4 ;doorCount++){
					if(doors[doorCount].equals(v)== true){
						for(int i = 0; i<3;i++){
							if((InventoryModel.alloc[i] && DoorModel.getDoorColorNr(doorCount) == GameController.inv.getInv(i))|| DoorModel.getDoorColorNr(doorCount) == 5){
								if(doorCount == 0){
									MapModel.moveUp();
									Log.v("FirstScreen", "Up");
								}
								else if(doorCount == 1){
									MapModel.moveRight();
									Log.v("FirstScreen", "Right");
								}
								else if(doorCount == 2){
									MapModel.moveDown();
									Log.v("FirstScreen", "Down");
								}
								else if(doorCount == 3){
									MapModel.moveLeft();
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
								break;
							}
						}
					}

					//The room, keys and doors have to be set for the new room
					setRoom();
					setKeys();
					setDoors();

					//If the player is in the last room of the map, the method mapDone() is called which ends the level
					if(MapModel.getRoom()=="70000"){
						Log.v("FirstScreen", "Timer cancled");
						timer.cancel();
						mapDone();
					}
				}
			}
		};




		for(int i = 0; i<4; i++){
			doors[i].setOnClickListener(doorClick);
		}

		//Handles the clicks on the inventory
		View.OnClickListener inventoryClick = new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				for(int i =0; i<3; i++){
					if(inventories[i].equals(v) == true && i == 0){dropKey(i);}
					else if(inventories[i].equals(v) == true && i == 1){dropKey(i);}
					else if(inventories[i].equals(v) == true && i == 2){dropKey(i);}
				}

			}
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
					if(keys[i].equals(v) == true){clickedKeyColor = i;}
				}
				setInventory(clickedKeyColor);
			}
		};
		for(int i = 0; i<5; i++){
			keys[i].setOnClickListener(keyClick);
		}

	}

	/**
	 * Sets the inventory on the screen.
	 * @param int keyPos, values between 0 - 4 
	 */

	protected void setInventory(int keyPos){

		//Set the requested drawable key
		int clickedKey = keyImg[keyPos];

		//Inventory position to put key at. If something is not declared, 9 will make it go out of bounds.
		int invPosition = 9;

		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];
		// TODO vad gör egentligen denna?

		boolean placed = false;
		char[] buffer = thisKey.getKeyString().toCharArray();
		String newKey = new String(buffer);
		Log.v("FirstScreen", "setInventory newKey init: " + newKey);

		//AllocatedInv checks the three spots a key could fit in and put it at the first available
		for(int i =0; i<3;i++){
			boolean allocated = InventoryModel.alloc[i];
			if(allocated == false && placed == false){
				findViewById(invPos[i]).setBackgroundResource(clickedKey);
				invPosition = i;
				Log.v("FirstScreen", "Key put in: "+i);

				placed =true;
				allocated = true;

				if(keyPos != 5){
					buffer[keyPos] = '0';
					Log.v("FirstScreen", "Set 0");
				}

				newKey = new String(buffer);
				thisKey.setKeyString(newKey);
				thisKey.setKeyVisibility(false);
				findViewById(invPos[invPosition]).setBackgroundResource(keyImg[keyPos]);
				GameController.inv.setInv(invPosition, keyPos);

				setKeys();
			}
			else if (allocated == true && placed == false){
				Log.v("FirstScreen", "Spot: "+i+" was full.");
			}
			else{
				Log.v("FirstScreen", "Key has value has been placed");
			}
			
			InventoryModel.alloc[i] = allocated;
		}

		Log.v("FirstScreen", "setInventory newKey ending: " + newKey);

		/*		Log.v("FirstScreen", "input setInventory : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());
		Log.v("FirstScreen", "buffer setInventory 1: " + buffer[0]+ buffer[1]+ buffer[2]+ buffer[3]+ buffer[4]);
		Log.v("FirstScreen", "set InvPos: " + GameController.inv.getInv(putAtPosition));
		Log.v("FirstScreen", "newKey: " + newKey);*/

	}

	/** 
	 * onSaveInstanceState saves valuable information that should not be lost in a screen rotation
	 * */


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("visiblespeaker",visSpeak);
		outState.putLong("savedtime", savedTime);
		outState.putLong("roomsavedtime", roomSavedTime);
		Log.v("Mafi","Visible speaker state: " + visSpeak + " saved");
	}

	protected void dropKey(int invPosition){
		//keyInvPos takes 0-2

		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];
		int emptyInventory = keyImg[5];
		char[] buffer = thisKey.getKeyString().toCharArray();
		String newKey = new String(buffer);
		int keyPos = GameController.inv.getInv(invPosition);
		boolean alloc = InventoryModel.alloc[invPosition];
		Log.v("FirstScreen", "DropKey newKey init: " + newKey);
		Log.v("FirstScreen", "Allocations: "+InventoryModel.alloc);
		

		if(newKey.charAt(keyPos) == '1'){
			//TODO Visa ett snabbt felmeddelande att nyckeln redan finns i rummet
			Log.v("FirstScreen", "The key already exist in the room");
		}
		else if(alloc == true ){
			findViewById(invPos[invPosition]).setBackgroundResource(emptyInventory);
			alloc = false;
			if(keyPos != 5){
				buffer[keyPos] = '1';
				Log.v("FirstScreen", "Set 1");

			}

			newKey = new String(buffer);
			thisKey.setKeyString(newKey);
			thisKey.setKeyVisibility(true);
			GameController.inv.setInv(invPosition, 5);

			setKeys();
		}
		else if(alloc== false){
			Log.v("FirstScreen", "The key has been dropped/Was never there: " + invPosition);
		}
		else{ 
			Log.v("FirstScreen", "Something went wrong when calling dropKey");
		}
		
		InventoryModel.alloc[invPosition] = alloc;

		Log.v("FirstScreen", "DropKey newKey ending: " + newKey);
	}

	/*Log.v("FirstScreen", "input : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());
		Log.v("FirstScreen", "buffer 1: " + buffer[0]+ buffer[1]+ buffer[2]+ buffer[3]+ buffer[4]);
		Log.v("FirstScreen", "InvPos: " + GameController.inv.getInv(keyInvPos));
		Log.v("FirstScreen", "input : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());*/
	//TODO

	/**
	 * This method controls that DoorModel appoints the correct doors to each room
	 * This method controls that the room is set to its corresponding color. 
	 */
	protected void setRoom(){
		DoorModel.setDoor(MapModel.getRoom());
		RectModel.setRectColor(MapModel.getRoom());
		findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
	}

	/**
	 * This method handles that the doors are appointed to the right locations in a room
	 * If a door is initially defined as being black, then that door is made invisible
	 */
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

	/**
	 * This method defines the keys within a room as being visible or invisible, depending on what keys
	 * are supposed to be in a room, as defined in the database (Levels.java)
	 */

	protected void setKeys(){
		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];

		for(int i = 0; i<5; i++){
			if(thisKey.getKeyString().charAt(i) == '1'){
				thisKey.setKeyImg(i);
				Log.v("Firstscreen", i+ " Its visible:" + thisKey.getImg());
				findViewById(keyNames[thisKey.getImg()]).setVisibility(View.VISIBLE);
			}
			else if(thisKey.getKeyString().charAt(i) == '0'){
				Log.v("Firstscreen","Instansiering:" + "Nope, nothing" + i);
				findViewById(keyNames[i]).setVisibility(View.INVISIBLE);
			}
			else Log.v("Firstscreen", "Incorrect input");
		}	
	}

	/**
	 * Cleans the current inventory, so that no keys are displayed as being the inventory
	 */
	private void cleanInventory(){
		for(int i = 0; i<3;i++){
			dropKey(i);
		}
	}

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
			}
		});

		View closeButton=dialogView.findViewById(R.id.endGame);
		closeButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.endGame){
					finish_game.stop();
					endGame();
				}
			}	
		});
		
		View retryButton=dialogView.findViewById(R.id.doneRetry);
		retryButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.doneRetry){
					finish_game.stop();
					retryLevel();
				}	
			}
		});

		AlertDialog finishDialog = alertDialog.create();
		finishDialog.setCanceledOnTouchOutside(false);
		finishDialog.show();
	}

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
			}
		});

		View playAgainButton=loseView.findViewById(R.id.endGame);
		playAgainButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.endGame){
					endGame();
				}
			}
		});
		AlertDialog gameOverDialog = loseDialog.create();
		gameOverDialog.setCanceledOnTouchOutside(false);
		gameOverDialog.show();

	}

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
	           .setNeutralButton(R.string.end_game, new DialogInterface.OnClickListener(){
	        	   public void onClick(DialogInterface dialog, int i){
	        		   endGame();

	        	   }
	           })
	           .setPositiveButton(R.string.try_again,new DialogInterface.OnClickListener(){
	        	   public void onClick(DialogInterface dialog, int i){
	        		   retryLevel();
	        	   }
	           })
	           .show();
	}

	/**
	 * This method handles what happens if the user presses the button "Play next level". The next level is 
	 * returned with the correspding rooms, doors and keys. A new activity with the next level is started, and 
	 * the current inventory is cleaned.
	 */
	
	public void playNextLevel() {
		levelCounter++;
		GameController.setLevel(levelCounter);
		startActivity(new Intent(FirstScreen.this, FirstScreen.class));
		cleanInventory();
	}
	
	/**
	 * This method handles a user pressing the button "Main menu". The current activity is finished, the 
	 * variable levelCounter is set to prepare for the first level, and a new activity for the main menu is
	 * processed. 
	 */
	
	public void endGame() {
		levelCounter = 1;
		GameController.setLevel(levelCounter);
		finish();
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //All previous activities are cleared
		startActivity(intent);
		cleanInventory();
	}
	
	/**
	 * This method handles when the user presses the button "Try again". A new activity with the same level is 
	 * initiated.
	 */
	
	public void retryLevel(){
		MapModel.setPos(0,1);
		startActivity(new Intent(FirstScreen.this, FirstScreen.class));
		levelCounter = GameController.getLevel();
		cleanInventory();
	}

	/**
	 * Returns the total time for the player to complete a level, in seconds
	 * @return long playedTime 
	 * TODO är detta rätt sätt med Javadoc @param?
	 */

	private long getPlayedTime() {
		stopTime = System.currentTimeMillis();
		Log.v("Mafi","" + "stopTime-startTime milli" + (stopTime-startTime));
		Log.v("Mafi","savedTime" + savedTime);
		return playedTime = (stopTime - startTime)/1000 + savedTime;
	}

	/**
	 * Presents the variable playedTime as a String that can be showed to the user
	 */
	private void showTime(){
		playedTime = getPlayedTime();
		timeResult = Long.toString(playedTime);
		//		int i = (int) totalTime/1000; TODO
		//		String s = Integer.toString(i);
		//		showTime=s;
		//		return timeResult;
	}


	/**
	 * Returns the time the player has spent in the current room, in seconds
	 * @return long roomPlayedTime
	 */
	private long getRoomPlayedTime() {
		roomStopTime = System.currentTimeMillis();
		return roomPlayedTime = (roomStopTime - roomStartTime)/1000;
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
		if (timerRotation != null) {//Sets timerRotation to cancel its countdown, so not to interfere with the instance 'timer'
			timerRotation.cancel();
		}
		Log.v("Mafi","" + savedTime + " innan");
		savedTime = getPlayedTime();
		Log.v("Mafi","" + savedTime + " efter");
		roomStopTime = System.currentTimeMillis(); 
		roomSavedTime = getRoomPlayedTime();
		Log.v("FirstScreen","roomsavedtime Šr "+roomSavedTime);
	}

	protected void onStop() {
		super.onStop();
		mp.release();
		visSpeak = 0;
		timer.cancel();
	}


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
			textTimer.setText("0");//TODO ska detta tas bort?
			gameLost();
		}

		//Each second, the text in the textTimer TextView is updated with the next number in the count down
		@Override
		public void onTick(long millisUntilFinished) {
			textTimer.setText((millisUntilFinished/1000)+ "");
		}
	}	
	

	
}


