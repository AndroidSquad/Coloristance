/**
 * 
 */
package se.androidsquad.coloristance.tests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import se.androidsquad.coloristance.MapModel;

/**
* The first test asserts that the mapArray has the correct size.
* The second test is made in order to check that the roomcode is correctly 
* defined and represented in correlation to where the player currently is.
* The third test checks that the players position is correct after each move.
* The last test is performed to make sure that the player is unable
* to move outside of the mapArray and also that the player is unable 
* to move into black squares.
*/

public class MapModelTest extends TestCase{

	@Before
	public void setUp() throws Exception {
		MapModel.setMap(1);
	}
	
	
	@Test
	public void testCorrectArrayLength() {
		assertEquals(8, MapModel.mapArray.length);
		assertEquals(3, MapModel.mapArray[0].length);
	}
	/*
	 * this test examine if the you have moved to the position (1,1) on the map
	 * the roomcode should be 13027
	 */
	@Test
	public void testCodeOfRoom() {
		MapModel.setPos(1, 1);
		assertEquals(MapModel.getRoom(),"13027");
	}
	
	/*
	 * this test help us investigate if the player can move on the map and
	 * that the position is correct after each movement.
	 */	
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
	/*
	 * this test helped us investigate if our set preconditions on the movements
	 * worked as intended
	 */
	@Test
	public void testOutOfBounds(){
		MapModel.setMap(1);
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
