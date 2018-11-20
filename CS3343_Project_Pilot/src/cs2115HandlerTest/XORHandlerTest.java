package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.XORHandler;
import fxcelException.InvalidExpressionException;

public class XORHandlerTest {

	private XORHandler xor;
	
	@Before
	public void setup() {
		xor = new XORHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testXOR_01() {
		double res = xor.handleForDoubleReturn("1,1");
		assertEquals(0, res, 0.01);
	}
	
	@Test
	public void testXOR_02() {
		double res = xor.handleForDoubleReturn("1,0");
		assertEquals(1, res, 0.01);
	}
	
	@Test
	public void testXOR_03() {
		double res = xor.handleForDoubleReturn("0,1");
		assertEquals(1, res, 0.01);
	}
	
	@Test
	public void testXOR_04() {
		double res = xor.handleForDoubleReturn("0,0");
		assertEquals(0, res, 0.01);
	}

}