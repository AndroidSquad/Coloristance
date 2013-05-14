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

public class DrawMap extends View { // Creates a custom view that paints a filled rectangle 

	public DrawMap(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		//MŒlar ut kartans ram
		Rect frameRect = new Rect();
		frameRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
		Paint blue = new Paint();
		blue.setColor(Color.BLUE);
		blue.setStyle(Paint.Style.FILL);
		canvas.drawRect(frameRect, blue);

		Paint dark = new Paint();
		dark.setColor(Color.BLACK);
		Paint red = new Paint();
		red.setColor(Color.RED);


		for (int i = 1; i < getWidth() ; i++) {
			canvas.drawLine(i*getWidth()/8, 0, i*getWidth()/8, getHeight(), dark);
			canvas.drawLine(0, i*getHeight()/3, getWidth(), i*getHeight()/3, dark);
		
		}
		//for (int i = 0; i < getWidth()-1 ; i++){
		//	canvas.drawLine(i+getWidth()/8, (i+getHeight()/3)+(1/2), (i+getWidth()/8)+(1/5), (i+getHeight()/3)+(1/2), dark);

		//Creates an object of gameController to be able to control the size and color of the rectangle

		 // When creating a GameController object we create a RectModel object as well
		
		HashMap<String, Rect> map = new HashMap<String, Rect>();
		
		HashMap<String, Paint> col = new HashMap<String, Paint>();
		col.put("bl", new Paint());
		col.get("bl").setColor(RectModel.BLUE_LIGHT);
		col.put("gl", new Paint());
		col.get("gl").setColor(RectModel.GREEN_LIGHT);
		col.put("ol", new Paint());
		col.get("ol").setColor(RectModel.ORANGE_LIGHT);
		col.put("pl", new Paint());
		col.get("pl").setColor(RectModel.PURPLE_LIGHT);
		col.put("rl", new Paint());
		col.get("rl").setColor(RectModel.RED_LIGHT);
		
		String name = "Ej startat";
				
		// Behöver String[][], Färger[]
		
		for(int i = 0; i<Levels.mapArray.length;i++){
			Log.v("Loop", name);
			for(int j = 0; j<Levels.mapArray[i].length;j++){
				name = i+","+j;
				//Log.v("Loop", name);
				map.put(name, new Rect());
				map.get(name).set(((j*8)+2)*(getWidth()/72), ((i*8)+2)*(getHeight()/24), (((j+1)*8))*(getWidth()/72),(((i+1)*8))*(getHeight()/24)); 
				//Log.v("Loop4", name);
				RectModel.setRectColor(Levels.mapArray[i][j]);
				Log.v("Loop4", name);
				canvas.drawRect( map.get(name), col.get("pl"));
				//Log.v("Loop5", name);
			}
		}
			
/*
		//Map Rectangle Two
		Rect mapRectTwo = new Rect();
		mapRectTwo.set(9*getWidth()/64, 9*getHeight()/24, 15*getWidth()/64,15*getHeight()/24); 
		Paint colorTwo = new Paint();
		colorTwo.setColor(RectModel.PURPLE_LIGHT);
		canvas.drawRect(mapRectTwo, colorTwo);

*/
		
		// Använd detta för att testa om den flyttar på sig. Just nu flyttas den i punkter, inte i koordinater
		//	MapModel.setPos(3, 3);
		
		//Your Position: Kmmer behöva en specifik position 
		canvas.drawCircle(MapModel.getX()+(getWidth()/16), MapModel.getY()+(getHeight()/6), 10, red);
	}


	/**Refera till xml-filen i javan istŠllet fšr tvŠrtom som vi trodde annars

			View p = view.getRootView();
	    	TextView greenRect = (TextView)p.findViewById(R.id.toggleButton2); */

	//		else { Draw a map sized rectangle where it should be drawn. Check for the color with an if statement }


}

