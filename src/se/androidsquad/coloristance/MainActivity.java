package se.androidsquad.coloristance;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;
/*
 * This class represent the first screen of our game, it should contain buttons to 
 * create a new game, start the music, pause the music. From this first screen you should
 * be routed to the firstscreen.xml when you click on the newGame button.
 */

public class MainActivity extends Activity {

	MediaPlayer mp;
	//ToggleButton musicSwitch;

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

		/*musicSwitch = (ToggleButton) findViewById(R.id.musictogglebutton);
		musicSwitch.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(musicSwitch.isChecked()){
					try {
						mp.prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}				 
					mp.start();
					mp.setLooping(true);
				}else{
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
			}
		});

	}*/
		/*@Override
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
			/*if(musicSwitch.isChecked()){
			musicSwitch.toggle();
		}
			mp = MediaPlayer.create(MainActivity.this, R.raw.house_music);				 
		 */
	}

}
