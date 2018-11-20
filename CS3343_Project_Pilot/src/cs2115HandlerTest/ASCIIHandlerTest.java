package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.ASCIIHandler;
import fxcelException.InvalidExpressionException;

public class ASCIIHandlerTest {

	private ASCIIHandler ascii;
	
	@Before
	public void setup() {
		ascii= new ASCIIHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testASCIIDoubleReturn_01() {
		double res = ascii.handleForDoubleReturn("A+B");
		assertEquals("0b1010", res);
	}
	
	@Test
	public void testASCIIStringReturn_01() {
		String res = ascii.handleForStringReturn("A");
		assertEquals("65 ", res);
	}
}
