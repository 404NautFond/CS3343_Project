package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.NOTHandler;
import fxcelException.InvalidExpressionException;

public class NOTHandlerTest {

	private NOTHandler not;
	
	@Before
	public void setup() {
		not = new NOTHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testNOT_01() {
		double res = not.handleForDoubleReturn("1");
		assertEquals(0, res, 0.01);
	}
	
	@Test
	public void testNOT_02() {
		double res = not.handleForDoubleReturn("TRUE");
		assertEquals(0, res, 0.01);
	}
	
	@Test (expected = InvalidExpressionException.class)
	public void testNOT_03() {
		assertEquals(0, not.handleForDoubleReturn("=0+1"), 0.01);
	}
	
	@Test
	public void testNOT_04() {
		double res = not.handleForDoubleReturn("0");
		assertEquals(1, res, 0.01);
	}
	
	@Test
	public void testNOT_05() {
		double res = not.handleForDoubleReturn("FALSE");
		assertEquals(1, res, 0.01);
	}

}