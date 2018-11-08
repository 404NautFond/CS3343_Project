package ma2127HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ma2172Handler.CombinitionHandler;
import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class CombinitionHandlerTest {
	
	private Fxcel fxcel;
	private CombinitionHandler combinitionHandler;
	
	@Before
	public void setup() {
		fxcel = Fxcel.getInstance();
		combinitionHandler = new CombinitionHandler();
	}
	
	@After
	public void tearDown() {
		fxcel.clear();
	}	
	
	@Test
	public void testHandleForDoubleReturn_01() {
		fxcel.writeCell(0, 0, "=10");
		fxcel.writeCell(0, 1, "=9");
		assertEquals(10,combinitionHandler.handleForDoubleReturn("A1,B1"), 0.0001);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_02() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(0, 1, "=1");
		combinitionHandler.handleForDoubleReturn("A1,C1");
	}

}
