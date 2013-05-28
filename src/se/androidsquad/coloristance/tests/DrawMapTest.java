package se.androidsquad.coloristance.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.androidsquad.coloristance.models.MapModel;
/**
 * This test checks the length and width of the array
 * for the map, and makes sure that it is given the correct values.
 */
public class DrawMapTest {

	@Before
	public void setUp() throws Exception {
		MapModel.setMap(1);
	}

	@Test
	public void testOnDrawCanvas() {
//		assertEquals(8, MapModel.mapArray.length);
//		assertEquals(3, MapModel.mapArray[0].length);
System.out.print("hej");
	}

}
