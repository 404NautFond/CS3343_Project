package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.OctalHandler;
import fxcelException.InvalidExpressionException;

public class OctalHandlerTest {

	private OctalHandler octal;
	
	@Before
	public void setup() {
		octal= new OctalHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testOctalDoubleReturn_01() {
		double res = octal.handleForDoubleReturn("1+1");
		assertEquals(2, res, 0.0001);
	}
	
	@Test
	public void testOctalStringReturn_01() {
		String res = octal.handleForStringReturn("17");
		assertEquals("0o21", res);
	}
	
	@Test
	public void testOctalStringReturn_02() {
		String res = octal.handleForStringReturn("-19345");
		assertEquals("-0o45621", res);
	}
	
}