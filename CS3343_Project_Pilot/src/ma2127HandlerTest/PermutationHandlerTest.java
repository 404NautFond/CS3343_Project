package ma2127HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ma2172Handler.PermutationHandler;
import fxcelException.InvalidExpressionException;

public class PermutationHandlerTest {
	
	private PermutationHandler permutationHandler;
	
	@Before
	public void setup() {
		permutationHandler = new PermutationHandler();
	}
	
	@After
	public void tearDown() {
	}	
	
	@Test
	public void testHandleForDoubleReturn_01() {
		assertEquals(3628800,permutationHandler.handleForDoubleReturn("10,9"), 0.0001);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_02() {
		permutationHandler.handleForDoubleReturn("10:9");
	}
	
//	@Test(expected = AssertionError.class)
//	public void testHandleForDoubleReturn_03() {
//		fxcel.writeCell(0, 0, "=2");
//		fxcel.writeCell(0, 1, "=1");
//		permutationHandler.handleForDoubleReturn("A1,C1");
//	}

}
