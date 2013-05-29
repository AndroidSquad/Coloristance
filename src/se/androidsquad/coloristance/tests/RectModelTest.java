package se.androidsquad.coloristance.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.androidsquad.coloristance.models.MapModel;
import se.androidsquad.coloristance.models.RectModel;

/**
 * The initial test checks that we can set the RectColor correctly.
 * The second test is more advanced and asserts that the RectColor is corresponding
 * to the room that the player is currently in.
 * The last test checks that the roomcolor is the correct one, by knowing that the RectColor
 * was orange. 
 */

public class RectModelTest {
	
	// investigates if we can set the color and that it is stored correctly.
	@Test
	public void testSetRectColor() {
		RectModel.setRectColor("2");
		assertEquals(RectModel.rectColor, RectModel.getRectColor());
		
	}
	// This test case investigates if the color corresponds to the position on the map when the
	// player moves on the map.
	@Test
	public void testMoveColor(){
		MapModel.setMap(1);
		MapModel.setPos(0, 1);
		MapModel.moveRight();
		RectModel.setRectColor(MapModel.getRoom());
		assertEquals(RectModel.BLUE_LIGHT, RectModel.getRectColor());
		MapModel.moveUp();
		RectModel.setRectColor(MapModel.getRoom());
		assertEquals(RectModel.rectColor, RectModel.getRectColor());
		assertEquals(RectModel.ORANGE_LIGHT, RectModel.getRectColor());
		
	}
	
	// This test case needs the code above to be run because if it is not the 
	//	color is not ORANGE_LIGHT because the position is not (1,0)
	 
	@Test
	public void testGetRoomColor() {
		assertEquals("ol", RectModel.getRoomColor());
		
	}


}
