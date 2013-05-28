package se.androidsquad.coloristance.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.androidsquad.coloristance.MapModel;

//diskutera licenses:)


public class FirstScreenTest {



	@Before
	public void setUp() throws Exception {
		MapModel.setMap(1);
		MapModel.setPos(0, 1);

	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * this test help us investigate if the player can move on the map and
	 * that the position is correct after each movement.
	 */
	@Test
	public void testOnCreateBundle() {
		
		MapModel.moveRight();
		assertEquals(1, MapModel.getMyX());
		assertEquals(1,MapModel.getMyY());
		
		MapModel.moveRight();					//this row will test so that we are not able to go into a black=empty room
		assertEquals(1, MapModel.getMyX());		//the result on the x and y positions should be the same as before the tried movement
		assertEquals(1,MapModel.getMyY());
		
		MapModel.moveUp();
		assertEquals(1, MapModel.getMyX());
		assertEquals(0,MapModel.getMyY());
		
		MapModel.moveUp();						//this row will test if you are able to move out of bounds, this should return
		assertEquals(1, MapModel.getMyX());		// the same position asthe player stood on before which was (1,0)
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
