package fxcelTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import fxcelHandler.ExpHandler;
import fxcelHandler.GeneralHandler;
import fxcelException.InvalidExpressionException;

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
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_10() {
		double results = generalHandler.handleForDoubleReturn("=3)");
		assertEquals(0,results,0.0001);
	}
	
	//Test Case "=2*(3+2))"
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_11() {
		double results = generalHandler.handleForDoubleReturn("=2*(3+2))");
		assertEquals(0,results,0.0001);
	}
	 
	//Test Case "=2*(3+2)(" 
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_12() {
		double results = generalHandler.handleForDoubleReturn("=2*(3+2)(");//Test for Line 123
		assertEquals(0,results,0.0001);
	}
	
	//Test Case "=2&3"
	@Test(expected = InvalidExpressionException.class)
	public void testHandleForDoubleReturn_13() {
		double results = generalHandler.handleForDoubleReturn("=2&3");
		assertEquals(0,results,0.0001);
	}
	
	@Test
	public void TestIsFunc_01() {
		assertEquals(GeneralHandler.isFunc("SUM"),true);
	}
	
	@Test
	public void TestIsFunc_02() {
		assertEquals(GeneralHandler.isFunc("AVE"),true);
	}
	
	@Test
	public void TestIsFunc_03() {
		assertEquals(GeneralHandler.isFunc("MIN"),true);
	}
	
	@Test
	public void TestIsFunc_04() {
		assertEquals(GeneralHandler.isFunc("MAX"),true);
	}
	
	@Test
	public void TestIsFunc_05() {
		assertEquals(GeneralHandler.isFunc("COUNT"),true);
	}
	
	@Test
	public void TestIsFunc_06() {
		assertEquals(GeneralHandler.isFunc("COMB"),true);
	}
	
	@Test
	public void TestIsFunc_07() {
		assertEquals(GeneralHandler.isFunc("PERM"),true);
	}
	
	@Test
	public void TestIsFunc_08() {
		assertEquals(GeneralHandler.isFunc("MEAN"),true);
	}
	
	@Test
	public void TestIsFunc_09() {
		assertEquals(GeneralHandler.isFunc("SD"),true);
	}
	
	@Test
	public void TestIsFunc_10() {
		assertEquals(GeneralHandler.isFunc("VAR"),true);
	}
	
	@Test
	public void TestIsFunc_11() {
		assertEquals(GeneralHandler.isFunc("VAR."),false);
	}
}