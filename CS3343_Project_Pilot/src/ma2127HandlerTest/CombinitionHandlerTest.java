package ma2127HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ma2172Handler.CombinitionHandler;
import fxcelException.InvalidExpressionException;

public class CombinitionHandlerTest {
	
	private CombinitionHandler combinitionHandler;
	
	@Before
	public void setup() {
		combinitionHandler = new CombinitionHandler();
	}
	
	@After
	public void tearDown() {
	}	
	
	@Test
	public void testHandleForDoubleReturn_01() {
		assertEquals(10,combinitionHandler.handleForDoubleReturn("10,9"), 0.0001);
	}
	
	@Test
	public void testHandleForDoubleReturn_02() {
		assertEquals(2,combinitionHandler.handleForDoubleReturn("2,1"), 0.0001);
	}
	
	@Test (expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_03() {
		assertEquals(2,combinitionHandler.handleForDoubleReturn("@@@"), 0.0001);
	}

}
