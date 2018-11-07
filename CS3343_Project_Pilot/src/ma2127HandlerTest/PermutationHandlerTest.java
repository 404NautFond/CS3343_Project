package ma2127HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ma2172Handler.PermutationHandler;
import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class PermutationHandlerTest {
	
	private Fxcel fxcel;
	private PermutationHandler permutationHandler;
	
	@Before
	public void setup() {
		fxcel = Fxcel.getInstance();
		permutationHandler = new PermutationHandler();
	}
	
	@After
	public void tearDown() {
		fxcel.clear();
	}	
	
	@Test
	public void testHandleForDoubleReturn_01() {
		fxcel.writeCell(0, 0, "=10");
		fxcel.writeCell(0, 1, "=9");
		assertEquals(3628800,permutationHandler.handleForDoubleReturn("A1,B1"), 0.0001);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_02() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(0, 1, "=1");
		permutationHandler.handleForDoubleReturn("A1:C1");
	}
	
//	@Test(expected = AssertionError.class)
//	public void testHandleForDoubleReturn_03() {
//		fxcel.writeCell(0, 0, "=2");
//		fxcel.writeCell(0, 1, "=1");
//		permutationHandler.handleForDoubleReturn("A1,C1");
//	}

}
