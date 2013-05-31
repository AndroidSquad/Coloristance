package se.androidsquad.coloristance.controllers;

import se.androidsquad.coloristance.R;
import java.io.IOException;

import se.androidsquad.coloristance.R.drawable;
import se.androidsquad.coloristance.models.MapModel;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * This class represents the first screen of our game. It contains buttons to 
 * create a new game, start the music, pause the music. From this first screen you should
 * be routed to the FirstScreen.xml when you click on the newGame button.
 */

public class StartMenuActivity extends Activity {

	MediaPlayer mp;
	ImageButton musicMenuButton;
	Button resumeButton;
	boolean visMenuSpeak; //state of the ImageButton musicMenuButton
	public static boolean visResume = false; //state of the Button resumeButton

	/**
	 * When the class is called the onCreate sets the view to activity_main.xml, and it also creates different
	 * buttons in order for the player to start a newGame and turn on the music.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GameActivity.turn = false; 

		mp = MediaPlayer.create(StartMenuActivity.this, R.raw.house_music);				 

		Button newGame = (Button) findViewById(R.id.new_game); 
		Button resumeButton = (Button) findViewById(R.id.resume_game);

		View.OnClickListener startNewGame = new View.OnClickListener(){ 

			@Override
			public void onClick(View v) {
				if(GameActivity.invV != null){
					GameActivity.invV.cleanInventory();
				}
				Intent intent = new Intent(getApplicationContext(), GameActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				finish();
				startActivity(intent);
				GameActivity.levelCounter=1;
				GameController.setLevel(1);
				MapModel.setPos(0,1);
			}//onClick

		};//startNewGame

		/**
		 * A button that, in the case of the game being exited by pressing the backwards button while playing,
		 * allows the player to resume the game from the previous position
		 */
		View.OnClickListener resumeGame = new View.OnClickListener(){ 

			@Override
			public void onClick(View v){
				finish();
			}
		};//resumeGame

		newGame.setOnClickListener(startNewGame);
		resumeButton.setOnClickListener(resumeGame);

		/*
		 * The button resumeButton is only visible if the variable visResume is defined as true	
		 */
		if(visResume == true){ 
			findViewById(R.id.resume_game).setVisibility(View.VISIBLE);
		}
		else{
			findViewById(R.id.resume_game).setVisibility(View.GONE);
		}

		resumeButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v){
				startActivity(new Intent(StartMenuActivity.this, GameActivity.class));
			}
		});//resumeButton

		/**
		 * An ImageButton that gives the player ability to pause and start the game music.
		 * Depending on the state two different pictures show so the user know if the music is playing 
		 * 
		 */

		musicMenuButton = (ImageButton) findViewById(R.id.musicmenubutton);
		Log.v("MainActivity","value 1: " + musicMenuButton);
		visMenuSpeak = false;
		musicMenuButton.setBackgroundResource(drawable.mutespeaker);
		musicMenuButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if(!visMenuSpeak){
					musicMenuButton.setBackgroundResource(drawable.speaker);
					try {
						mp.prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}				 
					mp.start();
					mp.setLooping(true);
					visMenuSpeak = true;
				}//if
				else{
					musicMenuButton.setBackgroundResource(drawable.mutespeaker);
					try {
						mp.prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}				 
					mp.start();
					mp.pause();	
					visMenuSpeak = false;
				}//else
			}//onClick
		});//musicMenuButton

		/**
		 * A button that starts the activity GameRules, and allows the user to view the game rules as presented in
		 * gamerules.xml
		 */
		Button gameRules = (Button) findViewById(R.id.gamerules); // This row connect the button named Game Rules in main_activity.xml to the button Game Rules.
		gameRules.setOnClickListener(new View.OnClickListener() { 

			@Override
			public void onClick(View v) {
				startActivity(new Intent(StartMenuActivity.this, GameRules.class));
			}//onClick
		});//gameRules

	}//onCreate


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}//onCreateOptionsMenu

	@Override
	protected void onResume() {
		super.onResume();
		musicMenuButton = (ImageButton) findViewById(R.id.musicmenubutton);
	}//onResume

	@Override
	protected void onRestart() {
		super.onRestart();
		mp = MediaPlayer.create(StartMenuActivity.this, R.raw.house_music);
		musicMenuButton = (ImageButton) findViewById(R.id.musicmenubutton);
		musicMenuButton.setBackgroundResource(drawable.mutespeaker);

	}//onRestart

	protected void onPause() {
		super.onPause();
		mp.release(); //kills the MediaPlayer
	}//onPause

	protected void onStop() {
		super.onStop();
		mp.release();
		visMenuSpeak = false;
	}//onStop

}//MainActivity
