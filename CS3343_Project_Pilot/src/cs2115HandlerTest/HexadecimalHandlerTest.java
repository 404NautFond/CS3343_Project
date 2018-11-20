package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.HexadecimalHandler;
import fxcelException.InvalidExpressionException;

public class HexadecimalHandlerTest {

	private HexadecimalHandler hexadecimal;
	
	@Before
	public void setup() {
		hexadecimal= new HexadecimalHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testHexadecimalDoubleReturn_01() {
		double res = hexadecimal.handleForDoubleReturn("1+1");
		assertEquals(2, res, 0.0001);
	}
	
	@Test
	public void testHexadecimalStringReturn_01() {
		String res = hexadecimal.handleForStringReturn(17);
		assertEquals("0x11", res);
	}
	
}