package cs2115HandlerTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs2115Handler.LogicHandler;
import fxcelException.InvalidExpressionException;

public class LogicHandlerTest {

	
	@Before
	public void setup() {
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testIsTrueLike_01() {
		assertTrue(LogicHandler.isTrueLike("2"));
	}
	
	@Test
	public void testIsTrueLike_02() {
		assertTrue(LogicHandler.isTrueLike("TRUE"));
	}
	
	@Test
	public void testIsTrueLike_03() {
		assertFalse(LogicHandler.isTrueLike("False"));
	}
	
	@Test
	public void testIsTrueLike_04() {
		assertTrue(LogicHandler.isTrueLike("1+3"));
	}
	
	@Test
	public void testIsTrueLike_05() {
		assertFalse(LogicHandler.isTrueLike("0"));
	}
	
	@Test
	public void testIsFalseLike_01() {
		assertTrue(LogicHandler.isFalseLike("0"));
	}
	
	@Test
	public void testIsFalseLike_02() {
		assertFalse(LogicHandler.isFalseLike("TRUE"));
	}
	
	@Test
	public void testIsFalseLike_03() {
		assertTrue(LogicHandler.isFalseLike("False"));
	}
	
	@Test
	public void testIsFalseLike_04() {
		assertTrue(LogicHandler.isFalseLike("2-2"));
	}
	
	@Test
	public void testIsFalseLike_05() {
		assertFalse(LogicHandler.isFalseLike("2"));
	}
	
	
}

