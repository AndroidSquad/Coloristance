/**
 * 
 */
package se.androidsquad.coloristance;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author atommy
 *
 */
public class MapModelTest extends TestCase{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	//Write what you want to be initiated before the test starts here
	public void setUp() throws Exception {
		MapModel.setMap("lvl_1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	//Write what you need to be sent after test, which in most cases for us is nothing
	public void tearDown() throws Exception {
		
	}

	/**
	 * Test method for {@link se.androidsquad.coloristance.MapModel#moveRight()}.
	 */
	//What a test method looks like before it is implemented.
	//Write the testcode and use assert to check the result
//	@Test
//	public final void testMoveRight() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link se.androidsquad.coloristance.MapModel#moveDown()}.
	 */
	
	//This method tests the MoveDown-method. We have added some code to assist us with 
	//finding the error where we were getting a null-pointer exception
	@Test
	public final void testMoveDown() {
		System.out.println(MapModel.mapArray[0].length);
		
		System.out.print(MapModel.getMyY());
		MapModel.moveDown();
		
		System.out.print(MapModel.getMyY());
		MapModel.moveDown();

		System.out.print(MapModel.getMyY());
		MapModel.moveDown();

		System.out.print(MapModel.getMyY());
		MapModel.moveDown();

		System.out.print(MapModel.getMyY());
		assertTrue(MapModel.getMyY() == 2);
	}

}
