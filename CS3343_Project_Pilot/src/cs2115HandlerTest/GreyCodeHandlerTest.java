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
	
	@Test
	public void testGreyCodeDoubleReturn_02() {
		assertEquals(1000, greyCode.handleForDoubleReturn("=GREY(1000)"), 0.0001);
	}
	
	@Test
	public void testGreyCodeDoubleReturn_03() {
		assertEquals(1000001, greyCode.handleForDoubleReturn("1000001"), 0.0001);
	}
	
	@Test
	public void testGreyCodeStringReturn_01() {
		String res = greyCode.handleForStringReturn("10");
		assertEquals("0b1111", res);
	}
	
	@Test
	public void testGreyCodeStringReturn_02() {
		String res = greyCode.handleForStringReturn("1000000");
		assertEquals("0b10001110001101100000", res);
	}
	
	@Test
	public void testGreyCodeStringReturn_03() {
		String res = greyCode.handleForStringReturn("=GREY(99)");
		assertEquals("0b1010010", res);
	}

	
}