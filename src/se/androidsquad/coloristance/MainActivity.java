package se.androidsquad.coloristance;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
/*
 * This class represent the first screen of our game, it should contain buttons to 
 * create a new game, start the music, pause the music. From this first screen you should
 * be routed to the firstscreen.xml when you click on the newGame button.
 */

public class MainActivity extends Activity {

	MediaPlayer mp;

	/*
	 * When the class is called the onCreate it sets the view to activity_main.xml, and it also creates different
	 * buttons in order for the player to start a newGame and turn on the music.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mp = MediaPlayer.create(MainActivity.this, R.raw.house_music);				 

		
		Button newGame = (Button) findViewById(R.id.button1); // This row connect the button named button1 in main_activity.xml to the button newGame.
		newGame.setOnClickListener(new View.OnClickListener() { 
			
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, FirstScreen.class));
				
			}
		});
		
		Button sexyMusicOn = (Button) findViewById(R.id.button2);
		sexyMusicOn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * The following lines of codes is needed in order to change the state of
				 *  the MediaPlayer from Initialized State to Prepared State.
				 */
				try {
					mp.prepare();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}				 
				mp.start();
				mp.setLooping(true);
			}
		});

		Button sexyMusicPause = (Button) findViewById(R.id.button3);
		sexyMusicPause.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * The following lines of codes is needed to be able
				 * to click on the button Sexy Music off the first time 
				 * before Media Player is in Started State			
				 */
				
				try {
					mp.prepare();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}				 
				mp.start();
				mp.pause();	
			}
		});
		
	}
	@Override
	protected void onPause() {
		super.onPause();
		mp.release();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	protected void onResume() {
		super.onResume();
		mp = MediaPlayer.create(MainActivity.this, R.raw.house_music);				 

	}
	
	
	


}