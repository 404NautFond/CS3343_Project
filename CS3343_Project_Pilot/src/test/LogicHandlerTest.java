package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.*;
import fxcelException.InvalidExpressionException;

public class LogicHandlerTest {
	
	private ANDHandler and;
	private NOTHandler not;
	private ORHandler or;
	private NANDHandler nand;
	private NORHandler nor;
	private XNORHandler xnor;
	private XORHandler xor;
	
	@Before
	public void setup() {
		and = new ANDHandler();
		not = new NOTHandler();
		or = new ORHandler();
		nand = new NANDHandler();
		nor = new NORHandler();
		xnor = new XNORHandler();
		xor = new XORHandler();
	}
	
	@After
	public void tearDown() {
		
	}
	
	/*
	 * ANDHandler Test Cases
	 */
	
	@Test
	public void testAND_01() {
		double res = and.handleForDoubleReturn("1,1");
		assertEquals(1, res, 0.01);
	}
	
	@Test
	public void testAND_02() {
		double res = and.handleForDoubleReturn("TRUE,1");
		assertEquals(1, res, 0.01);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testAND_03() {
		and.handleForDoubleReturn("TURE,2");
	}
	
	@Test
	public void testAND_04() {
		double res = and.handleForDoubleReturn("FALSE,1");
		assertEquals(0, res, 0.01);
	}
	
	@Test
	public void testAND_05() {
		assertEquals(1,and.handleForDoubleReturn("AND(1,1),AND(1,1)"),0.0001);
	}
	
	/*
	 * NOTHandler Test Cases
	 */
	
	@Test
	public void testNOT_01() {
		double res = not.handleForDoubleReturn("1");
		assertEquals(0, res, 0.01);
	}
	
	@Test
	public void testNOT_02() {
		double res = not.handleForDoubleReturn("TRUE");
		assertEquals(0, res, 0.01);
	}
	
	@Test
	public void testNOT_03() {
		assertEquals(1, not.handleForDoubleReturn("=1-1"), 0.01);
	}
	
	@Test
	public void testNOT_04() {
		double res = not.handleForDoubleReturn("0");
		assertEquals(1, res, 0.01);
	}
	
	@Test
	public void testNOT_05() {
		double res = not.handleForDoubleReturn("FALSE");
		assertEquals(1, res, 0.01);
	}
	
	/*
	 * ORHandler Test Cases
	 */
	
	@Test
	public void testOR_01() {
		double res = or.handleForDoubleReturn("1,1");
		assertEquals(1,res,0.01);
	}
	
	@Test
	public void testOR_02() {
		double res = or.handleForDoubleReturn("1,0");
		assertEquals(1,res,0.01);
	}
	
	@Test
	public void testOR_03() {
		double res = or.handleForDoubleReturn("0,0");
		assertEquals(0,res,0.01);
	}
	
	@Test
	public void testOR_04() {
		double res = or.handleForDoubleReturn("FALSE,0");
		assertEquals(0,res,0.01);
	}
	
	@Test
	public void testOR_05() {
		double res = or.handleForDoubleReturn("FALSE,TRUE");
		assertEquals(1,res,0.01);
	}
	
	@Test
	public void testOR_06() {
		double res = or.handleForDoubleReturn("0,OR(1,1)");
		assertEquals(1,res,0.01);
	}
	
	/*
	 * NANDHandler Test Cases
	 */
	
	@Test
	public void testNAND_01() {
		double res = nand.handleForDoubleReturn("1,1");
		assertEquals(0, res, 0.01);
	}
	
	/*
	 * NORHandler Test Cases
	 */
	
	@Test
	public void testNOR_01() {
		double res = nor.handleForDoubleReturn("1,1");
		assertEquals(0, res, 0.01);
	}
	
	/*
	 * XNORHandler Test Cases
	 */
	
	@Test
	public void testXNOR_01() {
		double res = xnor.handleForDoubleReturn("1,1");
		assertEquals(1, res, 0.01);
	}
	
	@Test
	public void testXNOR_02() {
		double res = xnor.handleForDoubleReturn("1,0");
		assertEquals(0, res, 0.01);
	}
	
	@Test
	public void testXNOR_03() {
		double res = xnor.handleForDoubleReturn("0,1");
		assertEquals(0, res, 0.01);
	}
	
	@Test
	public void testXNOR_04() {
		double res = xnor.handleForDoubleReturn("0,0");
		assertEquals(1, res, 0.01);
	}
	
	/*
	 * XORHandler Test Cases
	 */
	
	@Test
	public void testXOR_01() {
		double res = xor.handleForDoubleReturn("1,1");
		assertEquals(0, res, 0.01);
	}
	
	@Test
	public void testXOR_02() {
		double res = xor.handleForDoubleReturn("1,0");
		assertEquals(1, res, 0.01);
	}
	
	@Test
	public void testXOR_03() {
		double res = xor.handleForDoubleReturn("0,1");
		assertEquals(1, res, 0.01);
	}
	
	@Test
	public void testXOR_04() {
		double res = xor.handleForDoubleReturn("0,0");
		assertEquals(0, res, 0.01);
	}

}
