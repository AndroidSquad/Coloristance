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
	MainActivity main;
	ImageButton musicButton;
	KeyModel thisKey;
	int visSpeak; //state of the ImageButton musicButton, 0 = not playing, 1 = is playing, 2 = not defined
	Runnable runnable;
	protected int levelCounter = 1;

	int[] door = {R.id.top_door, R.id.right_door, R.id.bot_door,  R.id.left_door};
	int[] keyNames = {R.id.key_button_blue, R.id.key_button_green, R.id.key_button_orange, R.id.key_button_purple, R.id.key_button_red};
	int[] keyImg = {drawable.key_blue, drawable.key_green, drawable.key_orange, drawable.key_purple, drawable.key_red, drawable.key_empty};

	char[] pos = {'N','E','S','W'};
	boolean allocatedInv[] = {false,false,false};

	long startTime, stopTime, playedTime, savedTime;
	long roomStartTime, roomStopTime, roomPlayedTime, roomSavedTime;

	String timeResult;
	int[] invPos = {R.id.invKeyLeft, R.id.invKeyMid, R.id.invKeyRight};

	TextView textTimer;		
	CountDown timer, timerRotation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
			// Restore value of members from saved state
			visSpeak = savedInstanceState.getInt("visiblespeaker");
			savedTime = savedInstanceState.getLong("savedtime");
			roomSavedTime =savedInstanceState.getLong("roomsavedtime");
			startTime = System.currentTimeMillis();
			Log.d("Simon h‰lsar","innan if");
			Log.d("Simon h‰lsar",""+MapModel.getMyX()+MapModel.getMyY());
			if((MapModel.getMyX() != 0) || (MapModel.getMyY() != 1)){
				Log.d("Simon h‰lsar","if rad 1");
				roomStartTime = System.currentTimeMillis();
				timerRotation = new CountDown (10000 - (roomSavedTime*1000),1000);
				Log.d("Simon h‰lsar","if rad 2");
				timerRotation.start();
				Log.d("Simon h‰lsar","funkar ej");
				timer = new CountDown(10000,1000);
				Log.d("Simon hälsar","Vi räknar ner på nytt");
			}
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




		int x= MapModel.getMyX();
		int y= MapModel.getMyY();
		if(x==0 && y == 0){
			MapModel.setPos(0, 1);
		}
		else {
			MapModel.setPos(x,y);

		}

		setRoom();//is needed to get the right room when you start a new level or tilt the screen
		setDoors();// is needed to get the corresponding doors to the right room when a new level is started or screen is tilted

		musicButton  = (ImageButton) findViewById(R.id.musicbutton);

		mp = MediaPlayer.create(FirstScreen.this, R.raw.house_music);	

		if (visSpeak == 2){
			mp.start();
			mp.setLooping(true);
			Log.d("Mafi", "VisSpeak FIRST value is " + visSpeak);
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
				Log.d("Mafi", "VisSpeak Value before if " + visSpeak);
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
					Log.d("Mafi", "VisSpeak Value AFTER if " + visSpeak);

				}else{
					musicButton.setBackgroundResource(drawable.mutespeaker);
					//					try {
					//						mp.prepare();
					//					} catch (IllegalStateException e) {
					//						e.printStackTrace();
					//					} catch (IOException e) {
					//						e.printStackTrace();
					//					}				 
					//					mp.start();
					mp.pause();	
					visSpeak = 0;
					Log.d("Mafi", "VisSpeak Value AFTER if " + visSpeak);

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
		final View[] doors = {topDoor, rightDoor, botDoor, leftDoor};
		final String[] whatKey = {"Left was clicked","Mid was clicked","Right was clicked"}; 

		View.OnClickListener doorClick = new View.OnClickListener(){

			@Override
			public void onClick(View v) {

				if(doors[0].equals(v)== true){
					for(int i = 0; i<3;i++){
						if((allocatedInv[i] && DoorModel.getDoorColorNr(0) == GameController.inv.getInv(i))|| DoorModel.getDoorColorNr(0) == 6){
							MapModel.moveUp();
							Log.v("FirstScreen", "Up");
							if(timerRotation!=null)
								timerRotation.cancel();
							timer.start();
							Log.d("Simon hälsar","ny tid i rummet");
							roomStartTime = System.currentTimeMillis();
							break;
						}
						else if (DoorModel.getDoorColorNr(0) == 5){
							MapModel.moveUp();
						}
					}
				}
				else if(doors[1].equals(v)== true){
					for(int i = 0; i<3;i++){
						if(allocatedInv[i] && DoorModel.getDoorColorNr(1) == GameController.inv.getInv(i)){
							MapModel.moveRight();
							Log.v("FirstScreen", "Right");
							if(timerRotation!=null)
								timerRotation.cancel();
							timer.start();
							roomStartTime = System.currentTimeMillis();
							Log.d("Simon hälsar","ny tid i rummet");
							break;
						}
						else if (DoorModel.getDoorColorNr(1) == 5){
							MapModel.moveRight();
						}
					}
				}
				else if(doors[2].equals(v)== true){
					for(int i = 0; i<3;i++){
						if(allocatedInv[i] && DoorModel.getDoorColorNr(2) == GameController.inv.getInv(i)){
							MapModel.moveDown();
							Log.v("FirstScreen", "Down");
							if(timerRotation!=null)
								timerRotation.cancel();
							timer.start();
							Log.d("Simon hälsar","ny tid i rummet");
							roomStartTime = System.currentTimeMillis();
							break;
						}
						else if (DoorModel.getDoorColorNr(2) == 5){
							MapModel.moveDown();
						}
					}
				}
				else if(doors[3].equals(v)== true){
					for(int i = 0; i<3;i++){
						if(allocatedInv[i] && DoorModel.getDoorColorNr(3) == GameController.inv.getInv(i)){
							MapModel.moveLeft();
							Log.v("FirstScreen", "Left");
							if(timerRotation!=null)
								timerRotation.cancel();
							timer.start();
							Log.d("Simon hälsar","ny tid i rummet");
							roomStartTime = System.currentTimeMillis();
							break;
						}
						else if (DoorModel.getDoorColorNr(3) == 5){
							MapModel.moveLeft();
						}
					}
				}

				setRoom();
				setKeys();
				setDoors();


				if(MapModel.getRoom()=="70000"){
					Log.v("FirstScreen", "Aset var h‰r ‰ndÂ");
					timer.cancel();
					mapDone();
				}
			}

		};


		for(int i = 0; i<4; i++){
			doors[i].setOnClickListener(doorClick);
		}

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
					Log.v("FirstScreen", "Set 0");
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

	/** 
	 * onSaveInstanceState saves valueble information that should not be lost in a screen rotation
	 * */


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("visiblespeaker",visSpeak);
		outState.putLong("savedtime", savedTime);
		outState.putLong("roomsavedtime", roomSavedTime);
		Log.d("Mafi","Visible speaker state: " + visSpeak + " saved");
	}

	protected void dropKey(int invPosition){
		//keyInvPos takes 0-2

		thisKey = GameController.key[MapModel.getMyX()][MapModel.getMyY()];
		int emptyInventory = keyImg[5];
		char[] buffer = thisKey.getKeyString().toCharArray();
		String newKey = new String(buffer);
		Log.v("FirstScreen", "DropKey newKey init: " + newKey);

		Log.v("FirstScreen", ""+allocatedInv[0]+""+allocatedInv[1]+""+allocatedInv[2]);
		int keyPos = GameController.inv.getInv(invPosition);

		if(newKey.charAt(keyPos) == '1'){
			//TODO Visa ett snabbt felmeddelande att nyckeln redan finns i rummet
			Log.v("FirstScreen", "The key already exist in the room");
		}
		else if(allocatedInv[invPosition] == true ){
			findViewById(invPos[invPosition]).setBackgroundResource(emptyInventory);
			allocatedInv[invPosition] = false;
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
		else if(allocatedInv[invPosition] == false){
			Log.v("FirstScreen", "The key has been dropped/Was never there: " + invPosition);
		}
		else{ Log.v("FirstScreen", "Something went wrong");}

		Log.v("FirstScreen", "DropKey newKey ending: " + newKey);
	}

	/*Log.v("FirstScreen", "input : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());
		Log.v("FirstScreen", "buffer 1: " + buffer[0]+ buffer[1]+ buffer[2]+ buffer[3]+ buffer[4]);
		Log.v("FirstScreen", "InvPos: " + GameController.inv.getInv(keyInvPos));
		Log.v("FirstScreen", "input : " + GameController.key[MapModel.getMyX()][MapModel.getMyY()].getKeyString());*/



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


	protected void mapDone(){
		getPlayedTime();
		showTime();
		MapModel.setPos(0,1); //TODO
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(this.getText(R.string.finished)+"\t"+" You finished in: "+ timeResult +" seconds" );
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
				if(clicked.getId() == R.id.endGame){
					levelCounter=1;
					GameController.setLevel(levelCounter);
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish_game.stop();
					finish();
					cleanInventory();
				}
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
				cleanInventory();
			}
		});

		AlertDialog finishDialog = alertDialog.create();
		finishDialog.show();
	}

	// The method that is called when you fail to move from a room in under 10 seconds.
	// Currently some very similar code as the method above, as they treat similar scenarios
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
					MapModel.setPos(0,1);
					startActivity(new Intent(FirstScreen.this, FirstScreen.class));
					levelCounter = GameController.getLevel();
					cleanInventory();
				}	
			}
		});

		View playAgainButton=loseView.findViewById(R.id.endGame);
		playAgainButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View clicked){
				if(clicked.getId() == R.id.endGame){
					levelCounter=1;
					GameController.setLevel(levelCounter);
					MapModel.setPos(0,1);
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish();
					cleanInventory();
				}
			}
		});
		AlertDialog gameOverDialog = loseDialog.create();
		gameOverDialog.show();

	}


	@Override
	public void onBackPressed() {   
		new AlertDialog.Builder(this)
	           .setMessage("Do you want to exit already?")
	           .setCancelable(true)
	           .setNegativeButton("No", null)
	           .setNeutralButton("Main screen", new DialogInterface.OnClickListener(){
	        	   public void onClick(DialogInterface dialog, int i){
	        		   FirstScreen.this.finish();
	        		   startActivity(new Intent(getApplicationContext(), MainActivity.class));
	        		   cleanInventory();
	        	   }
	           })
	           .setPositiveButton("Restart",new DialogInterface.OnClickListener(){
	        	   public void onClick(DialogInterface dialog, int i){
	        		   MapModel.setPos(0,1);
	        		   FirstScreen.this.finish();
	        		   cleanInventory();
//	        		   main.resumeButton.setVisibility(View.VISIBLE);
	        		   Intent intent = new Intent(getApplicationContext(), FirstScreen.class);
//					   intent.putExtra("makeButtonVisible",true);
	        		   startActivity(intent);
	        		  
	        	   }
	           })
	           .show();
	}


	private long getPlayedTime() {
		stopTime = System.currentTimeMillis();
		Log.d("Mafi","" + "stopTime-startTime milli" + (stopTime-startTime));
		Log.d("Mafi","savedTime" + savedTime);
		return playedTime = (stopTime - startTime)/1000 + savedTime;
	}


	private void showTime(){
		playedTime = getPlayedTime();
		timeResult = Long.toString(playedTime);
		//		int i = (int) totalTime/1000;
		//		String s = Integer.toString(i);
		//		showTime=s;
		//		return timeResult;
	}


	private long getPlayedRoomTime() {
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
		if (timerRotation != null) {
			timerRotation.cancel();
		}
		Log.d("Mafi","" + savedTime + " innan");
		savedTime = getPlayedTime();
		Log.d("Mafi","" + savedTime + " efter");
		roomStopTime = System.currentTimeMillis();
		roomSavedTime = getPlayedRoomTime();
		Log.d("Simon hälsar","roomsavedtime är "+roomSavedTime);
	}

	protected void onStop() {
		super.onStop();
		mp.release();
		visSpeak = 0;
		timer.cancel();
	}


	class CountDown extends CountDownTimer{

		public CountDown(long millisInFuture, long countDownInterval){
			super(millisInFuture,countDownInterval);
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
	private void cleanInventory(){
		for(int i = 0; i<3;i++){
			dropKey(i);
		}
	}
}


