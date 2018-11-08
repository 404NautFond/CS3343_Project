package commonHandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commonHandler.MaxHandler;
import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class MaxHandlerTest {
	
	private Fxcel fxcel;
	private MaxHandler maxHandler;
	
	@Before
	public void setup() {
		fxcel = Fxcel.getInstance();
		maxHandler = new MaxHandler();
	}
	
	@After
	public void tearDown() {
		fxcel.clear();
	}
	
	@Test
	public void testHandleForDoubleReturn_01() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(1, 0, "=2");
		assertEquals(2.0,maxHandler.handleForDoubleReturn("A1:A2"),0.0001);
	}
	
	@Test
	public void testHandleForDoubleReturn_02() {
		fxcel.writeCell(0, 0, "=3");
		fxcel.writeCell(1, 0, "=2");
		fxcel.writeCell(2, 0, "=4");
		assertEquals(4.0,maxHandler.handleForDoubleReturn("A1,A2,A3"),0.0001);
	}
	
	@Test
	public void testHandleForDoubleReturn_03() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(1, 0, "=3");
		fxcel.writeCell(2, 0, "=3");
		assertEquals(3.0,maxHandler.handleForDoubleReturn("A1,A2,A3"),0.0001);
	}
	
	@Test
	public void testHandleForDoubleReturn_04() {
		fxcel.writeCell(0, 0, "=2.001");
		fxcel.writeCell(1, 0, "=2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		assertEquals(2.01,maxHandler.handleForDoubleReturn("A1,A2,A3"),0.0001);
	}
	
	@Test
	public void testHandleForDoubleReturn_05() {
		fxcel.writeCell(1, 0, "=2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		assertEquals(2.01,maxHandler.handleForDoubleReturn("A2:A3"),0.0001);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_06() {
		fxcel.writeCell(0, 0, "=2.001");
		fxcel.writeCell(1, 0, "=2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		maxHandler.handleForDoubleReturn("A1%A2%A3");
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_07() {
		fxcel.writeCell(1, 0, ":2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		maxHandler.handleForDoubleReturn("A2:A3");
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_08() {
		fxcel.writeCell(1, 0, ":2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		System.out.println(maxHandler.handleForDoubleReturn("A1,A2,A3"));
	}
	
//	@Test
//	public void testHandleForDoubleReturn_07() {
//		fxcel.writeCell(0, 0, "=2.001");
//		fxcel.writeCell(1, 0, "=2.01");
//		fxcel.writeCell(2, 0, "=2.0003");
//		assertEquals(2.0003,minHandler.handleForDoubleReturn("(A1+A2):A3"),0.0001);
//	}
}
