package se.androidsquad.coloristance.views;

import se.androidsquad.coloristance.R;
import se.androidsquad.coloristance.models.DoorModel;
import se.androidsquad.coloristance.models.RectModel;
import android.app.Activity;
import android.view.View;

public class DoorView{

	int[] door = {R.id.top_door, R.id.right_door, R.id.bot_door,  R.id.left_door};
	View currentView;
	public Activity act;

	public DoorView(Activity current) {
		this.act = current;
	}

	/**
	 * This method handles that the doors are appointed to the right locations in a room
	 * If a door is initially defined as being black, then that door is made invisible
	 */
	public void setDoors(){
		for(int i = 0; i<4; i++){
			View currentView = act.findViewById(door[i]);

			currentView.setVisibility(View.VISIBLE);
			currentView.setBackgroundColor(DoorModel.getDoor(i));
			if(DoorModel.getDoor(i) == RectModel.BLACK){
				currentView.setVisibility(View.INVISIBLE);
			}
		}//for
	}//setDoors
}//DoorView
