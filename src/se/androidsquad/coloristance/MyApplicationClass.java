package se.androidsquad.coloristance;

import android.os.Bundle;

public class MyApplicationClass extends FirstScreen {
	DrawMap map;
	DrawKeys drawKeys;
	GameController game;
	FirstScreen firstScreen;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstscreen);
		game = new GameController();
		map = new DrawMap(firstScreen, null);
		drawKeys = new DrawKeys(firstScreen, null);
		// TODO Put your application initialization code here.
	}
}
