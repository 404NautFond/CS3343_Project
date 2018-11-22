package fxcelTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fxcel.*;

public class SystemTestByRichardLi {

	private Fxcel instance;
	
	@Before
	public void setup(){
		instance = Fxcel.getInstance();
	}
	
	@After
	public void tearDown() {
		instance.clear();
	}
	
	public void validateCell(double exp_val, String exp_text, double act_val, String act_text) {
		assertEquals(exp_val, act_val, 0.0001);
		assertEquals(exp_text, act_text);
	}
	
	@Test
	public void writeCell_00() {
		// 1-1 Strong Text
		instance.writeCell(0,0,":1");
		assertEquals("1",instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_01() {
		// 1-2 Expression without functions and computations
		instance.writeCell(0,0,"=1");
		validateCell(1,"1.00",instance.getCellValue(0,0),instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_02() {
		// 1-3 Expression without function call but with computations
		instance.writeCell(0,0,"=2+3");
		validateCell(5,"5.00",instance.getCellValue(0,0),instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_03() {
		// 1-4 Weak Numeric
		instance.writeCell(0,0,"1");
		validateCell(1,"1.00",instance.getCellValue(0,0),instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_04() {
		// 1-5 Weak Text
		instance.writeCell(0,0,"1asd");
		assertEquals("1asd",instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_05() {
		// 1-6 Weak Boolean
		instance.writeCell(0,0,"True");
		validateCell(1,"TRUE",instance.getCellValue(0,0),instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_06() {
		// 1-7 Weak Text with special character, e.g. space
		instance.writeCell(0,0,"True ");
		assertEquals("True ",instance.getTextualValue(0, 0));
	}
	
//	@Test
//	public void writeCell_07() {
//		// 1-8 NULL
//		instance.writeCell(0,0,"");
//		assertEquals("",instance.getTextualValue(0, 0));
//	}
	
	@Test
	public void writeCell_08() {
		// 1-9 Complex computing
		instance.writeCell(0,0,"=1*2+(3/4+(1+1))-5");
		validateCell(-0.25,"-.25",instance.getCellValue(0,0),instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_10() {
		// 2-1 Write Cell with Cell reference
		writeCell_01();
		instance.writeCell(0, 1, "=A1");
		validateCell(1,"1.00",instance.getCellValue(0,1),instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_11() {
		// 2-2 Write Cell with Cell reference and computation
		writeCell_01();
		instance.writeCell(0, 1, "=A1-2");
		validateCell(-1,"-1.00",instance.getCellValue(0,1),instance.getTextualValue(0,1));
	}
	
	@Test
	public void writeCell_12() {
		// 2-3 Write Cell with Cell reference and computation between Cells
		writeCell_11();
		instance.writeCell(0, 2, "=A1*B1");
		validateCell(-1,"-1.00",instance.getCellValue(0,2),instance.getTextualValue(0,2));
	}
	
	@Test
	public void writeCell_13() {
		// 2-4 Change Cell context to see the change of linked Cell
		writeCell_11();
		instance.writeCell(0, 0, "=2");
		validateCell(0,".00",instance.getCellValue(0,1),instance.getTextualValue(0,1));
	}
	
	@Test
	public void writeCell_21() {
		// 3-1 Check Common::Sum with comma
		writeCell_12();
		instance.writeCell(0, 3, "=SUM(A1,B1,C1)");
		validateCell(-1,"-1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_22() {
		// 3-2 Check Common::Sum with column
		writeCell_12();
		instance.writeCell(0, 3, "=SUM(A1:C1)");
		validateCell(-1,"-1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	@Test
	public void writeCell_23() {
		// 3-3 Check Common::Min with comma
		writeCell_12();
		instance.writeCell(0, 3, "=MIN(A1,B1,C1)");
		validateCell(-1,"-1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_24() {
		// 3-4 Check Common::Min with column
		writeCell_12();
		instance.writeCell(0, 3, "=MIN(A1:C1)");
		validateCell(-1,"-1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	@Test
	public void writeCell_25() {
		// 3-5 Check Common::Max with comma
		writeCell_12();
		instance.writeCell(0, 3, "=MAX(A1,B1,C1)");
		validateCell(1,"1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_26() {
		// 3-6 Check Common::Max with column
		writeCell_12();
		instance.writeCell(0, 3, "=MAX(A1:C1)");
		validateCell(1,"1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_27() {
		// 3-7 Check Common::Average with comma
		writeCell_12();
		instance.writeCell(0, 3, "=AVE(A1,B1,C1)");
		validateCell(-0.3333,"-.33",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_28() {
		// 3-8 Check Common::Average with column
		writeCell_12();
		instance.writeCell(0, 3, "=AVE(A1:C1)");
		validateCell(-0.3333,"-.33",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_29() {
		// 3-9 Check Common::Count with comma
		writeCell_12();
		instance.writeCell(0, 3, "=COUNT(A1,B1,C1)");
		validateCell(3,"3.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_2A() {
		// 3-10 Check Common::Average with column
		writeCell_12();
		instance.writeCell(0, 3, "=COUNT(A1:C1)");
		validateCell(3,"3.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_2B() {
		// 3-11 Check Common with crossing each other
		writeCell_12();
		instance.writeCell(0, 3, "=SUM(COUNT(A1:C1),MIN(A1:C1),MAX(A1:C1),AVE(A1:C1))");
		validateCell(2.6666,"2.67",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_30() {
		// 4-1 Check Math::Comb
		instance.writeCell(0, 0, "4");
		instance.writeCell(0, 1, "6");
		instance.writeCell(0, 2, "=COMB(B1,A1)");
		validateCell(15,"15.00",instance.getCellValue(0,2),instance.getTextualValue(0,2));
	}
	
	@Test
	public void writeCell_31() {
		// 4-1 Check Math::Permutation
		instance.writeCell(0, 0, "4");
		instance.writeCell(0, 1, "6");
		instance.writeCell(0, 2, "=PERM(B1,A1)");
		validateCell(360,"360.00",instance.getCellValue(0,2),instance.getTextualValue(0,2));
	}
	
	@Test
	public void writeCell_32() {
		// 4-1 Check Math::Mean
		writeCell_12();
		instance.writeCell(0, 3, "=MEAN(A1,B1,C1)");
		validateCell(-0.3333,"-.33",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_33() {
		// 4-1 Check Math::SD with comma input
		instance.writeCell(0, 1, "=2");
		instance.writeCell(0, 2, "=4");
		instance.writeCell(0, 3, "=6");
		instance.writeCell(0, 4, "=8");
		instance.writeCell(0, 5, "=SD(A1,B1,C1,D1)");
		validateCell(2.23607,"2.24",instance.getCellValue(0,5),instance.getTextualValue(0,5));
	}
	
	@Test
	public void writeCell_34() {
		// 4-1 Check Math::VAR with comma input
		instance.writeCell(0, 1, "=2");
		instance.writeCell(0, 2, "=4");
		instance.writeCell(0, 3, "=6");
		instance.writeCell(0, 4, "=8");
		instance.writeCell(0, 5, "=VAR(A1,B1,C1,D1)");
		validateCell(5,"5.00",instance.getCellValue(0,5),instance.getTextualValue(0,5));
	}
	
	@Test
	public void writeCell_35() {
		// 4-1 Check Math::VAR with column input
		instance.writeCell(0, 1, "=2");
		instance.writeCell(0, 2, "=4");
		instance.writeCell(0, 3, "=6");
		instance.writeCell(0, 4, "=8");
		instance.writeCell(0, 5, "=VAR(A1:D1)");
		validateCell(5,"5.00",instance.getCellValue(0,5),instance.getTextualValue(0,5));
	}
	
	@Test
	public void writeCell_60() {
		// 6-1 Invalid expression
		instance.writeCell(0, 1, "=w");
		assertEquals("#INVALID#",instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_61() {
		// 6-2 Operate on an error Cell
		writeCell_60();
		instance.writeCell(0, 2, "=B1");
		assertEquals("#CELL#",instance.getTextualValue(0, 2));
	}
	
	@Test
	public void writeCell_62() {
		// 6-3 Infinity Reference
		instance.writeCell(0, 1, "=B1");
		assertEquals("#INF#",instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_63() {
		// 6-4 Solve after infinity Reference
		writeCell_62();
		instance.writeCell(0, 1, "=A1");
		validateCell(0,".00",instance.getCellValue(0, 1), instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_64() {
		// 6-5 Infinity reference between Cells
		instance.writeCell(1, 0, "=B1*B1");
		instance.writeCell(0, 1, "=SUM(A1:A3)");
		assertEquals("#INF#",instance.getTextualValue(1, 0));
		assertEquals("#INF#",instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_65() {
		// 6-6 Solve after reference between Cells
		writeCell_64();
		instance.writeCell(1,0,"2");
		validateCell(2,"2.00",instance.getCellValue(1, 0), instance.getTextualValue(1, 0));
		validateCell(2,"2.00",instance.getCellValue(0, 1), instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_66() {
		// 6-7 recursively reference
		instance.writeCell(0,0,"=A2");
		instance.writeCell(1,0,"=A3");
		instance.writeCell(2,0,"=A1");
		assertEquals("#INF#",instance.getTextualValue(0, 0));
		assertEquals("#INF#",instance.getTextualValue(1, 0));
		assertEquals("#INF#",instance.getTextualValue(2, 0));
	}
}
