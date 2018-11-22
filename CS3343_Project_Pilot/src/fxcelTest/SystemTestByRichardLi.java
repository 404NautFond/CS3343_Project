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
	
	public void writeCell(int row, int col, String cont) {
		instance.writeCell(row, col, cont);
	}
	
	@Test
	public void writeCell_00() {
		// 1-1 Strong Text
		writeCell(0,0,":1");
		assertEquals("1",instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_01() {
		// 1-2 Expression without functions and computations
		writeCell(0,0,"=1");
		validateCell(1,"1.00",instance.getCellValue(0,0),instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_02() {
		// 1-3 Expression without function call but with computations
		writeCell(0,0,"=2+3");
		validateCell(5,"5.00",instance.getCellValue(0,0),instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_03() {
		// 1-4 Weak Numeric
		writeCell(0,0,"1");
		validateCell(1,"1.00",instance.getCellValue(0,0),instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_04() {
		// 1-5 Weak Text
		writeCell(0,0,"1asd");
		assertEquals("1asd",instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_05() {
		// 1-6 Weak Boolean
		writeCell(0,0,"True");
		validateCell(1,"TRUE",instance.getCellValue(0,0),instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_06() {
		// 1-7 Weak Text with special character, e.g. space
		writeCell(0,0,"True ");
		assertEquals("True ",instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_07() {
		// 1-8 NULL
		writeCell(0,0,"");
		assertEquals("",instance.getTextualValue(0, 0));
	}
	
	@Test
	public void writeCell_08() {
		// 1-9 Complex computing
		writeCell(0,0,"=1*2+(3/4+(1+1))-5");
		validateCell(-0.25,"-.25",instance.getCellValue(0,0),instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_09() {
		// 1-10* False
		writeCell(0,0,"faLse");
		validateCell(0.00,"FALSE",instance.getCellValue(0,0),instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_10() {
		// 2-1 Write Cell with Cell reference
		writeCell_01();
		writeCell(0, 1, "=A1");
		validateCell(1,"1.00",instance.getCellValue(0,1),instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_11() {
		// 2-2 Write Cell with Cell reference and computation
		writeCell_01();
		writeCell(0, 1, "=A1-2");
		validateCell(-1,"-1.00",instance.getCellValue(0,1),instance.getTextualValue(0,1));
	}
	
	@Test
	public void writeCell_12() {
		// 2-3 Write Cell with Cell reference and computation between Cells
		writeCell_11();
		writeCell(0, 2, "=A1*B1");
		validateCell(-1,"-1.00",instance.getCellValue(0,2),instance.getTextualValue(0,2));
	}
	
	@Test
	public void writeCell_13() {
		// 2-4 Change Cell context to see the change of linked Cell
		writeCell_11();
		writeCell(0, 0, "=2");
		validateCell(0,".00",instance.getCellValue(0,1),instance.getTextualValue(0,1));
	}
	
	@Test
	public void writeCell_21() {
		// 3-1 Check Common::Sum with comma
		writeCell_12();
		writeCell(0, 3, "=SUM(A1,B1,C1)");
		validateCell(-1,"-1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_22() {
		// 3-2 Check Common::Sum with column
		writeCell_12();
		writeCell(0, 3, "=SUM(A1:C1)");
		validateCell(-1,"-1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	@Test
	public void writeCell_23() {
		// 3-3 Check Common::Min with comma
		writeCell_12();
		writeCell(0, 3, "=MIN(A1,B1,C1)");
		validateCell(-1,"-1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_24() {
		// 3-4 Check Common::Min with column
		writeCell_12();
		writeCell(0, 3, "=MIN(A1:C1)");
		validateCell(-1,"-1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	@Test
	public void writeCell_25() {
		// 3-5 Check Common::Max with comma
		writeCell_12();
		writeCell(0, 3, "=MAX(A1,B1,C1)");
		validateCell(1,"1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_26() {
		// 3-6 Check Common::Max with column
		writeCell_12();
		writeCell(0, 3, "=MAX(A1:C1)");
		validateCell(1,"1.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_27() {
		// 3-7 Check Common::Average with comma
		writeCell_12();
		writeCell(0, 3, "=AVE(A1,B1,C1)");
		validateCell(-0.3333,"-.33",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_28() {
		// 3-8 Check Common::Average with column
		writeCell_12();
		writeCell(0, 3, "=AVE(A1:C1)");
		validateCell(-0.3333,"-.33",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_29() {
		// 3-9 Check Common::Count with comma
		writeCell_12();
		writeCell(0, 3, "=COUNT(A1,B1,C1)");
		validateCell(3,"3.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_2A() {
		// 3-10 Check Common::Average with column
		writeCell_12();
		writeCell(0, 3, "=COUNT(A1:C1)");
		validateCell(3,"3.00",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_2B() {
		// 3-11 Check Common with crossing each other
		writeCell_12();
		writeCell(0, 3, "=SUM(COUNT(A1:C1),MIN(A1:C1),MAX(A1:C1),AVE(A1:C1))");
		validateCell(2.6666,"2.67",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_2C() {
		// 3-12* Check Illegal
		writeCell(0, 0, "=SUM(1)");
		assertEquals("#INVALID#",instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_30() {
		// 4-1 Check Math::Comb
		writeCell(0, 0, "4");
		writeCell(0, 1, "6");
		writeCell(0, 2, "=COMB(B1,A1)");
		validateCell(15,"15.00",instance.getCellValue(0,2),instance.getTextualValue(0,2));
	}
	
	@Test
	public void writeCell_31() {
		// 4-2 Check Math::Permutation
		writeCell(0, 0, "4");
		writeCell(0, 1, "6");
		writeCell(0, 2, "=PERM(B1,A1)");
		validateCell(360,"360.00",instance.getCellValue(0,2),instance.getTextualValue(0,2));
	}
	
	@Test
	public void writeCell_32() {
		// 4-3 Check Math::Mean
		writeCell_12();
		writeCell(0, 3, "=MEAN(A1,B1,C1)");
		validateCell(-0.3333,"-.33",instance.getCellValue(0,3),instance.getTextualValue(0,3));
	}
	
	@Test
	public void writeCell_33() {
		// 4-4 Check Math::SD with comma input
		writeCell(0, 1, "=2");
		writeCell(0, 2, "=4");
		writeCell(0, 3, "=6");
		writeCell(0, 4, "=8");
		writeCell(0, 5, "=SD(A1,B1,C1,D1)");
		validateCell(2.23607,"2.24",instance.getCellValue(0,5),instance.getTextualValue(0,5));
	}
	
	@Test
	public void writeCell_34() {
		// 4-5 Check Math::VAR with comma input
		writeCell(0, 1, "=2");
		writeCell(0, 2, "=4");
		writeCell(0, 3, "=6");
		writeCell(0, 4, "=8");
		writeCell(0, 5, "=VAR(A1,B1,C1,D1)");
		validateCell(5,"5.00",instance.getCellValue(0,5),instance.getTextualValue(0,5));
	}
	
	@Test
	public void writeCell_35() {
		// 4-6 Check Math::VAR with column input
		writeCell(0, 1, "=2");
		writeCell(0, 2, "=4");
		writeCell(0, 3, "=6");
		writeCell(0, 4, "=8");
		writeCell(0, 5, "=VAR(A1:D1)");
		validateCell(5,"5.00",instance.getCellValue(0,5),instance.getTextualValue(0,5));
	}
	
	@Test
	public void writeCell_36() {
		// 4-7* Illegal Combination or Permutation
		writeCell(0,0,"=COMB(1,3)");
		writeCell(0,1,"=PERM(1,3)");
		assertEquals("#INVALID#", instance.getTextualValue("A1"));
		assertEquals("#INVALID#", instance.getTextualValue("B1"));
	}
	
	@Test
	public void writeCell_37() {
		// 4-8* Illegal Variance
		writeCell(0,0,"=VAR(15)");
		assertEquals("#INVALID#", instance.getTextualValue("A1"));
	}
	
	@Test
	public void writeCell_40() {
		// 5-1 Check Converter::BIN
		writeCell(0, 0, "=BIN(16)");
		validateCell(16.00, "0b10000",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_41() {
		// 5-2 Check Converter::OCT
		writeCell(0, 0, "=OCT(16)");
		validateCell(16.00, "0o20",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_42() {
		// 5-3 Check Converter::HEX
		writeCell(0, 0, "=HEX(16)");
		validateCell(16.00, "0x10",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_43() {
		// 5-4 Check Converter::DEC
		writeCell(0, 0, "=DEC(16)");
		validateCell(16.00, "0d16",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_44() {
		// 5-5 Check Converter::DEC after changing
		writeCell_40();
		writeCell(0, 0, "=DEC(16)");
		validateCell(16.00, "0d16",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_45() {
		// 5-6 Check Converter::ASCII
		writeCell(0, 0, "=ASCII(ABCDE)");
		validateCell(0, "65 66 67 68 69 ",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_46() {
		// 5-7 Check Converter::Grey
		writeCell(0, 0, "=BIN(15)");
		writeCell(0, 1, "=GREY(A1)");
		validateCell(15, "0b1000",instance.getCellValue(0,1), instance.getTextualValue(0,1));
	}
	
	@Test
	public void writeCell_47() {
		// 5-8 Check Converter::Negative
		writeCell(0, 0, "=BIN(-15)");
		validateCell(-15, "-0b1111",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_48() {
		// 5-9 Check Converter::Decimal
		writeCell(0, 0, "=BIN(-2.5)");
		validateCell(-2.5, "-0b10.1",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_49() {
		// 5-10* Check Converter::Decimal
		writeCell(0, 0, "=ASCII(中文)");
		assertEquals("#INVALID#", instance.getTextualValue("A1"));
	}
	
	@Test
	public void writeCell_50() {
		// 6-1 Check Logic::AND
		writeCell(0, 0, "=AND(1,1)");
		validateCell(1, "TRUE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_51() {
		// 6-2 Check Logic::AND
		writeCell(0, 0, "=AND(0,1)");
		validateCell(0, "FALSE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_52() {
		// 6-3 Check Logic::AND
		writeCell(0, 0, "=AND(0,FALSE)");
		validateCell(0, "FALSE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_53() {
		// 6-4 Check Logic::OR
		writeCell(0, 0, "=OR(0,FALSE)");
		validateCell(0, "FALSE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_54() {
		// 6-5 Check Logic::OR
		writeCell(0, 0, "=OR(TRUE,0)");
		validateCell(1,"TRUE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_55() {
		// 6-6 Check Logic::NOT
		writeCell(0, 0, "=NOT(0)");
		validateCell(1,"TRUE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_56() {
		// 6-7 Check Logic::NOT
		writeCell(0, 0, "=NOT(1)");
		validateCell(0,"FALSE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_57() {
		// 6-7 Check Logic::NAND
		writeCell(0, 0, "=NAND(1,1)");
		validateCell(0,"FALSE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_58() {
		// 6-7 Check Logic::NAND
		writeCell(0, 0, "=NAND(0,1)");
		validateCell(1,"TRUE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_59() {
		// 6-7 Check Logic::NOR
		writeCell(0, 0, "=NOR(0,1)");
		validateCell(0,"FALSE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_5A() {
		// 6-7 Check Logic::NOR
		writeCell(0, 0, "=NOR(0,FALSE)");
		validateCell(1,"TRUE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_5B() {
		// 6-7 Check Logic::XNOR
		writeCell(0, 0, "=XNOR(1,FALSE)");
		validateCell(0,"FALSE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_5D() {
		// 6-9* Check Logic::XOR
		writeCell(0, 0, "=XOR(1,FALSE)");
		validateCell(1,"TRUE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
	}
	
	@Test
	public void writeCell_5C() {
		// 6-8 Check Logic::Cross
		writeCell(0, 0,"=OR(AND(1,1),AND(1,1))");
		writeCell(0, 1, "=XNOR(AND(TRUE,1),A1)");
		validateCell(1,"TRUE",instance.getCellValue(0,0), instance.getTextualValue(0,0));
		validateCell(1,"TRUE",instance.getCellValue(0,1), instance.getTextualValue(0,1));
	}
	
	@Test
	public void writeCell_60() {
		// 7-1 Invalid expression
		writeCell(0, 1, "=w");
		assertEquals("#INVALID#",instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_61() {
		// 7-2 Operate on an error Cell
		writeCell_60();
		writeCell(0, 2, "=B1");
		assertEquals("#CELL#",instance.getTextualValue(0, 2));
	}
	
	@Test
	public void writeCell_62() {
		// 7-3 Infinity Reference
		writeCell(0, 1, "=B1");
		assertEquals("#INF#",instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_63() {
		// 7-4 Solve after infinity Reference
		writeCell_62();
		writeCell(0, 1, "=A1");
		validateCell(0,".00",instance.getCellValue(0, 1), instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_64() {
		// 7-5 Infinity reference between Cells
		writeCell(1, 0, "=B1*B1");
		writeCell(0, 1, "=SUM(A1:A3)");
		assertEquals("#INF#",instance.getTextualValue(1, 0));
		assertEquals("#INF#",instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_65() {
		// 7-6 Solve after reference between Cells
		writeCell_64();
		writeCell(1,0,"2");
		validateCell(2,"2.00",instance.getCellValue(1, 0), instance.getTextualValue(1, 0));
		validateCell(2,"2.00",instance.getCellValue(0, 1), instance.getTextualValue(0, 1));
	}
	
	@Test
	public void writeCell_66() {
		// 7-7 recursively reference
		writeCell(0,0,"=A2");
		writeCell(1,0,"=A3");
		writeCell(2,0,"=A1");
		assertEquals("#INF#",instance.getTextualValue(0, 0));
		assertEquals("#INF#",instance.getTextualValue(1, 0));
		assertEquals("#INF#",instance.getTextualValue(2, 0));
	}
}
