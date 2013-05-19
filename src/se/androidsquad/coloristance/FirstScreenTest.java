package se.androidsquad.coloristance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FirstScreenTest {

	@Before
	public void setUp() throws Exception {
		MapModel.setMap("lvl_1");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnCreateBundle() {
		MapModel.moveRight();
		assertEquals(1, MapModel.getMyX());
		assertEquals(0,MapModel.getMyY());
		
	}

}
