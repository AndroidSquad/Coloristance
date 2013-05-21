package se.androidsquad.coloristance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DoorModelTest {
	int zero=RectModel.BLACK;
	int two = RectModel.GREEN_LIGHT;
	int tre = RectModel.ORANGE_LIGHT;
	int seven = RectModel.WHITE;
	
	@Before
	public void setUp() throws Exception {
		MapModel.setMap("map_1");
		MapModel.setPos(1, 1);
	}

	@Test
	public void testSetDoor() {
		DoorModel.setDoor("13027");
		assertEquals(DoorModel.getDoor('N'), tre);
		assertEquals(DoorModel.getDoor('E'), zero);
		assertEquals(DoorModel.getDoor('S'), two);
		assertEquals(DoorModel.getDoor('W'), seven);	
	}

}
