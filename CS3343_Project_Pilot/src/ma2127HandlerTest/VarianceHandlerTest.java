package ma2127HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ma2172Handler.VarianceHandler;
import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class VarianceHandlerTest {
	
	private Fxcel fxcel;
	private VarianceHandler varianceHandler;
	
	@Before
	public void setup() {
		fxcel = Fxcel.getInstance();
		varianceHandler = new VarianceHandler();
	}
	
	@After
	public void tearDown() {
		fxcel.clear();
	}	
	
	@Test
	public void testHandleForDoubleReturn_01() {
		fxcel.writeCell(0, 0, "=10");
		fxcel.writeCell(0, 1, "=9");
		assertEquals(0.25,varianceHandler.handleForDoubleReturn("A1:B1"), 0.0001);
	}
	
	@Test
	public void testHandleForDoubleReturn_02() {
		fxcel.writeCell(0, 0, "=10");
		fxcel.writeCell(0, 1, "=9");
		fxcel.writeCell(1, 0, "=4");
		fxcel.writeCell(1, 1, "=0.5");
		fxcel.writeCell(2, 0, "=9.5");
		fxcel.writeCell(2, 1, "=10093.4");
		assertEquals(14131058.03889,varianceHandler.handleForDoubleReturn("A1:B3"), 0.0001);
	}
	
	@Test
	public void testHandleForDoubleReturn_03() {
		fxcel.writeCell(0, 0, "=10");
		fxcel.writeCell(0, 1, "=9");
		fxcel.writeCell(1, 0, "=4");
		fxcel.writeCell(1, 1, "=0.5");
		fxcel.writeCell(2, 0, "=9.5");
		fxcel.writeCell(2, 1, "=10093.4");
		assertEquals(14131058.03889,varianceHandler.handleForDoubleReturn("A1,A2,A3,B1,B2,B3"), 0.0001);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_04() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(0, 1, "=1");
		varianceHandler.handleForDoubleReturn("A1|B1");
	}
	@Test
	public void testHandleForDoubleReturn_05() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(0, 1, "=1");
		assertEquals(1,varianceHandler.handleForDoubleReturn("A1,C1"), 0.0001);
	}
}
