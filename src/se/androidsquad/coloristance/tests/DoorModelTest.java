package se.androidsquad.coloristance.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import se.androidsquad.coloristance.DoorModel;
import se.androidsquad.coloristance.MapModel;
import se.androidsquad.coloristance.RectModel;


public class DoorModelTest {
	/*
	 * We need to instantiate the colors that we use in the game as
	 * an int in order to test so the doors get the right color
	 */
	int zero=RectModel.BLACK;
	int two = RectModel.GREEN_LIGHT;
	int tre = RectModel.ORANGE_LIGHT;
	int seven = RectModel.WHITE;
	
	@Before
	public void setUp() throws Exception {
		MapModel.setMap(1);
		MapModel.setPos(1, 1);
	}

	/*
	 * Investigates if we set the value of a door the getDoor returns the right color
	 */
	@Test
	public void testSetDoor() {
		DoorModel.setDoor("13027");
		assertEquals(DoorModel.getDoor('N'), tre);
		assertEquals(DoorModel.getDoor('E'), zero);
		assertEquals(DoorModel.getDoor('S'), two);
		assertEquals(DoorModel.getDoor('W'), seven);	
	}

}
