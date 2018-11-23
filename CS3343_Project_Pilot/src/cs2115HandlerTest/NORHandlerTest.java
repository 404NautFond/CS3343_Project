package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.NORHandler;
import fxcelException.InvalidExpressionException;

public class NORHandlerTest {

	private NORHandler nor;
	
	@Before
	public void setup() {
		nor = new NORHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testNOR_01() {
		double res = nor.handleForDoubleReturn("1,1");
		assertEquals(0, res, 0.01);
	}

}