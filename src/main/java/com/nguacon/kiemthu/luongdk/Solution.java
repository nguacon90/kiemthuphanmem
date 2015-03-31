package com.nguacon.kiemthu.luongdk;

class Solution {
	public int findEquilibriumIndex(int[] A) {
		long sum = 0;
		long leftSum = 0;
		for (int i = 0; i < A.length; i++) {
			leftSum += A[i];
		}
		
		for (int i = 0; i < A.length; i++) {
			if (sum == leftSum - A[i])
				return i;
			sum += A[i];
			leftSum -= A[i];
		}
		return -1;
	}
}
