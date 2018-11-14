package cs2115HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cs2115Handler.ConvertHandler;

public class ConvertHandlerTest {

	private ConvertHandler conv;
	
	@Before
	public void setup() {
		conv = new ConvertHandler();
	}
	
	@Test
	public void testGeneral_00() {
		String res = conv.convertTo(2, 10);
		assertEquals("1010",res);
	}
	
	@Test
	public void testGeneral_01() {
		String res = conv.convertTo(16, 10);
		assertEquals("A",res);
	}
	
	@Test
	public void testGeneral_02() {
		String res = conv.convertTo(2, 10.5);
		assertEquals("1010.1",res);
	}
	
	@Test
	public void testGeneral_03() {
		String res = conv.convertTo(2, 10.3333);
		assertEquals("1010.01010",res);
	}
	
}
