package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cs2115Handler.ORHandler;

public class ORHandlerTest {

	private ORHandler or;
	
	@Before
	public void setup() {
		or = new ORHandler();
	}
	
	@Test
	public void testOR_00() {
		double res = or.handleForDoubleReturn("1,1");
		assertEquals(1,res,0.01);
	}
	
	@Test
	public void testOR_01() {
		double res = or.handleForDoubleReturn("1,0");
		assertEquals(1,res,0.01);
	}
	
	@Test
	public void testOR_02() {
		double res = or.handleForDoubleReturn("0,0");
		assertEquals(0,res,0.01);
	}
	
	@Test
	public void testOR_03() {
		double res = or.handleForDoubleReturn("FALSE,0");
		assertEquals(0,res,0.01);
	}
	
	@Test
	public void testOR_04() {
		double res = or.handleForDoubleReturn("FALSE,TRUE");
		assertEquals(1,res,0.01);
	}
	
	@Test
	public void testOR_05() {
		double res = or.handleForDoubleReturn("0,OR(1,1)");
		assertEquals(1,res,0.01);
	}
}
