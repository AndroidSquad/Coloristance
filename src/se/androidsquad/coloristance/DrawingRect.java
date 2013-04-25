package se.androidsquad.coloristance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class DrawingRect extends View{ // Creates a custom view that paints a filled rectangle

	public DrawingRect(Context context, AttributeSet attrs){
		super(context, attrs);
		// TODO Auto-generated constructor stub

	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		Rect ourRect = new Rect();
		//ourRect.set(canvas.getWidth()/8, canvas.getHeight()/4, 7*canvas.getWidth()/8, 3*canvas.getHeight()/4);
		ourRect.set(0, 0, canvas.getWidth(),canvas.getHeight());

		Paint green = new Paint();
		green.setColor(Color.GREEN);
		green.setStyle(Paint.Style.FILL);

		canvas.drawRect(ourRect, green);

		/*Refera till xml-filen i javan istället för tvärtom som vi trodde annars

		View p = view.getRootView();
    	TextView greenRect = (TextView)p.findViewById(R.id.toggleButton2); */
		
		
	}



}
