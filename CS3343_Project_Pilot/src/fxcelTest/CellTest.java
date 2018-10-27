package fxcelTest;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import java.lang.reflect.*;

import fxcel.Cell;
import fxcel.Fxcel;
import fxcelException.InvalidCellException;

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
	public void tearDown() {}
	
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0,cell.getValue(),0.0001);
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
 
}
