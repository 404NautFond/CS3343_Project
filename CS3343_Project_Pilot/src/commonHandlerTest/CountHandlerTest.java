package commonHandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commonHandler.CountHandler;
import fxcelException.InvalidExpressionException;

public class CountHandlerTest {
	
	private CountHandler countHandler;
	
	@Before
	public void setup() {
		countHandler = new CountHandler();
	}
	
	@After
	public void tearDown() {}
	
	@Test
	public void testHandleForDoubleReturn_01() {
		assertEquals(25,countHandler.handleForDoubleReturn("A1:E5"),1);
	}
	
	@Test
	public void testHandleForDoubleReturn_02() {
		assertEquals(4,countHandler.handleForDoubleReturn("A1,B1,C4,E5"),1);
	}
	
	@Test
	public void testHandleForDoubleReturn_03() {
		assertEquals(10,countHandler.handleForDoubleReturn("1,1,4,5,,123,74356,1,0,0.1"),1);
	}
	
	@Test (expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_04() {
		countHandler.handleForDoubleReturn("A1B1-C4E5");
	}
	
}
