package ma2127HandlerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ma2172Handler.CombinitionHandler;
import fxcel.Fxcel;
import fxcelException.InvalidExpressionException;

public class CombinitionHandlerTest {
	
	private Fxcel fxcel;
	private CombinitionHandler combinitionHandler;
	
	@Before
	public void setup() {
		fxcel = Fxcel.getInstance();
		combinitionHandler = new CombinitionHandler();
	}
	
	@After
	public void tearDown() {
		fxcel.clear();
	}	
	
	@Test
	public void testHandleForDoubleRetur() {
		
	}
}
