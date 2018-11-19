package commonHandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commonHandler.SumHandler;
import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class SumHandlerTest {
	private Fxcel fxcel;
	private SumHandler sum;
	@Before
	public void setup() {
		fxcel = Fxcel.getInstance();
		sum = new SumHandler();
	}

	@After
	public void tearDown() {
		fxcel.clear();
	}
	@Test
	public void testHandler_01() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		assertEquals(3,sum.handleForDoubleReturn("A1:B1"), 0.0001);
	}

	@Test
	public void testHandler_02() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(1, 0, "=1");
		fxcel.writeCell(2, 0, "=1");
		assertEquals(3,sum.handleForDoubleReturn("A1,A2,A3"),0.001);
		fxcel.writeCell(2, 0, "=2");
		assertEquals(4,sum.handleForDoubleReturn("A1,A2,A3"),0.001);
	}

	@Test(expected = InvalidExpressionException.class)
	public void testHandler_03() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(1, 0, ":1");
		fxcel.writeCell(2, 0, "=2");
		assertEquals(1,sum.handleForDoubleReturn("A1,A2,A3"),0.001);
	}
	
	@Test
	public void testHandler_04() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(2, 0, "=2");
		assertEquals(5,sum.handleForDoubleReturn("A1,2,A3"),0.001);
	}
	
	@Test
	public void testHandler_05() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(2, 0, "=2");
		assertEquals(5,sum.handleForDoubleReturn("A1,1+1,A3"),0.001);
	}
	
	@Test
	public void testHandler_06() {
		assertEquals(5,sum.handleForDoubleReturn("SUM(1,1),1+1,1"),0.001);
	}
}
