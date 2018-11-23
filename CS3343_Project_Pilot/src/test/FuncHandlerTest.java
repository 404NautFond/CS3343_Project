package test;

import static org.junit.Assert.*;
import org.junit.*;

import fxcelHandler.FuncHandler;

public class FuncHandlerTest {
	
	@Test
	public void testColumnHandler_01() {
		class FuncConcreteClass extends FuncHandler{
			public double handleForDoubleReturn(String expression) {
				return 0;
			}
		}
		FuncConcreteClass funcHandler = new FuncConcreteClass();
		assertNull(null,funcHandler.columnHandler("adsfdf"));
	}
	
	@Test
	public void testColumnHandler_02() {
		class FuncConcreteClass extends FuncHandler{
			public double handleForDoubleReturn(String expression) {
				return 0;
			}
		}
		FuncConcreteClass funcHandler = new FuncConcreteClass();
		
		assertArrayEquals(new String[]{"A1","B2"},funcHandler.columnHandler("A1:B2"));
	}
	
	@Test
	public void testColumnHandler_03() {
		class FuncConcreteClass extends FuncHandler{
			public double handleForDoubleReturn(String expression) {
				return 0;
			}
		}
		FuncConcreteClass funcHandler = new FuncConcreteClass();
		
		assertNull(funcHandler.columnHandler("A1:B2:C3"));
	}
}
