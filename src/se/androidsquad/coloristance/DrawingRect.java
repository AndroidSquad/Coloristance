package se.androidsquad.coloristance;

import android.R.color;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class DrawingRect extends View{ // Creates a custom view that paints a filled rectangle

	public static final int BLUE_LIGHT 	= 0xFF33B5E5;
	public static final int BLUE_DARK 	= 0xFF0099CC;
	public static final int GREEN_LIGHT	= 0xFF669900;
	public static final int GREEN_DARK 	= 0xFF99CC00;
	public static final int ORANGE_LIGHT= 0xFFFF8800;
	public static final int ORANGE_DARK = 0xFFFFBB33;
	public static final int PURPLE_LIGHT= 0xFF9933CC;
	public static final int PURPLE_DARK = 0xFFAA66CC;
	public static final int RED_LIGHT 	= 0xFFCC0000;
	public static final int RED_DARK 	= 0xFFFF4444;

	private GameController gameController;
	private int size;
	private int col;

	public DrawingRect(Context context, AttributeSet attrs){
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		/*Creates an object of gameController to be able to control the size and color of the rectangle
		 * 
		 */
		gameController = new GameController(); // When creating a GameController object we create a RectModel object as well
		size = gameController.rect.getSize(); // using method from the RectModel object named rect

		if(size == 1){

			Rect ourRect = new Rect();
			//ourRect.set(canvas.getWidth()/8, canvas.getHeight()/4, 7*canvas.getWidth()/8, 3*canvas.getHeight()/4);
			ourRect.set(0, 0, canvas.getWidth(),canvas.getHeight());

			col = gameController.rect.getColor();

			if (col == 3){

				Paint green = new Paint(); 
				green.setColor(PURPLE_LIGHT);
				green.setStyle(Paint.Style.FILL);
				canvas.drawRect(ourRect, green);

			}
			else {
				// måla ut rektangeln i annan färg
			}

			/**Refera till xml-filen i javan istället för tvärtom som vi trodde annars

			View p = view.getRootView();
	    	TextView greenRect = (TextView)p.findViewById(R.id.toggleButton2); */
		}
		else {
			// Draw a map sized rectangle where it should be drawn
			// Check for the color with an if statement
			
		}
	}



}
