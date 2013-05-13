package se.androidsquad.coloristance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

//Anton: Why do we have this class? Better to code DrawingRect to take argument that will define if its a map or a room rect...

public class DrawMap extends View { // Creates a custom view that paints a filled rectangle 

	private GameController gameController;
	private RectModel rectModel;
	private int size;
	private int col;

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
				
				for (int i = 0; i < getWidth() ; i++) {
					canvas.drawLine(i*getWidth()/8, 0, i*getWidth()/8, getHeight(), dark);
					canvas.drawLine(0, i*getHeight()/3, getWidth(), i*getHeight()/3, dark);
		
		//Creates an object of gameController to be able to control the size and color of the rectangle
		gameController = new GameController(); // When creating a GameController object we create a RectModel object as well
		
		//Room One
		Rect roomOne = new Rect();
		roomOne.set(getWidth()/64, 9*getHeight()/24, 7*getWidth()/64,15*getHeight()/24); 
		Paint color = new Paint();
		color.setColor(RectModel.getRectColor());
		canvas.drawRect(roomOne, color);
		
		//Room Two
		Rect roomTwo = new Rect();
		roomTwo.set(9*getWidth()/64, 9*getHeight()/24, 15*getWidth()/64,15*getHeight()/24); 
		Paint colorTwo = new Paint();
		color.setColor(RectModel.getRectColor());
		canvas.drawRect(roomTwo, colorTwo);

		/**Refera till xml-filen i javan istŠllet fšr tvŠrtom som vi trodde annars

			View p = view.getRootView();
	    	TextView greenRect = (TextView)p.findViewById(R.id.toggleButton2); */
	
	//		else { Draw a map sized rectangle where it should be drawn. Check for the color with an if statement }

		
			
		}
	}
}
