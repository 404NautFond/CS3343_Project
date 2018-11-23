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
	
	@Test (expected = InvalidExpressionException.class)
	public void testASCIIDoubleReturn_01() {
		ascii.handleForDoubleReturn("A但adaf");
	}

	@Test
	public void testASCIIDoubleReturn_02() {
		assertEquals(0,ascii.handleForDoubleReturn("ABCED"),1);
	}
	
	@Test
	public void testASCIIStringReturn_01() {
		String res = ascii.handleForStringReturn("A");
		assertEquals("65 ", res);
	}
	
	@Test
	public void testASCIIStringReturn_02() {
		String res = ascii.handleForStringReturn("=ASCII(A)");
		assertEquals("65 ", res);
	}
	
	@Test
	public void testASCIIStringReturn_03() {
		String res = ascii.handleForStringReturn("KJH*&Ja-c");
		assertEquals("75 74 72 42 38 74 97 45 99 ", res);
	}
}
