package se.androidsquad.coloristance;

import java.util.ArrayList;
import java.util.List;

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
	private String [][] rectId = Levels.Level1;

	public DrawMap(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		//M�lar ut kartans ram
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
		}

		//Creates an object of gameController to be able to control the size and color of the rectangle
		gameController = new GameController(); // When creating a GameController object we create a RectModel object as well

		/*Rect[][] rectList = new Rect[2][2];	
		MapModel.setMap(Levels.Level1);
		Paint[][] colorList = new Paint[2][2];	

		//Test
		for(int i = 0; i<2 ;i++){ // Loopen �r felaktig. 2:an b�r ers�ttas med rectId[i].length
			for(int j = 0; j<2 ;j++){ //Loopen �r felaktig. 2:a b�r ers�ttas med dynamiskt v�rde

				MapModel.setPos(i, j);

				rectList[i][j] = new Rect();
				rectList[i][j].set(getWidth()/(8*(i+1)), 9*getHeight()/((j+1)*3), 7*getWidth()/(8*(i+1)),15*getHeight()/((j+1)*3)); 

				colorList[i][j] = new Paint();
				colorList[i][j].setColor(RectModel.getRectColor()); // Ers�tt konstant med typ MapModel.getRoomColor(rectId[0][0])
				canvas.drawRect(rectList[i][j], colorList[i][j]);*/

		//Map Rectangle One
		Rect mapRectOne = new Rect();
		mapRectOne.set(getWidth()/64, 9*getHeight()/24, 7*getWidth()/64,15*getHeight()/24); 
		Paint color = new Paint();
		color.setColor(RectModel.getRectColor());
		canvas.drawRect(mapRectOne, color);

		//Map Rectangle Two
		Rect mapRectTwo = new Rect();
		mapRectTwo.set(9*getWidth()/64, 9*getHeight()/24, 15*getWidth()/64,15*getHeight()/24); 
		Paint colorTwo = new Paint();
		colorTwo.setColor(RectModel.PURPLE_LIGHT);
		canvas.drawRect(mapRectTwo, colorTwo);

		//Map Rectangle Three
		Rect mapRectThree = new Rect();
		mapRectThree.set(9*getWidth()/64, 1*getHeight()/24, 15*getWidth()/64,7*getHeight()/24); 
		Paint colorThree = new Paint();
		colorThree.setColor(RectModel.GREEN_LIGHT);
		canvas.drawRect(mapRectThree, colorThree);
		
		//Map Rectangle Apple
		Rect mapRectApple = new Rect();
		mapRectApple.set(17*getWidth()/64, 9*getHeight()/24, 23*getWidth()/64,15*getHeight()/24); 
		Paint colorApple = new Paint();
		colorApple.setColor(RectModel.BLUE_LIGHT);
		canvas.drawRect(mapRectApple, colorApple);

		//Map Rectangle Four
		Rect mapRectFour = new Rect();
		mapRectFour.set(17*getWidth()/64, 1*getHeight()/24, 23*getWidth()/64,7*getHeight()/24); 
		Paint colorFour = new Paint();
		colorFour.setColor(RectModel.getRectColor());
		canvas.drawRect(mapRectFour, colorFour);

		//Map Rectangle Five
		Rect mapRectFive = new Rect();
		mapRectFive.set(25*getWidth()/64, 1*getHeight()/24, 31*getWidth()/64,7*getHeight()/24); 
		Paint colorFive = new Paint();
		colorFive.setColor(RectModel.getRectColor());
		canvas.drawRect(mapRectFive, colorFive);

		//Map Rectangle Six
		Rect mapRectSix = new Rect();
		mapRectSix.set(25*getWidth()/64, 9*getHeight()/24, 31*getWidth()/64,15*getHeight()/24); 
		Paint colorSix = new Paint();
		colorSix.setColor(RectModel.getRectColor());
		canvas.drawRect(mapRectSix, colorSix);

		//Map Rectangle Seven
		Rect mapRectSeven = new Rect();
		mapRectSeven.set(25*getWidth()/64, 17*getHeight()/24, 31*getWidth()/64,23*getHeight()/24); 
		Paint colorSeven = new Paint();
		colorSeven.setColor(RectModel.getRectColor());
		canvas.drawRect(mapRectSeven, colorSeven);

		//Map Rectangle Eight
		Rect mapRectEight = new Rect();
		mapRectEight.set(33*getWidth()/64, 17*getHeight()/24, 39*getWidth()/64,23*getHeight()/24); 
		Paint colorEight = new Paint();
		colorEight.setColor(RectModel.getRectColor());
		canvas.drawRect(mapRectEight, colorEight);

		//Map Rectangle Nine
		Rect mapRectNine = new Rect();
		mapRectNine.set(41*getWidth()/64, 17*getHeight()/24, 47*getWidth()/64,23*getHeight()/24); 
		Paint colorNine = new Paint();
		colorNine.setColor(RectModel.getRectColor());
		canvas.drawRect(mapRectNine, colorNine);

		//Map Rectangle Ten
		Rect mapRectTen = new Rect();
		mapRectTen.set(49*getWidth()/64, 17*getHeight()/24, 55*getWidth()/64,23*getHeight()/24); 
		Paint colorTen = new Paint();
		colorTen.setColor(RectModel.getRectColor());
		canvas.drawRect(mapRectTen, colorTen);

		//Map Rectangle Eleven
		Rect mapRectEleven = new Rect();
		mapRectEleven.set(49*getWidth()/64, 9*getHeight()/24, 55*getWidth()/64,15*getHeight()/24); 
		Paint colorEleven = new Paint();
		colorEleven.setColor(RectModel.getRectColor());
		canvas.drawRect(mapRectEleven, colorEleven);

		//Map Rectangle Twelve
		Rect mapRectTwelve = new Rect();
		mapRectTwelve.set(57*getWidth()/64, 9*getHeight()/24, 63*getWidth()/64,15*getHeight()/24); 
		Paint colorTwelve = new Paint();
		colorTwelve.setColor(RectModel.getRectColor());
		canvas.drawRect(mapRectTwelve, colorTwelve);
	}


	/**Refera till xml-filen i javan ist�llet f�r tv�rtom som vi trodde annars

			View p = view.getRootView();
	    	TextView greenRect = (TextView)p.findViewById(R.id.toggleButton2); */

	//		else { Draw a map sized rectangle where it should be drawn. Check for the color with an if statement }


}

