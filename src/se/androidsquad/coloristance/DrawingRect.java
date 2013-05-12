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

	//Color 0 is transparent
	public static final int BLUE_LIGHT 	= 0xFF33B5E5; // Color 1
	public static final int BLUE_DARK 	= 0xFF0099CC;
	public static final int GREEN_LIGHT	= 0xFF669900; // Color 2
	public static final int GREEN_DARK 	= 0xFF99CC00;
	public static final int ORANGE_LIGHT= 0xFFFF8800; // Color 3
	public static final int ORANGE_DARK = 0xFFFFBB33;
	public static final int PURPLE_LIGHT= 0xFF9933CC; // Color 4
	public static final int PURPLE_DARK = 0xFFAA66CC;
	public static final int RED_LIGHT 	= 0xFFCC0000; // Color 5
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
		Rect ourRect = new Rect();
		
		if(size == 1){
			//ourRect.set(canvas.getWidth()/8, canvas.getHeight()/4, 7*canvas.getWidth()/8, 3*canvas.getHeight()/4);
			ourRect.set(0, 0, canvas.getWidth(),canvas.getHeight());

			col = gameController.rect.getColor();

			
			Paint rectCol = new Paint(); 
			if (col == 1)
				rectCol.setColor(BLUE_LIGHT);
			if (col == 2)
				rectCol.setColor(GREEN_LIGHT);
			if (col == 3)
				rectCol.setColor(ORANGE_LIGHT);
			if (col == 4)
				rectCol.setColor(PURPLE_LIGHT);
			if (col == 5)
				rectCol.setColor(RED_LIGHT);
			
			else {
				// måla ut rektangeln i annan färg
			}
			
			rectCol.setStyle(Paint.Style.FILL);
			canvas.drawRect(ourRect, rectCol);
			

			/**Refera till xml-filen i javan istället för tvärtom som vi trodde annars

			View p = view.getRootView();
	    	TextView greenRect = (TextView)p.findViewById(R.id.toggleButton2); */
		}
		else {
			//ourRect.set(8*canvas.getWidth()/16, 8*canvas.getHeight()/16, 9*canvas.getWidth()/16, 9*canvas.getHeight()/16);
			ourRect.set(0, 0, canvas.getWidth(),canvas.getHeight());

			col = gameController.rect.getColor();

			Paint rectCol = new Paint(); 
			if (col == 1)
				rectCol.setColor(BLUE_LIGHT);
			if (col == 2)
				rectCol.setColor(GREEN_LIGHT);
			if (col == 3)
				rectCol.setColor(ORANGE_LIGHT);
			if (col == 4)
				rectCol.setColor(PURPLE_LIGHT);
			if (col == 5)
				rectCol.setColor(RED_LIGHT);
			
			else {
				// måla ut rektangeln i annan färg
			}
			
			rectCol.setStyle(Paint.Style.FILL);
			canvas.drawRect(ourRect, rectCol);
			
		}
	}



}
