package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.BinaryHandler;
import fxcelException.InvalidExpressionException;

public class BinaryHandlerTest {

	private BinaryHandler binary;
	
	@Before
	public void setup() {
		binary= new BinaryHandler();
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testBinaryDoubleReturn_01() {
		double res = binary.handleForDoubleReturn("2+3");
		assertEquals(5, res, 0.0001);
	}
	
	@Test
	public void testBinaryStringReturn_01() {
		String res = binary.handleForStringReturn("10");
		assertEquals("0b1010", res);
	}
	
	@Test
	public void testBinaryStringReturn_02() {
		String res = binary.handleForStringReturn("-6451.1");
		assertEquals("-0b1100100110011.00011", res);
	}
	
}
