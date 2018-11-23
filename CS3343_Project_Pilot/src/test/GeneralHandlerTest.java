package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void testCalcu_01() {
		double results = generalHandler.handleForDoubleReturn("=1");
		assertEquals(1,results,0.0001);
	}
	
	@Test
	public void testCalcu_03() {
		double results = generalHandler.handleForDoubleReturn("=1-2*2/3");
		assertEquals(-0.3333,results,0.0001);
	}
	
	@Test
	public void testCalcu_07() {
		double results = generalHandler.handleForDoubleReturn("=2+2*(2+69/33)");
		assertEquals(10.1818,results,0.0001);
	}
	
	@Test
	public void testCalcu_08() {
		double results = generalHandler.handleForDoubleReturn("=((3))"); 
		assertEquals(3,results,0.0001);
	}
	
	@Test
	public void testCalcu_09() {
		double results = generalHandler.handleForDoubleReturn("=2*((2+2+69/33)*(2/4+2-100))");
		assertEquals(-1187.7273,results,0.0001);
	}

	@Test(expected = InvalidExpressionException.class)
	public void testCalcu_10() {
		generalHandler.handleForDoubleReturn("=3)");
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testCalcu_11() {
		generalHandler.handleForDoubleReturn("=2*(3+2))");
	}
	 
	@Test(expected = InvalidExpressionException.class)
	public void testCalcu_12() {
		generalHandler.handleForDoubleReturn("=2*(3+2)(");
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testCalcu_13() {
		generalHandler.handleForDoubleReturn("=2&3");
	}
	
	@Test
	public void testCalcu_14() {
		double results = generalHandler.handleForDoubleReturn("=((-3))"); 
		assertEquals(-3,results,0.0001);
	}
	
	@Test
	public void testCalcu_15() {
		double results = generalHandler.handleForDoubleReturn("=-(2+(-3))"); 
		assertEquals(1,results,0.0001);
	}
	
	@Test
	public void TestFunc_01() {
		assertEquals(3, new GeneralHandler().handleForDoubleReturn("SUM(1,1)+1"),0.0001);
	}
	
	@Test
	public void TestFunc_02() {
		assertEquals(1, new GeneralHandler().handleForDoubleReturn("AND(AND(1,1),1)"),0.0001);
	}
	
	@Test
	public void TestFunc_03() {
		assertEquals("0b1110110", new GeneralHandler().handlerForStringReturn("=GREY(91)"));
	}
	
	@Test
	public void TestFunc_04() {
		assertEquals("TRUE", new GeneralHandler().handlerForStringReturn("=AND(AND(1,1),1)"));
	}
	
	@Test
	public void TestFunc_05() {
		assertEquals("FALSE", new GeneralHandler().handlerForStringReturn("=NAND(AND(1,1),1)"));
	}
	
	@Test
	public void TestFunc_06() {
		assertEquals(10, new GeneralHandler().handleForDoubleReturn("COMB(10,9)"),0.0001);
	}

}
