package se.androidsquad.coloristance;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * This class is in charge of the painting of the current map on the screen, and also 
 * the player position.
 */
public class DrawMap extends View { 


	public DrawMap(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * The onDraw method handles the drawing of the map, both in portrait and landscape mode
	 */

	//A Paint object used to paint the black border on the circle representing the player position
	Paint border; 
	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) { 
		super.onDraw(canvas);

		/**
		 * Creates a HashMap which pairs up the String representing a room with the corresponding color
		 */
		
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

		// Painting the black border around the white circle representing the player position
		col.put("black2", border = new Paint());
		col.get("black2").setColor(RectModel.BLACK);
		border.setStyle(Paint.Style.STROKE); 
		border.setStrokeWidth(3);


		String rectColor = "pl";
		//TODO vad h�nder h�r?

		int mapTop = findViewById(R.id.mapRect).getTop();
		int mapBot = findViewById(R.id.mapRect).getBottom();
		int mapRight = findViewById(R.id.mapRect).getRight();
		int mapLeft = findViewById(R.id.mapRect).getLeft();
		int mapHeight = findViewById(R.id.mapRect).getHeight();
		int mapWidth = findViewById(R.id.mapRect).getWidth();
		int xPos = MapModel.getMyX();
		int yPos = MapModel.getMyY();
		Log.v("DrawMap", mapWidth+", "+mapHeight+", "+mapLeft+", "+mapTop+", "+mapRight+", "+mapBot);

		/**
		 * creates a HashMap to be able to store a certain x,y position with the corresponding color of the room.
		 * The String is the key to the specific room.
		 */
		HashMap<String, Rect> map = new HashMap<String, Rect>();

		String name = "Ej startat";
		/**
		 * These two for-loops insert the values of the x,y-position and draws the map differently depending on if the device
		 * is tilted or not. Then it draws the player on the correct position on the map.
		 */

		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			//if we turn the phone the map should be rendered differently
			MapModel.setMap(8*mapWidth/3, 55*mapHeight/144, mapTop, mapRight, mapBot, mapLeft);
			for(int i = 0; i<MapModel.getMap()[i].length;i++){
				for(int j = 0; j<MapModel.getMap().length;j++){
					name = i+","+j;
					map.put(name, new Rect());
					map.get(name).set(MapModel.getRectPos(1, i), MapModel.getRectPos(2, j),  
							MapModel.getRectPos(3, i), MapModel.getRectPos(4, j)); 
					RectModel.setRectColor(MapModel.getMap()[j][i]);

					rectColor = RectModel.getRoomColor();
					canvas.drawRect( map.get(name), col.get(rectColor));
				}//for
			}//for
			canvas.drawCircle(MapModel.getCircPos(1, yPos), MapModel.getCircPos(2, xPos), MapModel.getCircPos(4, yPos), col.get("white"));
			canvas.drawCircle(MapModel.getCircPos(1, yPos), MapModel.getCircPos(2, xPos), MapModel.getCircPos(4, yPos), col.get("black2"));//Pints the black border on the white circle
			invalidate();// Calls the onDraw again as soon as it has painted everything
		}//if
		else{
			MapModel.setMap(mapWidth, mapHeight, mapTop, mapRight, mapBot, mapLeft);
			for(int i = 0; i<MapModel.getMap().length;i++){
				for(int j = 0; j<MapModel.getMap()[i].length;j++){
					name = i+","+j;
					map.put(name, new Rect());
					map.get(name).set(MapModel.getRectPos(1, i), MapModel.getRectPos(2, j),  
							MapModel.getRectPos(3, i), MapModel.getRectPos(4, j)); 
					RectModel.setRectColor(MapModel.getMap()[i][j]);

					rectColor = RectModel.getRoomColor();
					canvas.drawRect( map.get(name), col.get(rectColor));
				}//for
			}//for
			canvas.drawCircle(MapModel.getCircPos(1, xPos), MapModel.getCircPos(2, yPos), MapModel.getCircPos(3, yPos), col.get("white"));
			canvas.drawCircle(MapModel.getCircPos(1, xPos), MapModel.getCircPos(2, yPos), MapModel.getCircPos(3, yPos), col.get("black2"));//Paints the black border on the white circle
			invalidate();// Calls the onDraw again as soon as it has painted everything
		}//else
	}//onDraw
}//DrawMap

