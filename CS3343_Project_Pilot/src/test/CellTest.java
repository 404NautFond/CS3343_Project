package test;

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
	public void setup() throws Exception{
		cell = new Cell();
		method = cell.getClass().getDeclaredMethod("assign", String.class);
		method.setAccessible(true);
	}
	
	@After
	public void tearDown() {
		Fxcel.getInstance().clear();
	}
	
	@Test(expected = InvalidCellException.class)
	public void testAssign_01() throws Exception{
		method.invoke(cell, ":text");
		assertEquals(":text",cell.getExpression());
		cell.getValue();
	}
	
	@Test
	public void testAssign_02() throws Exception{
		method.invoke(cell, "=1+2");
		assertEquals(3,cell.getValue(),0.0001);
	}
	
	@Test(expected = InvalidCellException.class)
	public void testAssign_03() throws Exception{
		method.invoke(cell, "=3(");
		cell.getValue();
	}

	@Test
	public void testAssign_04() throws Exception{
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,0,"=1");
		fxcel.writeCell(0,1,"=-1");
		method.invoke(cell, "=A1+B1");
		assertEquals(0,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	@Test
	public void testAssign_05() throws Exception{
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,0,"=1");
		fxcel.writeCell(0,1,"=-1");
		method.invoke(cell, "=A1+B1*A1+B1");
		assertEquals(-1,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	@Test
	public void testAssign_06() throws Exception{
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,0,"=1");
		fxcel.writeCell(0,1,"=-1");
		fxcel.writeCell(1,1,"=10");
		fxcel.writeCell(1,0,"=11");
		method.invoke(cell, "=A1+B1+A2+B2");
		assertEquals(21,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	@Test
	public void testAssign_07() throws Exception{
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,0,"=1");
		fxcel.writeCell(0,1,"=-1");
		fxcel.writeCell(1,1,"=10");
		fxcel.writeCell(1,0,"=11");
		method.invoke(cell, "=A1+B1-A2/B2");
		assertEquals(-1.1,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	@Test
	public void testAssign_08() throws Exception{
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,0,"=1");
		fxcel.writeCell(1,1,"=10");
		method.invoke(cell, "=A1*B2");
		assertEquals(10,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	@Test
	public void testAssign_09() throws Exception{
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(9,5,"=-2.5");
		fxcel.writeCell(1,1,"=999");
		fxcel.writeCell(0,0,"=-99");
		fxcel.writeCell(2,2,"3");
		method.invoke(cell, "=F10*(A1+B2)/C3");
		assertEquals(-750,cell.getValue(),0.0001);
		fxcel.clear();
	}	
	
	
	@Test
	public void testAssign_10() throws Exception{
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,1,"=30");
		fxcel.writeCell(1,1,"=2");
		fxcel.writeCell(0,0,"=B2+B1");
		method.invoke(cell, "=B1+A1");
		assertEquals(62,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	@Test
	public void testAssign_11() throws Exception{
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,1,"=30");
		fxcel.writeCell(1,1,"=2");
		fxcel.writeCell(0,0,"=B2+B1+A1");
		fxcel.clear();
	}
	
	@Test
	public void testAssign_12() throws InvalidCellException{
		Fxcel fxcel = Fxcel.getInstance();
		assertEquals(0,fxcel.getCell(0,0).getValue(),0.0001);
	}
	
	@Test 
	public void testAssign_13() throws Exception{
		method.invoke(cell, "=ASCII(A)");
		assertEquals("65 ",cell.getTextual());
	}
	
	@Test 
	public void testAssign_14(){
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0, 0, "=A1");
		fxcel.writeCell(0, 0, "=2");
		fxcel.clear();
	}
	
	@Test 
	public void testAssign_15() throws Exception{
		method.invoke(cell, "");
		assertEquals("",cell.getExpression());
	} 
	
	@Test
	public void testAssign_16() throws Exception{
		method.invoke(cell, "tRue");
		assertEquals(1,cell.getValue(),0.0001);
	}
	
	@Test
	public void testAssign_17() throws Exception{
		method.invoke(cell, "FalSE");
		assertEquals(0,cell.getValue(),0.0001);
	}
	
	@Test
	public void testAssign_18() throws Exception{
		method.invoke(cell, "=1+AND(1,1)");
		assertEquals(2,cell.getValue(),0.0001);
	}
	
	@Test 
	public void testAssign_19(){
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(0, 1, "=A1+10");
		fxcel.writeCell(0, 0, "=3");
		assertEquals(13,fxcel.getCell(0,1).getValue(),0.0001);
	}
	
	@Test
	public void testAssign_20() throws Exception{
		method.invoke(cell, "=AND(AND(1,1),1)");
		assertEquals("TRUE",cell.getTextual());
	}
	
	@Test
	public void testAssign_21() throws Exception{
		method.invoke(cell, "LaLa");
		assertEquals("LaLa",cell.getTextual());
	}
	
	
	@Test 
	public void testCheckDep_01(){
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0, 0, "=B1");
		fxcel.writeCell(0, 1, "=C1");
		fxcel.writeCell(0, 2, "=A1");
		fxcel.clear();
	}
	
	@Test
	public void testAddDependent_01() throws Exception{
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		method.invoke(cell, "=SUM(A1:B1)");
		assertEquals(3,cell.getValue(),0.0001);
		fxcel.clear();
	}
	
	@Test
	public void testToString_01() throws Exception{
		method.invoke(cell, ":This is the text test");
		assertEquals("null: The expression is \":This is the text test\", The value is \"0.0\"",cell.toString());
	}
	
	@Test
	public void testToString_02() throws Exception{
		method.invoke(cell, "=2+5");
		assertEquals("null: The expression is \"=2+5\", The value is \"7.0\"",cell.toString());
	}
		
	@Test
	public void testToString_03(){
	Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,0,"=1");
		cell = fxcel.getCell(0,0);
		assertEquals("A1: The expression is \"=1\", The value is \"1.0\"",cell.toString());
		fxcel.clear();
	}
		
	@Test
	public void testToString_04(){
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,27,"=1");
		cell = fxcel.getCell(0,27);
		assertEquals("AB1: The expression is \"=1\", The value is \"1.0\"",cell.toString());
		fxcel.clear();
	}
	
	@Test
	public void testToString_05(){
		Fxcel fxcel = Fxcel.getInstance();
		assertEquals("A1: The expression is \"null\"",fxcel.getCell(0,0).toString());
		fxcel.clear();
	}
		
	@Test
	public void testGetPos_01(){
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,0,"=1");
		cell = fxcel.getCell(0,0);
		assertEquals("A1",cell.getPosition());
		fxcel.clear();
	}
	
	@Test
	public void testGetPos_02(){
		Fxcel fxcel = Fxcel.getInstance();
		fxcel.writeCell(0,27,":text");
		cell = fxcel.getCell(0,27);
		assertEquals("AB1",cell.getPosition());
		fxcel.clear();
	}

}
