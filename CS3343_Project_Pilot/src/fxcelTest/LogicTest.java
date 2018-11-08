package fxcelTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fxcel.Fxcel;

public class LogicTest {
	private Fxcel instance;
	@Before
	public void setup() {
		instance = Fxcel.getInstance();
	}
	
	@After
	public void tearDown() {
		instance.clear();
	}
	
	@Test
	public void ANDTest_00() {
		instance.writeCell(0, 0, "=1");
		instance.writeCell(0, 1, "=1");
		instance.writeCell(0, 2, "=AND(A1,B1)");
		assertEquals(1,instance.getCellValue("A1"),0.01);
		assertEquals(1,instance.getCellValue("C1"),0.01);
	}
	
	@Test
	public void ANDTest_01() {
		instance.writeCell(0, 0, "=1");
//		instance.writeCell(0, 1, "=1");
		instance.writeCell(0, 2, "=AND(A1,1)");
		assertEquals(1,instance.getCellValue("A1"),0.01);
		assertEquals(1,instance.getCellValue("C1"),0.01);
	}
	
	@Test
	public void ANDTest_02() {
		instance.writeCell(0, 0, "0");
//		instance.writeCell(0, 1, "=1");
		instance.writeCell(0, 2, "=AND(A1,1)");
		assertEquals(0,instance.getCellValue("A1"),0.01);
		assertEquals(0,instance.getCellValue("C1"),0.01);
	}
}
