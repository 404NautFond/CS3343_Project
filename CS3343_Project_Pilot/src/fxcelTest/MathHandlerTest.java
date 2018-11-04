package fxcelTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fxcel.Fxcel;
import fxcelException.InvalidCellException;

public class MathHandlerTest {
	Fxcel instance = Fxcel.getInstance();
	@Before
	public void setup() {
		instance.writeCell(0, 0, "1");
		instance.writeCell(0, 1, "2");
		instance.writeCell(0, 2, "3");
		instance.writeCell(1, 0, "1");
		instance.writeCell(1, 1, "2");
		instance.writeCell(1, 2, "3");
	}
	@After
	public void tearDown() {
		instance.clear();
	}
	
	@Test
	public void testCombinition_00() {
		instance.writeCell(2, 0, "=COMB(C1,B1)");
		try {
			assertEquals(instance.getCellValue("A3"),3,0.001);
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testPermutation_00() {
		instance.writeCell(2, 0, "=PERM(C1,B1)");
		try {
			assertEquals(instance.getCellValue("A3"),6,0.001);
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testMean_00() {
		instance.writeCell(2, 0, "=MEAN(C1,B1)");
		try {
			assertEquals(instance.getCellValue("A3"),2.5,0.001);
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMean_01() {
		instance.writeCell(2, 0, "=MEAN(A1:C1)");
		try {
			assertEquals(instance.getCellValue("A3"),2,0.001);
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStanDevi_00() {
		instance.writeCell(2, 0, "=SD(A1:C1)");
		try {
			assertEquals(instance.getCellValue("A3"),0.8165,0.001);
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVariance_00() {
		instance.writeCell(2, 0, "=VAR(A1:C1)");
		try {
			assertEquals(instance.getCellValue("A3"),0.6667,0.001);
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
