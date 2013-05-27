package se.androidsquad.coloristance.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.androidsquad.coloristance.MapModel;
import se.androidsquad.coloristance.RectModel;

public class RectModelTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

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
		MapModel.moveUp();
		assertEquals(RectModel.rectColor, RectModel.getRectColor());
	}
	@Test
	public void testGetRoomColor() {
		assertEquals("gl", RectModel.getRoomColor());
		
	}


}
