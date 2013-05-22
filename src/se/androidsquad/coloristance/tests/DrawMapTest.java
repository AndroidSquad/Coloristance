package se.androidsquad.coloristance.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.androidsquad.coloristance.MapModel;

public class DrawMapTest {

	@Before
	public void setUp() throws Exception {
		MapModel.setMap("lvl_1");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnDrawCanvas() {
		assertEquals(8, MapModel.mapArray.length);
		assertEquals(3, MapModel.mapArray[0].length);

	}

}
