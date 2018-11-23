package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import fxcel.Fxcel;
import fxcelHandler.DeleteHandler;

public class DeleteHandlerTest {
	
	private Fxcel fxcel;
	private DeleteHandler deleteHandler;
	
	@Before
	public void setUp() {
		fxcel = Fxcel.getInstance();
		deleteHandler = new DeleteHandler();
	}
	
	@After
	public void tearDown() {
		fxcel.clear();
	}
	
	@Test
	public void testDelete() {
		fxcel.writeCell(0,0,"=879");
		deleteHandler.handle("A1");
		assertEquals(0,fxcel.getCellValue("A1"),0.0001);
	}
}
