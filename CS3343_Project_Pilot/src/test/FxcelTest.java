package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import fxcel.Fxcel;

public class FxcelTest {

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
	public void testWriteCell_01() {
		fxcel.writeCell(0, 0, "=)1+3");
		assertEquals("#INVALID#",fxcel.getTextualValue("A1"));
	}
	
	@Test
	public void testWriteCell_02() {
		fxcel.writeCell(0, 0, "=1&3");
		assertEquals("#INVALID#",fxcel.getTextualValue("A1"));
	}
	
	@Test
	public void testWriteCell_03() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=B1+A1");
		assertEquals("#INF#",fxcel.getTextualValue("B1"));
	}
	
	@Test
	public void testGetterByName_01() {
		fxcel.writeCell(0, 0, ":1");
		assertEquals(":1",fxcel.getCellExpression("A1"));
	}
	
	@Test
	public void testGetterByName_02() {
		fxcel.writeCell(0, 1, "= B1+1");
		assertEquals("= B1+1",fxcel.getCellExpression("B1"));
		assertEquals("#INF#",fxcel.getTextualValue("B1"));
	}
	
	@Test
	public void testGetterByLocate_01() {
		fxcel.writeCell(0, 0, ":1");
		assertEquals(":1",fxcel.getCellExpression(0,0));
	}
	
	@Test
	public void testResize_01() {
		fxcel.resize(50, 50);
		assertEquals(null,fxcel.getCell(50,50).getExpression());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testResize_02() {
		fxcel.resize(50, 50);
		assertEquals(null,fxcel.getCell(100,100).getExpression());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testResize_03() {
		fxcel.resize(70, 70);
		assertEquals(null,fxcel.getCell(70,70).getExpression());
	}

	@Test
	public void testAddCol_01() {
		for(int i = 0; i < 10; i++) {
			fxcel.writeCell(0, i, "="+i);
		}
		System.out.println(fxcel);
		assertEquals("=5",fxcel.getCell(0,5).getExpression());
		fxcel.addCol(5);
		assertEquals(null,fxcel.getCell(0,5).getExpression());
		assertEquals("=5",fxcel.getCell(0,6).getExpression());
	}
	
	@Test//(expected = IndexOutOfBoundsException.class)
	public void testAddCol_02() {
		fxcel.getCell(30,0);
	}
	
	@Test//(expected = IndexOutOfBoundsException.class)
	public void testAddCol_03() {
		fxcel.addCol(2);
		fxcel.getCell(30,0);
	}

	@Test//(expected = AssertionError.class)
	public void testAddCol_04() {
		fxcel.writeCell(30, 0, "=1");
		fxcel.addCol(30);
		assertEquals("=1",fxcel.getCell(30,0).getExpression());
	}
	
	@Test//(expected = AssertionError.class)
	public void testAddCol_05() {
		fxcel.writeCell(30, 0, "=1");
		fxcel.addCol(31);
		assertEquals("=1",fxcel.getCell(30,0).getExpression());
	}
	
	@Test
	public void testAddRow_01() {
		fxcel.writeCell("E1", "=1");
		fxcel.addRow(0);
		assertEquals(null,fxcel.getCell(0,4).getExpression());
		assertEquals("=1",fxcel.getCell(1,4).getExpression());
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddRow_02() {
		fxcel.getCell(0,30);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddRow_03() {
		fxcel.addRow(2);
		fxcel.getCell(0,30);
	}

	@Test//(expected = AssertionError.class)
	public void testAddRow_04() {
		fxcel.writeCell(0, 29, "=1");
		fxcel.addRow(31);
	}
	
	@Test
	public void testAddRow_05() {
		fxcel.writeCell(0, 20, "=1");
		fxcel.addCol(2);
		assertEquals(null,fxcel.getCell(0,30).getExpression());
	}
	
	@Test
	public void testGetCellValue() {
		fxcel.writeCell(0, 0, "=1");
		assertEquals(1,fxcel.getCellValue(0,0),0.0001);
		assertEquals(1,fxcel.getCellValue("A1"),0.0001);
	}

	/*
	@Test
	public void testToString() {
		assertEquals(null,fxcel.toString());
	}
	*/

}
