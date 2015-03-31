package com.nguacon.kiemthu.luongdk;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SolutionTest {
	private Solution solution;
	
	@Before
	public void setUp(){
		solution = new Solution();
	}
	
	@Test
	public void test_case_1(){
		int[] A = new int[]{};
		int actual = solution.findEquilibriumIndex(A);
		assertEquals(-1, actual);
	}
	
	@Test
	public void test_case_2(){
		int[] A = new int[]{-1};
		int actual = solution.findEquilibriumIndex(A);
		assertEquals(0, actual);
	}
	@Test
	public void test_case_3(){
		int[] A = new int[]{-1, 3};
		int actual = solution.findEquilibriumIndex(A);
		assertEquals(-1, actual);
	}
	
	@Test
	public void test_case_loop_8times(){
		int[] A = new int[]{-1, 3, -4, 5, 1, -6, 2, 1};
		int actual = solution.findEquilibriumIndex(A);
		assertEquals(1, actual);
	}
	
	@Test
	public void test_case_loop_99999times(){
		int A[] = new int[99999];
		for(int i=0; i<99999; i++){
			if(i >= 50000) {
				A[i] = i - 50000 + 1;
				continue;
			}
			
			A[i] = i - 500000 + 1;
		}
		int actual = solution.findEquilibriumIndex(A);
		assertEquals(-1, actual);
	}
	
	@Test
	public void test_case_loop_100000times(){
		int A[] = new int[100000];
		for(int i=0; i<99999; i++){
			if(i >= 49999 && i%2 == 0) {
				A[i] = i - 49999;
				continue;
			}
			
			if(i > 49999 && i%2 != 0) {
				A[i] = 49999 - i;
				continue;
			}
			
			if(i%2 != 0) {
				A[i] = 49999 - i;
				continue;
			}
			
			A[i] = i - 49999;
		}
		
		int actual = solution.findEquilibriumIndex(A);
		assertEquals(99999, actual);
	}
	
}
