package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import fxcelHandler.CellNamingHandler;

public class CellNamingHandlerTest {
	
	//Test Case row = "0"
	@Test
	public void testGetRowEnhanced_01() {
		int result = CellNamingHandler.getRowEnhanced("0");
		assertEquals(-1,result, 0.0001);
	}
	
	//Test Case row = "1"
	@Test
	public void testGetRowEnhanced_02() {
		int result = CellNamingHandler.getRowEnhanced("1");
		assertEquals(1,result, 0.0001);
	}
	
	//Test Case row = "30"
	@Test
	public void testGetRowEnhanced_03() {
		int result = CellNamingHandler.getRowEnhanced("30");
		assertEquals(30,result, 0.0001);
	}
	
	//Test Case row = "100"
	@Test
	public void testGetRowEnhanced_04() {
		int result = CellNamingHandler.getRowEnhanced("100");
		assertEquals(100,result, 0.0001);
	}
	
	//Test Case row = "-30"
	@Test
	public void testGetRowEnhanced_05() {
		int result = CellNamingHandler.getRowEnhanced("-30");
		assertEquals(-1,result, 0.0001);
	}
	
	//Test Case row = "1024"
	@Test
	public void testGetRowEnhanced_06() {
		int result = CellNamingHandler.getRowEnhanced("1024");
		assertEquals(1024,result, 0.0001);
	}
	
	//Test Case row = ""
	@Test (expected = NumberFormatException.class)
	public void testGetRowEnhanced_07() {
		CellNamingHandler.getRowEnhanced("");
	}
	
	//Test Case row = ""
	@Test (expected = NumberFormatException.class)
	public void testGetRowEnhanced_08() {
		CellNamingHandler.getRowEnhanced("A");
	}
	
	//Test Case col = "E"
	@Test
	public void testGetColumnEnhanced_01() {
		int result = CellNamingHandler.getColumnEnhanced("E");
		assertEquals(5,result, 0.0001);
	}
	
	//Test Case col = "AE"
	@Test
	public void testGetColumnEnhanced_02() {
		int result = CellNamingHandler.getColumnEnhanced("AA");
		assertEquals(27,result);
	}
	
	//Test Case col = "EE"
	@Test
	public void testGetColumnEnhanced_03() {
		int result = CellNamingHandler.getColumnEnhanced("EE");
		assertEquals(135,result);
	}
	
	//Test Case col = "-D"
	@Test
	public void testGetColumnEnhanced_04() {
		int result = CellNamingHandler.getColumnEnhanced("-D");
		assertEquals(-1,result);
	}
	
	//Test Case col = "##"
	@Test
	public void testGetColumnEnhanced_05() {
		int result = CellNamingHandler.getColumnEnhanced("##");
		assertEquals(-1,result);
	}
	
	@Test
	public void testGetColumnEnhanced_06() {
		int result = CellNamingHandler.getColumnEnhanced("23");
		assertEquals(-1,result);
	}
}
