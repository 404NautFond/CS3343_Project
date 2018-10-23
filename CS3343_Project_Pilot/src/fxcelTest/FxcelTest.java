package fxcelTest;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import fxcel.Fxcel;
import fxcelException.InvalidCellException;

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
	public void testGetterByName_01() {
		fxcel.writeCell(0, 0, ":1");
		assertEquals(":1",fxcel.getCell("A1").getExpression());
	}
	
	@Test
	public void testGetterByName_02() {
		fxcel.writeCell(0, 1, "=1");
		assertEquals("=1",fxcel.getCellExpression("B1"));
		try {
			assertEquals(1,fxcel.getCellValue("B1"),0.0001);
		} catch (InvalidCellException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetterByLocate_01() {
		fxcel.writeCell(0, 0, ":1");
		assertEquals(":1",fxcel.getCell(0,0).getExpression());
	}
	

}
