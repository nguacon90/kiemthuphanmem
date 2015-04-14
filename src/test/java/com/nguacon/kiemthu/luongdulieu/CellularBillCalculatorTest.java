package com.nguacon.kiemthu.luongdulieu;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CellularBillCalculatorTest {
	
	private CellularBillCalculator calculator;
	
	@Before
	public void setUp() {
		calculator = new CellularBillCalculator();
	}
	
	@Test
	public void test_usage_is_0() {
		double actual = calculator.calculateBill(0);
		assertEquals(0, actual, 0);
	}
	
	@Test
	public void test_usage_is_350() {
		double actual = calculator.calculateBill(350);
		assertEquals(94.5, actual, 0);
	}
	
	@Test
	public void test_usage_is_156() {
		double actual = calculator.calculateBill(156);
		assertEquals(68, actual, 0);
	}
}
