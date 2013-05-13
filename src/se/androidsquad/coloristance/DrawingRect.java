


































































































//*package se.androidsquad.coloristance;

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

	private GameController gameController;
	private RectModel rectModel;
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
		size = gameController.data.getSize(); // using method from the RectModel object named rect

		Rect ourRect = new Rect();
			//ourRect.set(canvas.getWidth()/8, canvas.getHeight()/4, 7*canvas.getWidth()/8, 3*canvas.getHeight()/4);
			ourRect.set(0, 0, canvas.getWidth(),canvas.getHeight());
			
			Paint color = new Paint();
			color.setColor(gameController.data.getColor());
			canvas.drawRect(ourRect, color);


			/**Refera till xml-filen i javan istället för tvärtom som vi trodde annars

			View p = view.getRootView();
	    	TextView greenRect = (TextView)p.findViewById(R.id.toggleButton2); */
		}
//		else {
//			// Draw a map sized rectangle where it should be drawn
//			// Check for the color with an if statement
//			
//		}
	}




