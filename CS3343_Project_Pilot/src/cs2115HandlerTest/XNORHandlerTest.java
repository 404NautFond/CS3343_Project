package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.XNORHandler;
import fxcelException.InvalidExpressionException;

public class XNORHandlerTest {

	private XNORHandler xnor;
	
	@Before
	public void setup() {
		xnor = new XNORHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testXNOR_01() {
		double res = xnor.handleForDoubleReturn("1,1");
		assertEquals(1, res, 0.01);
	}
	
	@Test
	public void testXNOR_02() {
		double res = xnor.handleForDoubleReturn("1,0");
		assertEquals(0, res, 0.01);
	}
	
	@Test
	public void testXNOR_03() {
		double res = xnor.handleForDoubleReturn("0,1");
		assertEquals(0, res, 0.01);
	}
	
	@Test
	public void testXNOR_04() {
		double res = xnor.handleForDoubleReturn("0,0");
		assertEquals(1, res, 0.01);
	}

}
