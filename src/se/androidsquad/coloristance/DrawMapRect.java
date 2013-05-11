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

public class DrawMapRect extends View { // Creates a custom view that paints a
										// filled rectangle

	

	
	public DrawMapRect(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Rect ourRect = new Rect();
		ourRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
		Paint blue = new Paint();
		blue.setColor(Color.BLUE);
		blue.setStyle(Paint.Style.FILL);
		canvas.drawRect(ourRect, blue);
	

		Paint dark = new Paint();
		dark.setColor(Color.BLACK);
		
		for (int i = 0; i < getWidth() ; i++) {
			canvas.drawLine(i*getWidth()/14, 0, i*getWidth()/14, getHeight(), dark);
			canvas.drawLine(0, i*getHeight()/5, getWidth(), i*getHeight()/5, dark);
			
		}
	}
}
