package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.NANDHandler;
import fxcelException.InvalidExpressionException;

public class NANDHandlerTest {

	private NANDHandler nand;
	
	@Before
	public void setup() {
		nand = new NANDHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testNAND_01() {
		double res = nand.handleForDoubleReturn("1,1");
		assertEquals(0, res, 0.01);
	}

}
