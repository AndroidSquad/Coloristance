/**
 * 
 */
package se.androidsquad.coloristance.tests;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import se.androidsquad.coloristance.models.MapModel;

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

	// This test investigates if we are able to set the position on the map as intended
	@Test
	public void testSetPos() {
		MapModel.setPos(3, 2);
		assertEquals(3,MapModel.getMyX());
		assertEquals(2,MapModel.getMyY());
		
	}

	 // this is a auto generated method stub where we investigate if level 1  is initialized as it is supposed to.
	@Test
	public void testArrayLevel1(){
		MapModel.setMap(1);
		String[][] result = MapModel.getMap();
		assertNotNull(result);
		assertEquals(8, result.length);
		assertNotNull(result[0]);
		assertEquals(3, result[0].length);
		assertEquals("00000", result[0][0]);
		assertEquals("70100", result[0][1]);
		assertEquals("00000", result[0][2]);
		assertNotNull(result[1]);
		assertEquals(3, result[1].length);
		assertEquals("30210", result[1][0]);
		assertEquals("13027", result[1][1]);
		assertEquals("21400", result[1][2]);
		assertNotNull(result[2]);
		assertEquals(3, result[2].length);
		assertEquals("20503", result[2][0]);
		assertEquals("00000", result[2][1]);
		assertEquals("40302", result[2][2]);
		assertNotNull(result[3]);
		assertEquals(3, result[3].length);
		assertEquals("50212", result[3][0]);
		assertEquals("15430", result[3][1]);
		assertEquals("31104", result[3][2]);
		assertNotNull(result[4]);
		assertEquals(3, result[4].length);
		assertEquals("20345", result[4][0]);
		assertEquals("42011", result[4][1]);
		assertEquals("14503", result[4][2]);
		assertNotNull(result[5]);
		assertEquals(3, result[5].length);
		assertEquals("30502", result[5][0]);
		assertEquals("00000", result[5][1]);
		assertEquals("50201", result[5][2]);
		assertNotNull(result[6]);
		assertEquals(3, result[6].length);
		assertEquals("50043", result[6][0]);
		assertEquals("45720", result[6][1]);
		assertEquals("24005", result[6][2]);
		assertNotNull(result[7]);
		assertEquals(3, result[7].length);
		assertEquals("00000", result[7][0]);
		assertEquals("70000", result[7][1]);
		assertEquals("00000", result[7][2]);
	}

	//We needed to understand why we got a nullpointer exception when drawing the map,
	//so we did this simple test to see that the for-loop ran 1 position more than our length of the map
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
		//Variation1
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

		MapModel.moveLeft();
		assertEquals(0, MapModel.getMyX());
		assertEquals(1,MapModel.getMyY());
		
		/*we have moved the player back to the start position (0,1) on the map with the code above.In order 
		for us to test next variation.*/
		//Variation2
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
