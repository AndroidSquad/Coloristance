package se.androidsquad.coloristance.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import se.androidsquad.coloristance.MapModel;
import se.androidsquad.coloristance.RectModel;

/**
 * The initial test checks that we can set the RectColor correctly.
 * The second test is more advanced and asserts that the RectColor is corresponding
 * to the room that the player is currently in.
 * The last test checks that the roomcolor is the correct one, by knowing that the RectColor
 * was orange. 
 */

public class RectModelTest {

	@Test
	public void testSetRectColor() {
		RectModel.setRectColor("2");
		assertEquals(RectModel.rectColor, RectModel.getRectColor());
		
	}
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
	@Test
	public void testGetRoomColor() {
		assertEquals("ol", RectModel.getRoomColor());
		
	}


}
