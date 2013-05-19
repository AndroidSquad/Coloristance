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
 * crate a new game, start the music, pause the music. From this first screen you should
 * be routed to the firstscreen when you click on the button new game.
 */

public class MainActivity extends Activity {

	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mp = MediaPlayer.create(MainActivity.this, R.raw.house_music);				 

		
		Button newGame = (Button) findViewById(R.id.button1); // This row connect the button named button1 in main_activity.xml to the button a.
		newGame.setOnClickListener(new View.OnClickListener() { //in order for the user to be able to click on the button a you need to be able to listen to the button.
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, FirstScreen.class));
				
			}
		});
		
		Button sexyMusicOn = (Button) findViewById(R.id.button2);
		sexyMusicOn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Nedan behövs för att ta MediaPlayer från Initialized State till Prepared State
				try {
					mp.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
				// TODO Auto-generated method stub
				// Nedan behövs för att trycka på Sexy Music off
				// första gången innan MediaPlayer är i Started State
				
				try {
					mp.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				 
				mp.start();
				mp.pause();	
			}
		});
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		super.onResume();
		mp = MediaPlayer.create(MainActivity.this, R.raw.house_music);				 

	}
	
	
	


}