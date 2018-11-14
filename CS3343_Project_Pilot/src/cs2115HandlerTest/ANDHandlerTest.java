package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.ANDHandler;
import fxcelException.InvalidExpressionException;

public class ANDHandlerTest {

	private ANDHandler and;
	
	@Before
	public void setup() {
		and = new ANDHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testAND_01() {
		double res = and.handleForDoubleReturn("1,1");
		assertEquals(1, res, 0.01);
	}
	
	@Test
	public void testAND_02() {
		double res = and.handleForDoubleReturn("TRUE,1");
		assertEquals(1, res, 0.01);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testAND_03() {
		and.handleForDoubleReturn("TURE,2");
	}
	
	@Test
	public void testAND_04() {
		double res = and.handleForDoubleReturn("FALSE,1");
		assertEquals(0, res, 0.01);
	}
	
//	@Test
//	public void testAND_05() {
//		and.handleForDoubleReturn("AND(1,1),1");
//	}
}
