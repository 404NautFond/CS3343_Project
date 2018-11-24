package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import fxcelHandler.ExpHandler;

public class ExpHandlerTest {
	
	
	@Before
	public void setup() {}
	
	@After
	public void tearDown() {}
	
	@Test
	public void TestIsNumeric_01() {
		assertEquals(ExpHandler.isNumeric("9.0320"),true);
	}
	
	@Test
	public void TestIsNumeric_02() {
		assertEquals(ExpHandler.isNumeric("3:45Ab"),false);
	}
	
	@Test
	public void TestIsNumeric_03() {
		assertEquals(ExpHandler.isNumeric("-3.0"),true);
	}
	
	@Test
	public void TestIsNumeric_04() {
		assertEquals(ExpHandler.isNumeric("-A10"),false);
	}
	
	@Test
	public void TestIsNumeric_05() {
		assertEquals(ExpHandler.isNumeric(".123"),true);
	}
	
	@Test
	public void TestIsCell_01() {
		assertEquals(ExpHandler.isCell("A10"),true);
	}
	
	@Test
	public void TestIsCell_02() {
		assertEquals(ExpHandler.isCell("-A10"),true);
	}
	
	@Test
	public void TestIsCell_03() {
		assertEquals(ExpHandler.isCell("AAA10"),true);
	}
	
	@Test
	public void TestIsCell_04() {
		assertEquals(ExpHandler.isCell("+V10"),false);
	}
	
	@Test
	public void TestIsCell_05() {
		assertEquals(ExpHandler.isCell("+V10.1"),false);
	}
	
}