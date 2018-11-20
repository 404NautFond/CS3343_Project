package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.GreyCodeHandler;
import fxcelException.InvalidExpressionException;

public class GreyCodeHandlerTest {

	private GreyCodeHandler greyCode;
	
	@Before
	public void setup() {
		greyCode = new GreyCodeHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testGreyCodeDoubleReturn_01() {
		double res = greyCode.handleForDoubleReturn("1000");
		assertEquals(1000, res, 0.0001);
	}
	
	@Test (expected = InvalidExpressionException.class)
	public void testGreyCodeDoubleReturn_02() {
		assertEquals(1000, greyCode.handleForDoubleReturn("1000+1"), 0.0001);
	}
	
	@Test
	public void testGreyCodeStringReturn_01() {
		String res = greyCode.handleForStringReturn("10");
		assertEquals("0b1111", res);
	}
	
}