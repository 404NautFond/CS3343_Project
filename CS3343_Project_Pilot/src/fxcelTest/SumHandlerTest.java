package fxcelTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fxcel.Fxcel;
import fxcelException.InvalidCellException;

public class SumHandlerTest {
	private Fxcel fxcel;
	@Before
	public void setup() {
		fxcel = Fxcel.getInstance();
	}
	
	@After
	public void tearDown() {
		fxcel.clear();
	}
	@Test
	public void testHandler_01() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		fxcel.writeCell(0, 2, "=SUM(A1:B1)");
		
		try {
			assertEquals(3,fxcel.getCellValue(0,2), 0.0001);
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHandler_02() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(1, 0, "=1");
		fxcel.writeCell(2, 0, "=1");
		fxcel.writeCell(3, 0, "=SUM(A1,A2,A3)");
		fxcel.writeCell(2, 0, "=2");
//		System.out.println("Q1");
		try {
			assertEquals(4,fxcel.getCellValue("A4"),0.001);
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
//		fxcel.writeCell(1, 0, "1");
//		try {
//			assertEquals(1,fxcel.getCellValue("A1"),0.0001);
//		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
