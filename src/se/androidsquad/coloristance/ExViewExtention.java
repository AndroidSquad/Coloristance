package se.androidsquad.coloristance;

import java.util.HashMap;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/*
 * This class is in charge of the painting of the current map on the screen, and also 
 * the player position.
 */

public class ExViewExtention extends View { 
	
	//Declare the key's references and a "incorrect" value in 9 that will show in log-messages if something is wrong.
	int[] keyNames = {R.id.key_button_blue, R.id.key_button_green, R.id.key_button_orange, R.id.key_button_purple, R.id.key_button_red, 9};	
	
	//DrawKeys just use View's constructor without additions. 
	public ExViewExtention(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) { 
		super.onDraw(canvas);
		
		//Declaring variables needed when scaling the map
		int mapHeight = findViewById(R.id.room_layout).getHeight();
		int mapWidth = findViewById(R.id.room_layout).getWidth();
		Log.v("DrawKeys","Höjd: "+mapHeight +" Bredd: "+ mapWidth);
		
		for(int i = 0; i<5;i++){
			findViewById(keyNames[i]).setScaleX((mapWidth/10)/mapWidth);
			findViewById(keyNames[i]).setScaleY((mapHeight/10)/mapHeight);
		}
		
	}
}

