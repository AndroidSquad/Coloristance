package se.androidsquad.coloristance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	public void testGetRoomColor() {
		assertEquals("gl", RectModel.getRoomColor());
		
	}


}
