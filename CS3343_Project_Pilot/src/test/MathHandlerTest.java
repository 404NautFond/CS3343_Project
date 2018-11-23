package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;
import ma2172Handler.*;

public class MathHandlerTest {
	Fxcel fxcel = Fxcel.getInstance();
	private PermutationHandler permutationHandler;
	private CombinitionHandler combinitionHandler;
	private MeanHandler meanHandler;
	private VarianceHandler varianceHandler;
	private StandDeviHandler standDeviHandler;
	
	@Before
	public void setup() {
		permutationHandler = new PermutationHandler();
		combinitionHandler = new CombinitionHandler();
		meanHandler = new MeanHandler();
		varianceHandler = new VarianceHandler();
		standDeviHandler = new StandDeviHandler();
	}
	@After
	public void tearDown() {
		fxcel.clear();
	}
	
	/*
	 * PermutationHandler Test Cases
	 */
	
	@Test
	public void testPermutationHandler_01() {
		assertEquals(3628800,permutationHandler.handleForDoubleReturn("10,9"), 0.0001);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testPermutationHandler_02() {
		permutationHandler.handleForDoubleReturn("10:9");
	}
	
	/*
	 * CombinitionHandler Test Cases
	 */
	
	@Test
	public void testCombinitionHandler_01() {
		assertEquals(10,combinitionHandler.handleForDoubleReturn("10,9"), 0.0001);
	}
	
	@Test
	public void testCombinitionHandler_02() {
		assertEquals(2,combinitionHandler.handleForDoubleReturn("2,1"), 0.0001);
	}
	
	@Test (expected = InvalidExpressionException.class)
	public void testCombinitionHandler_03() {
		assertEquals(2,combinitionHandler.handleForDoubleReturn("@@@"), 0.0001);
	}
	
	/*
	 * MeanHandler Test Cases
	 */
	
	@Test
	public void testMeanHandler_01() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		assertEquals(1.5,meanHandler.handleForDoubleReturn("A1:B1"), 0.0001);
	}
	
	@Test
	public void testMeanHandler_02() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		fxcel.writeCell(0, 2, "=10");
		fxcel.writeCell(0, 3, "=20");
		assertEquals(8.25,meanHandler.handleForDoubleReturn("A1,B1,C1,D1"), 0.0001);
	}

	@Test
	public void testMeanHandler_03() {
		fxcel.writeCell(0, 0, "=1");
		fxcel.writeCell(0, 1, "=2");
		fxcel.writeCell(0, 2, "=10");
		fxcel.writeCell(0, 3, "=20");
		assertEquals(6.6,meanHandler.handleForDoubleReturn("A1,B1,C1,D1,E1"), 0.0001);
	}
	
	@Test (expected = InvalidExpressionException.class)
	public void testMeanHandler_04() {
		meanHandler.handleForDoubleReturn("A1B1-C4E5");
	}
	
	/*
	 * VarianceHandler Test Cases 
	 */
	
	@Test
	public void testVarianceHandler_01() {
		fxcel.writeCell(0, 0, "=10");
		fxcel.writeCell(0, 1, "=9");
		assertEquals(0.25,varianceHandler.handleForDoubleReturn("A1:B1"), 0.0001);
	}
	
	@Test
	public void testVarianceHandler_02() {
		fxcel.writeCell(0, 0, "=10");
		fxcel.writeCell(0, 1, "=9");
		fxcel.writeCell(1, 0, "=4");
		fxcel.writeCell(1, 1, "=0.5");
		fxcel.writeCell(2, 0, "=9.5");
		fxcel.writeCell(2, 1, "=10093.4");
		assertEquals(14131058.03889,varianceHandler.handleForDoubleReturn("A1:B3"), 0.0001);
	}
	
	@Test
	public void testVarianceHandler_03() {
		fxcel.writeCell(0, 0, "=10");
		fxcel.writeCell(0, 1, "=9");
		fxcel.writeCell(1, 0, "=4");
		fxcel.writeCell(1, 1, "=0.5");
		fxcel.writeCell(2, 0, "=9.5");
		fxcel.writeCell(2, 1, "=10093.4");
		assertEquals(14131058.03889,varianceHandler.handleForDoubleReturn("A1,A2,A3,B1,B2,B3"), 0.0001);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testVarianceHandler_04() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(0, 1, "=1");
		varianceHandler.handleForDoubleReturn("A1|B1");
	}
	@Test
	public void testVarianceHandler_05() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(0, 1, "=1");
		assertEquals(1,varianceHandler.handleForDoubleReturn("A1,C1"), 0.0001);
	}
	
	/*
	 * StandDevihandler Test Cases
	 */
	
	@Test
	public void testStandDeviHandler_02() {
		fxcel.writeCell(0, 0, "=10");
		fxcel.writeCell(0, 1, "=9");
		fxcel.writeCell(1, 0, "=4");
		fxcel.writeCell(1, 1, "=0.5");
		fxcel.writeCell(2, 0, "=9.5");
		fxcel.writeCell(2, 1, "=10093.4");
		assertEquals(3759.1300,standDeviHandler.handleForDoubleReturn("A1:B3"), 0.0001);
	}
	
	@Test
	public void testStandDeviHandler_03() {
		fxcel.writeCell(0, 0, "=10");
		fxcel.writeCell(0, 1, "=9");
		fxcel.writeCell(1, 0, "=4");
		fxcel.writeCell(1, 1, "=0.5");
		fxcel.writeCell(2, 0, "=9.5");
		fxcel.writeCell(2, 1, "=10093.4");
		assertEquals(3759.1300,standDeviHandler.handleForDoubleReturn("A1,A2,A3,B1,B2,B3"), 0.0001);
	}
	
	@Test(expected = InvalidExpressionException.class)
	public void testStandDeviHandler_04() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(0, 1, "=1");
		standDeviHandler.handleForDoubleReturn("A1|C1");
	}
	
	@Test
	public void testStandDeviHandler_05() {
		fxcel.writeCell(0, 0, "=2");
		fxcel.writeCell(0, 1, "=1");
		assertEquals(1,standDeviHandler.handleForDoubleReturn("A1,C1"), 0.0001);
	}
	
}

