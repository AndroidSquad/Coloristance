/**
 * 
 */
package se.androidsquad.coloristance;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MapModelTest extends TestCase{

	@Before
	public void setUp() throws Exception {
		MapModel.setMap("map_1");
	}
	
	@Test
	public void testCorrectArrayLength() {
		assertEquals(8, MapModel.mapArray.length);
		assertEquals(3, MapModel.mapArray[0].length);
	}
	@Test
	public void testCodeOfRoom() {
		MapModel.setPos(1, 1);
		assertEquals(MapModel.getRoom(),"13027");
	}
	
	
	@Test
	public void testMovementsOnMap() {
		MapModel.setPos(0, 1);
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
	
	@Test
	public void testOutOfBounds(){
		MapModel.setMap("map_1");
		MapModel.setPos(0, 1);
		
		MapModel.moveRight();
		assertEquals(1, MapModel.getMyX());
		assertEquals(1,MapModel.getMyY());
		/*
		 * This next block will test so that we are not able to go into a black=empty room,due to the conditions
		 *  the position of the player should be equal to the position before the tried movement which was (1,1)
		 */
		MapModel.moveRight();					
		assertEquals(1, MapModel.getMyX());		
		assertEquals(1,MapModel.getMyY());
		
		MapModel.moveUp();
		assertEquals(1, MapModel.getMyX());
		assertEquals(0,MapModel.getMyY());
		/*
		 * This next block will test if you are able to move out of bounds, due to the conditions
		 *  the position of the player should be equal to the position before the tried movement which was (1,0)
		 */
		MapModel.moveUp();						
		assertEquals(1, MapModel.getMyX());		
		assertEquals(0,MapModel.getMyY());
		
		MapModel.moveDown();
		assertEquals(1, MapModel.getMyX());
		assertEquals(1,MapModel.getMyY());	
		
		MapModel.moveDown();
		assertEquals(1, MapModel.getMyX());
		assertEquals(2,MapModel.getMyY());
		/*
		 * This last block test if you are able to call moveDown() when you do not have any rooms under you, move out of bounds.
		 * Due to our predetermined conditions on the movement the player should remain on the same position
		 */
		MapModel.moveDown();					
		assertEquals(1, MapModel.getMyX());		
		assertEquals(2,MapModel.getMyY());
		
		
		
	}

}
