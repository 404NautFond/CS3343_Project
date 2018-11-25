package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commonHandler.*;
import fxcel.*;
import fxcelException.InvalidCellException;
import fxcelException.InvalidExpressionException;

public class CommonHandlerTest {
	
	private Fxcel fxcel;
	private SumHandler sum;
	private CountHandler countHandler;
	private MaxHandler maxHandler;
	private MinHandler minHandler;
	private AverageHandler averageHandler;
	
	@Before
	public void setup() {
		fxcel = Fxcel.getInstance();
		sum = new SumHandler();
		countHandler = new CountHandler();
		maxHandler = new MaxHandler();
		minHandler = new MinHandler();
		averageHandler = new AverageHandler();
	}
	
	@After
	public void tearDown() {
		fxcel.clear();
	}
	
	/*
	 * SumHandler Test Cases
	 */
	
	@Test
	public void testSumHandler_01() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		assertEquals(3,sum.handleForDoubleReturn("A1:B1"), 0.0001);
	}

	@Test
	public void testSumHandler_02() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(1, 0, "=1");
		fxcel.writeCell(2, 0, "=1");
		assertEquals(3,sum.handleForDoubleReturn("A1,A2,A3"),0.001);
		fxcel.writeCell(2, 0, "=2");
		assertEquals(4,sum.handleForDoubleReturn("A1,A2,A3"),0.001);
	}

	@Test(expected = InvalidExpressionException.class)
	public void testSumHandler_03() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(1, 0, ":1");
		fxcel.writeCell(2, 0, "=2");
		assertEquals(1,sum.handleForDoubleReturn("A1,A2,A3"),0.001);
	}
	
	@Test
	public void testSumHandler_04() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(2, 0, "=2");
		assertEquals(5,sum.handleForDoubleReturn("A1,2,A3"),0.001);
	}
	
	@Test
	public void testSumHandler_05() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(2, 0, "=2");
		assertEquals(5,sum.handleForDoubleReturn("A1,1+1,A3"),0.001);
	}
	
	@Test
	public void testSumHandler_06() {
		assertEquals(5,sum.handleForDoubleReturn("SUM(1,1),1+1,1"),0.001);
	}
	
	/*
	 * CountHandler Test Cases
	 */
	
	@Test
	public void testCountHandler_01() {
		assertEquals(25,countHandler.handleForDoubleReturn("A1:E5"),1);
	}
	
	@Test
	public void testCountHandler_02() {
		assertEquals(4,countHandler.handleForDoubleReturn("A1,B1,C4,E5"),1);
	}
	
	@Test
	public void testCountHandler_03() {
		assertEquals(10,countHandler.handleForDoubleReturn("1,1,4,5,,123,74356,1,0,0.1"),1);
	}
	
	@Test (expected = InvalidExpressionException.class)
	public void testCountHandler_04() {
		countHandler.handleForDoubleReturn("A1B1-C4E5");
	}
	
	/*
	 * MaxHandler Test Cases
	 */
	
	@Test
	public void testMaxHandler_01() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(1, 0, "=2");
		assertEquals(2.0,maxHandler.handleForDoubleReturn("A1:A2"),0.0001);
	}
	
	@Test
	public void testMaxHandler_02() {
		fxcel.writeCell(0, 0, "=3");
		fxcel.writeCell(1, 0, "=2");
		fxcel.writeCell(2, 0, "=4");
		assertEquals(4.0,maxHandler.handleForDoubleReturn("A1,A2,A3"),0.0001);
	}
	
	@Test
	public void testMaxHandler_03() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(1, 0, "=3");
		fxcel.writeCell(2, 0, "=3");
		assertEquals(3.0,maxHandler.handleForDoubleReturn("A1,A2,A3"),0.0001);
	}
	
	@Test
	public void testMaxHandler_04() {
		fxcel.writeCell(0, 0, "=2.001");
		fxcel.writeCell(1, 0, "=2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		assertEquals(2.01,maxHandler.handleForDoubleReturn("A1,A2,A3"),0.0001);
	}
	
	@Test
	public void testMaxHandler_05() {
		fxcel.writeCell(1, 0, "=2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		assertEquals(2.01,maxHandler.handleForDoubleReturn("A2:A3"),0.0001);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testMaxHandler_06() {
		fxcel.writeCell(0, 0, "=2.001");
		fxcel.writeCell(1, 0, "=2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		maxHandler.handleForDoubleReturn("A1%A2%A3");
	}
	
	@Test
	public void testMaxHandler_07() {
		fxcel.writeCell(1, 0, ":2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		try {
			maxHandler.handleForDoubleReturn("A2:A3");
		}catch(InvalidCellException e) {
			System.out.println(e);
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testMaxHandler_08() {
		fxcel.writeCell(1, 0, ":2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		try {
			System.out.println(maxHandler.handleForDoubleReturn("A1,A2,A3"));
		}catch(InvalidExpressionException e) {
//			e = new InvalidExpressionException(fxcel.getCell("A1"));
			System.out.println(e);
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * MinHandler Test Cases
	 */
	
	@Test
	public void testMinHandler_01() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(1, 0, "=2");
		assertEquals(1.0,minHandler.handleForDoubleReturn("A1:A2"),0.0001);
	}
	
	@Test
	public void testMinHandler_02() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(1, 0, "=2");
		fxcel.writeCell(2, 0, "=3");
		assertEquals(1.0,minHandler.handleForDoubleReturn("A1,A2,A3"),0.0001);
	}
	
	@Test
	public void testMinHandler_03() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(1, 0, "=2");
		fxcel.writeCell(2, 0, "=3");
		assertEquals(2.0,minHandler.handleForDoubleReturn("A1,A2,A3"),0.0001);
	}
	
	@Test
	public void testMinHandler_04() {
		fxcel.writeCell(0, 0, "=2.001");
		fxcel.writeCell(1, 0, "=2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		assertEquals(2.0003,minHandler.handleForDoubleReturn("A1,A2,A3"),0.0001);
	}
	
	@Test
	public void testMinHandler_05() {
		fxcel.writeCell(1, 0, "=2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		assertEquals(2.0003,minHandler.handleForDoubleReturn("A2:A3"),0.0001);
	}
	
	@Test
	public void testMinHandler_06() {
		fxcel.writeCell(0, 0, "=2.001");
		fxcel.writeCell(1, 0, "=2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		try {
			minHandler.handleForDoubleReturn("A1%A2%A3");
		}catch(InvalidExpressionException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test(expected = InvalidCellException.class)
	public void testMinHandler_07() {
		fxcel.writeCell(1, 0, ":2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		minHandler.handleForDoubleReturn("A2:A3");
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testMinHandler_08() {
		fxcel.writeCell(1, 0, ":2.01");
		fxcel.writeCell(2, 0, "=2.0003");
		minHandler.handleForDoubleReturn("A1,A2,A3");
	}
	
	/*
	 * AverageHandler Test Cases
	 */
	
	@Test
	public void testAverageHandler_01() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		assertEquals(1.5,averageHandler.handleForDoubleReturn("A1:B1"), 0.0001);
	}
	
	@Test
	public void testAverageHandler_02() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		fxcel.writeCell(0, 2, "=10");
		fxcel.writeCell(0, 3, "=20");
		assertEquals(8.25,averageHandler.handleForDoubleReturn("A1,B1,C1,D1"), 0.0001);
	}
	
	@Test
	public void testAverageHandler_03() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		fxcel.writeCell(0, 2, "=10");
		fxcel.writeCell(0, 3, "=20");
		assertEquals(6.6,averageHandler.handleForDoubleReturn("A1,B1,C1,D1,E1"), 0.0001);
	}
	
	@Test (expected = InvalidExpressionException.class)
	public void testAverageHandler_04() {
		averageHandler.handleForDoubleReturn("A1B1-C4E5");
	}
}