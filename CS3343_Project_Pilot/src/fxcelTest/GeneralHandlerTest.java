package fxcelTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fxcelHandler.GeneralHandler;
//import fxcelException.InvalidExpressionException;

public class GeneralHandlerTest {
	
	private GeneralHandler generalHandler;
	
	@Before
	public void setup() {
		generalHandler = new GeneralHandler();
	}
	
	@After
	public void tearDown() {}
	
	//Test Case "=1"
	@Test
	public void testHandleForDoubleReturn_01() {
		double results = generalHandler.handleForDoubleReturn("=1");
		assertEquals(1,results,0.0001);
	}
	 
//	//Test Case "=2^3"
//	@Test
//	public void testHandleForDoubleReturn_02() {
//		double results = generalHandler.handleForDoubleReturn("=2^3");
//		assertEquals(8,results,0.0001);
//	}
	
	//Test Case "=1+2*2/3"
	@Test
	public void testHandleForDoubleReturn_03() {
		double results = generalHandler.handleForDoubleReturn("=1-2*2/3");
		assertEquals(-0.3333,results,0.0001);
	}
	
//	//Test Case "=2*2^13"
//	@Test
//	public void testHandleForDoubleReturn_04() {
//		double results = generalHandler.handleForDoubleReturn("=2*2^13");
//		assertEquals(16384,results,0.0001);
//	}
//	
//	//Test Case "=2/2^2"
//	@Test
//	public void testHandleForDoubleReturn_05() {
//		double results = generalHandler.handleForDoubleReturn("=2/2^2");
//		assertEquals(0.5,results,0.0001);
//	}
//	
//	//Test Case "=2+2^13"
//	@Test
//	public void testHandleForDoubleReturn_06() {
//		double results = generalHandler.handleForDoubleReturn("=2+2^13");
//		assertEquals(8194,results,0.0001);
//	}
	
	//Test Case "=2+(2+69/33)*2"
	@Test
	public void testHandleForDoubleReturn_07() {
		double results = generalHandler.handleForDoubleReturn("=2+2*(2+69/33)");
		assertEquals(10.1818,results,0.0001);
	}
	
	//Test Case "=((3))"
	@Test
	public void testHandleForDoubleReturn_08() {
		double results = generalHandler.handleForDoubleReturn("=((3))"); 
		assertEquals(3,results,0.0001);
	}
	
	//Test Case "=2*((2+(2+69/33))*(2/4+2-100))"
	@Test
	public void testHandleForDoubleReturn_09() {
		double results = generalHandler.handleForDoubleReturn("=2*((2+2+69/33)*(2/4+2-100))");
		assertEquals(-1187.7273,results,0.0001);
	}

	//Test Case "=3)"
	@Test
	public void testHandleForDoubleReturn_10() {
		double results = generalHandler.handleForDoubleReturn("=3)");
		assertEquals(0,results,0.0001);
	}
	
	//Test Case "=2*(3+2))"
	@Test
	public void testHandleForDoubleReturn_11() {
		double results = generalHandler.handleForDoubleReturn("=2*(3+2))");
		assertEquals(0,results,0.0001);
	}
	 
	//Test Case "=2*(3+2)(" 
	@Test
	public void testHandleForDoubleReturn_12() {
		double results = generalHandler.handleForDoubleReturn("=2*(3+2)(");//Test for Line 123
		assertEquals(0,results,0.0001);
	}
	
	//Test Case "=2&3"
	@Test
	public void testHandleForDoubleReturn_13() {
		double results = generalHandler.handleForDoubleReturn("=2&3");
		assertEquals(0,results,0.0001);
	}
}