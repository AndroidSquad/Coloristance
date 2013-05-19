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
public class DrawMap extends View { 
	

	public DrawMap(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) { 
		super.onDraw(canvas);
		
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
		col.put("white", new Paint());
		col.get("white").setColor(RectModel.WHITE);
		col.put("black", new Paint());
		col.get("black").setColor(RectModel.BLACK);
		
		String rectColor = "pl";
		
		
		int mapTop, mapBot, mapRight, mapLeft, mapWidth, mapHeight;
		mapTop = findViewById(R.id.mapRect).getTop();
		mapBot = findViewById(R.id.mapRect).getBottom();
		mapRight = findViewById(R.id.mapRect).getRight();
		mapLeft = findViewById(R.id.mapRect).getLeft();
		mapHeight = findViewById(R.id.mapRect).getHeight();
		mapWidth = findViewById(R.id.mapRect).getWidth();

				
		MapModel.setMap(mapWidth, mapHeight, mapTop, mapRight, mapBot, mapLeft);
		MapModel.setMap("lvl_1");//this line sets the level to be played
		
		//Log.v("DrawMap", mapWidth+", "+mapHeight+", "+mapLeft+", "+mapTop+", "+mapRight+", "+mapBot);


		/*
		 * the following code segment including the for-loop creates the map frame that the
		 * levels will be painted on.
		 */
		Rect frameRect = new Rect();
		frameRect.set(mapLeft, mapTop, mapRight, mapBot);
		Paint blue = new Paint();
		blue.setColor(Color.BLUE);
		blue.setStyle(Paint.Style.FILL);
		canvas.drawRect(frameRect, blue);

		for (int i = 1; i < getWidth() ; i++) {
			canvas.drawLine(i*getWidth()/8, 0, i*getWidth()/8, getHeight(), col.get(rectColor));
			canvas.drawLine(0, i*getHeight()/3, getWidth(), i*getHeight()/3, col.get(rectColor));
		}

		//for (int i = 0; i < getWidth()-1 ; i++){
		//	canvas.drawLine(i+getWidth()/8, (i+getHeight()/3)+(1/2), (i+getWidth()/8)+(1/5), (i+getHeight()/3)+(1/2), dark);
		
		/*
		 * creates a HashMap to be able to store a certain x,y position with the corresponding color of the room.
		 * The String is the key to the specific room.
		 */
		HashMap<String, Rect> map = new HashMap<String, Rect>();

		String name = "Ej startat";
		/*
		 * these two for-loops insert the values of the x,y-position and the corresponding color in the map
		 */
		
		for(int i = 0; i<MapModel.getMap().length;i++){
			for(int j = 0; j<MapModel.getMap()[i].length;j++){
				name = i+","+j;
				map.put(name, new Rect());
				map.get(name).set(MapModel.getRectPos(1, i), MapModel.getRectPos(2, j),  
				MapModel.getRectPos(3, i), MapModel.getRectPos(4, j)); 
				RectModel.setRectColor(MapModel.getMap()[i][j]);

				rectColor = RectModel.getRoomColor();
				canvas.drawRect( map.get(name), col.get(rectColor));
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
		
		//The following line draws the position of the player on the map 
		canvas.drawCircle(MapModel.getCircPos(1, MapModel.getMyX()), MapModel.getCircPos(2, MapModel.getMyY()), MapModel.getCircPos(3, MapModel.getMyY()), col.get("white"));
		invalidate();// Calls the onDraw again as soon as it has painted everything
	}
		
	/**Refera till xml-filen i javan ist�llet f�r tv�rtom som vi trodde annars

			View p = view.getRootView();
	    	TextView greenRect = (TextView)p.findViewById(R.id.toggleButton2); */

}

