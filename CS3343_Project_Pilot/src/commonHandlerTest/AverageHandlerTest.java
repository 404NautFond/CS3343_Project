package commonHandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commonHandler.AverageHandler;
import fxcel.*;
import fxcelException.InvalidExpressionException;

public class AverageHandlerTest {
	
	private Fxcel fxcel;
	private AverageHandler averageHandler;
	
	@Before
	public void setup() {
		fxcel = Fxcel.getInstance();
		averageHandler = new AverageHandler();
	}
	
	@After
	public void tearDown() {
		fxcel.clear();
	}
	
	@Test
	public void testHandleForDoubleReturn_01() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		assertEquals(1.5,averageHandler.handleForDoubleReturn("A1:B1"), 0.0001);
	}
	
	@Test
	public void testHandleForDoubleReturn_02() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		fxcel.writeCell(0, 2, "=10");
		fxcel.writeCell(0, 3, "=20");
		assertEquals(8.25,averageHandler.handleForDoubleReturn("A1,B1,C1,D1"), 0.0001);
	}
	
	@Test
	public void testHandleForDoubleReturn_03() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		fxcel.writeCell(0, 2, "=10");
		fxcel.writeCell(0, 3, "=20");
		assertEquals(6.6,averageHandler.handleForDoubleReturn("A1,B1,C1,D1,E1"), 0.0001);
	}
	
	@Test (expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_04() {
		averageHandler.handleForDoubleReturn("A1B1-C4E5");
	}
	
}
