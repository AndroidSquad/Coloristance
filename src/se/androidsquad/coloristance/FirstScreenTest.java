package se.androidsquad.coloristance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//diskutera licenses:)


public class FirstScreenTest {



	@Before
	public void setUp() throws Exception {
		MapModel.setMap("map_1");
		MapModel.setPos(0, 1);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnCreateBundle() {
		
		MapModel.moveRight();
		assertEquals(1, MapModel.getMyX());
		assertEquals(1,MapModel.getMyY());
		
		MapModel.moveUp();
		assertEquals(1, MapModel.getMyX());
		assertEquals(0,MapModel.getMyY());
		
		MapModel.moveDown();
		assertEquals(1, MapModel.getMyX());
		assertEquals(1,MapModel.getMyY());	
		
		MapModel.moveUp();
		assertEquals(1, MapModel.getMyX());
		assertEquals(0,MapModel.getMyY());
		
		MapModel.moveRight();
		assertEquals(2, MapModel.getMyX());
		assertEquals(0,MapModel.getMyY());
		
	}


}
