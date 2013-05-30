package se.androidsquad.coloristance.views;

import se.androidsquad.coloristance.R;
import se.androidsquad.coloristance.models.DoorModel;
import se.androidsquad.coloristance.models.MapModel;
import se.androidsquad.coloristance.models.RectModel;
import android.app.Activity;
import android.util.Log;
import android.view.View;

public class RoomView{

	View currentView;
	public Activity act;

	public RoomView(Activity current) {
		this.act = current;
	}


	//	This method controls that DoorModel appoints the correct doors to each room, and it also 
	//	controls that the room is set to its corresponding color. 

	public void setRoom(){
		Log.v("RoomView","Setting room");
		DoorModel.setDoor(MapModel.getRoom());
		Log.v("RoomView","Setting door: "+ MapModel.getRoom());
		RectModel.setRectColor(MapModel.getRoom());
		Log.v("RoomView","RectColor: " + RectModel.getRectColor());
		act.findViewById(R.id.room).setBackgroundColor(RectModel.getRectColor());
	}//setRoom
}//RoomView
