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
	public void setUp() throws Exception {
		MapModel.setMap("lvl_1");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link se.androidsquad.coloristance.MapModel#moveRight()}.
	 */
//	@Test
//	public final void testMoveRight() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link se.androidsquad.coloristance.MapModel#moveDown()}.
	 */
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
