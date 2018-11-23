package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cs2115Handler.*;
import fxcelException.InvalidExpressionException;

public class ConvertHandlerTest {

	private BinaryHandler binary;
	private OctalHandler octal;
	private DecimalHandler decimal;
	private HexadecimalHandler hexadecimal;
	private ASCIIHandler ascii;
	private GreyCodeHandler greyCode;
	
	@Before
	public void setup() {
		binary= new BinaryHandler();
		decimal = new DecimalHandler();
		hexadecimal= new HexadecimalHandler();
		octal= new OctalHandler();
		ascii= new ASCIIHandler();
		greyCode = new GreyCodeHandler();
	}
	
	/*
	 * BinaryHandler Test Cases
	 */
	
	@Test
	public void testBinaryDoubleReturn_01() {
		double res = binary.handleForDoubleReturn("2+3");
		assertEquals(5, res, 0.0001);
	}
	
	@Test
	public void testBinaryStringReturn_01() {
		String res = binary.handleForStringReturn("10");
		assertEquals("0b1010", res);
	}
	
	@Test
	public void testBinaryStringReturn_02() {
		String res = binary.handleForStringReturn("-6451.1");
		assertEquals("-0b1100100110011.00011", res);
	}
	
	/*
	 * OctalHandlerTest
	 */
	
	@Test
	public void testOctalDoubleReturn_01() {
		double res = octal.handleForDoubleReturn("1+1");
		assertEquals(2, res, 0.0001);
	}
	
	@Test
	public void testOctalStringReturn_01() {
		String res = octal.handleForStringReturn("17");
		assertEquals("0o21", res);
	}
	
	@Test
	public void testOctalStringReturn_02() {
		String res = octal.handleForStringReturn("-19345");
		assertEquals("-0o45621", res);
	}
	
	/*
	 * DecimalHandler Test Cases
	 */
	
	@Test
	public void testDecimalDoubleReturn_01() {
		double res = decimal.handleForDoubleReturn("12+23");
		assertEquals(35, res, 0.0001);
	}
	
	@Test
	public void testDecimalStringReturn_01() {
		String res = decimal.handleForStringReturn("35");
		assertEquals("0d35", res);
	}
	
	@Test
	public void testDecimalStringReturn_02() {
		String res = decimal.handleForStringReturn("-98734");
		assertEquals("-0d98734", res);
	}
	
	/*
	 * HexadecimalHandler Test Cases
	 */
	
	@Test
	public void testHexadecimalDoubleReturn_01() {
		double res = hexadecimal.handleForDoubleReturn("1+1");
		assertEquals(2, res, 0.0001);
	}
	
	@Test
	public void testHexadecimalStringReturn_01() {
		String res = hexadecimal.handleForStringReturn("17");
		assertEquals("0x11", res);
	}
	
	@Test
	public void testHexadecimalStringReturn_02() {
		String res = hexadecimal.handleForStringReturn("-8761");
		assertEquals("-0x2239", res);
	}
	
	/*
	 * ASCIIHandler Test Cases
	 */
	
	@Test (expected = InvalidExpressionException.class)
	public void testASCIIDoubleReturn_01() {
		ascii.handleForDoubleReturn("A但adaf");
	}

	@Test
	public void testASCIIDoubleReturn_02() {
		assertEquals(0,ascii.handleForDoubleReturn("ABCED"),1);
	}
	
	@Test
	public void testASCIIStringReturn_01() {
		String res = ascii.handleForStringReturn("A");
		assertEquals("65 ", res);
	}
	
	@Test
	public void testASCIIStringReturn_02() {
		String res = ascii.handleForStringReturn("=ASCII(A)");
		assertEquals("65 ", res);
	}
	
	@Test
	public void testASCIIStringReturn_03() {
		String res = ascii.handleForStringReturn("KJH*&Ja-c");
		assertEquals("75 74 72 42 38 74 97 45 99 ", res);
	}
	
	/*
	 * GreyCodeHandler Test Cases
	 */
	
	@Test
	public void testGreyCodeDoubleReturn_01() {
		double res = greyCode.handleForDoubleReturn("1000");
		assertEquals(1000, res, 0.0001);
	}
	
	@Test
	public void testGreyCodeDoubleReturn_02() {
		assertEquals(1000, greyCode.handleForDoubleReturn("=GREY(1000)"), 0.0001);
	}
	
	@Test
	public void testGreyCodeDoubleReturn_03() {
		assertEquals(1000001, greyCode.handleForDoubleReturn("1000001"), 0.0001);
	}
	
	@Test
	public void testGreyCodeStringReturn_01() {
		String res = greyCode.handleForStringReturn("10");
		assertEquals("0b1111", res);
	}
	
	@Test
	public void testGreyCodeStringReturn_02() {
		String res = greyCode.handleForStringReturn("1000000");
		assertEquals("0b10001110001101100000", res);
	}
	
	@Test
	public void testGreyCodeStringReturn_03() {
		String res = greyCode.handleForStringReturn("=GREY(99)");
		assertEquals("0b1010010", res);
	}
}
