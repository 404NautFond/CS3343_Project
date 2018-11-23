package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.DecimalHandler;
import fxcelException.InvalidExpressionException;

public class DecimalHandlerTest {

	private DecimalHandler decimal;
	
	@Before
	public void setup() {
		decimal = new DecimalHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testDecimalDoubleReturn_01() {
		double res = decimal.handleForDoubleReturn("12+23");
		assertEquals(35, res, 0.0001);
	}
	
	@Test
	public void testDecimalStringReturn_01() {
		String res = decimal.handleForStringReturn("35");
		assertEquals("0d35", res);
	}
	
	@Test
	public void testDecimalStringReturn_02() {
		String res = decimal.handleForStringReturn("-98734");
		assertEquals("-0d98734", res);
	}
}
