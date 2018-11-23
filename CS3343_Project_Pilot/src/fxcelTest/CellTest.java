package fxcelTest;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import java.lang.reflect.*;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.*;

public class CellTest {
	
	private Cell cell;
	private Method method;
	
	@Before
	public void setup() {
		cell = new Cell();
		try {
			method = cell.getClass().getDeclaredMethod("assign", String.class);
			method.setAccessible(true);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() {
		Fxcel.getInstance().clear();
	}
	

	//Test Case: ":text"
	@Test(expected = InvalidCellException.class)
	public void testAssign_01() throws InvalidCellException{
		try {
			method.invoke(cell, ":text");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(":text",cell.getExpression());
		assertEquals(0,cell.getValue(),0.0001);
	}
	
	//Test Case: "=1+2"
	@Test
	public void testAssign_02() throws InvalidCellException{
		try {
			method.invoke(cell, "=1+2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(3,cell.getValue(),0.0001);
	}
	
	//Test Case: "=3("
	@Test
	public void testAssign_03() throws InvalidCellException{
		try {
			method.invoke(cell, "=3(");
			assertEquals(0,cell.getValue(),0.0001);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Assign Method Test Case: "=A1+B1"
	@Test
	public void testAssign_04() throws InvalidCellException{
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0,0,"=1");
			fxcel.writeCell(0,1,"=-1");
			method.invoke(cell, "=A1+B1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(0,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	//Assign Method Test Case: "=A1+B1*A1+B1"
	@Test
	public void testAssign_05() throws InvalidCellException{
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0,0,"=1");
			fxcel.writeCell(0,1,"=-1");
			method.invoke(cell, "=A1+B1*A1+B1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(-1,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	//Assign Method Test Case: "=A1+B1+A2+B2"
	@Test
	public void testAssign_06() throws InvalidCellException{
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0,0,"=1");
			fxcel.writeCell(0,1,"=-1");
			fxcel.writeCell(1,1,"=10");
			fxcel.writeCell(1,0,"=11");
			method.invoke(cell, "=A1+B1+A2+B2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(21,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	//Assign Method Test Case: "=A1+B1-A2/B2"
	@Test
	public void testAssign_07() throws InvalidCellException{
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0,0,"=1");
			fxcel.writeCell(0,1,"=-1");
			fxcel.writeCell(1,1,"=10");
			fxcel.writeCell(1,0,"=11");
			method.invoke(cell, "=A1+B1-A2/B2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(-1.1,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	//Assign Method Test Case: "=A1*B2"
	@Test
	public void testAssign_08() throws InvalidCellException{
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0,0,"=1");
			fxcel.writeCell(1,1,"=10");
			method.invoke(cell, "=A1*B2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(10,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	//Assign Method Test Case: "=F10*(A1+B2)/C3"
	@Test
	public void testAssign_09() throws InvalidCellException{
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(9,5,"=-2.5");
			fxcel.writeCell(1,1,"=999");
			fxcel.writeCell(0,0,"=-99");
			fxcel.writeCell(2,2,"3");
			method.invoke(cell, "=F10*(A1+B2)/C3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(-750,cell.getValue(),0.0001);
		fxcel.clear();
	}	
	//Assign Method Test Case: "=F10*(A1+B2)/C3"
	@Test
	public void testAssign_10() throws InvalidCellException{
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0,1,"=30");
			fxcel.writeCell(1,1,"=2");
			fxcel.writeCell(0,0,"=B2+B1");
			method.invoke(cell, "=B1+A1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(62,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	@Test
	public void testAssign_11() throws InvalidCellException{
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0,1,"=30");
			fxcel.writeCell(1,1,"=2");
			fxcel.writeCell(0,0,"=B2+B1+A1");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fxcel.clear();
		}
	}
	
	@Test 
	public void testAssign_12() throws InvalidCellException{
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0,1,"=30");
			fxcel.writeCell(1,1,"=A1");
			fxcel.writeCell(0,0,"=B2+B1+A1");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fxcel.clear();
		}
	}
	
	@Test
	public void testAddDependent_01(){
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0, 0, "=1");
			fxcel.writeCell(0, 1, "=2");
			method.invoke(cell, "=SUM(A1:B1)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(3,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
  /*
   * TODO Figure out why Test08 & 10 will throw exception when testing  with other test cases 
   * 	but will run normally when separately testing
   */
	
	
	//Test Case: ":This is the text test"
	@Test
	public void testToString_01() {
		try {
			method.invoke(cell, ":This is the text test");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("null: The expression is \":This is the text test\", The value is \"0.0\"",cell.toString());
	}
	
	//Test Case: ":This is the text test"
	@Test
	public void testToString_02() {
		try {
			method.invoke(cell, "=2+5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("null: The expression is \"=2+5\", The value is \"7.0\"",cell.toString());
	}
	
	//Test Case: "A1:"		
	@Test
	public void testToString_03(){
	Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,0,"=1");
		cell = fxcel.getCell(0,0);
		assertEquals("A1: The expression is \"=1\", The value is \"1.0\"",cell.toString());
		fxcel.clear();
	}
	
	//Test Case: "AB1:"		
	@Test
	public void testToString_04(){
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,27,"=1");
		cell = fxcel.getCell(0,27);
		assertEquals("AB1: The expression is \"=1\", The value is \"1.0\"",cell.toString());
		fxcel.clear();
	}
	
	//Test Case: "A1:"		
	@Test
	public void testGetPos_01(){
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0,0,"=1");
			cell = fxcel.getCell(0,0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("A1",cell.getPosition());
		fxcel.clear();
	}

	//Test Case: "AB1:"		
	@Test
	public void testGetPos_02(){
		Fxcel fxcel = Fxcel.getInstance();
		try {
			fxcel.writeCell(0,27,":text");
			cell = fxcel.getCell(0,27);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("AB1",cell.getPosition());
		fxcel.clear();
	}
//	
//	@Test
//	public void testGetValue_01() {
//		Fxcel fxcel = Fxcel.getInstance();
//		fxcel.writeCell(0,27,":text");
//		cell = fxcel.getCell(0,27);
//		try {
//			cell.getValue();
//		} catch (InvalidCellException e) {
//			assertEquals(e.getMessage(),"AB1(Expression:\":text\") is not legal for this computation.");
//		}
//	}
}
