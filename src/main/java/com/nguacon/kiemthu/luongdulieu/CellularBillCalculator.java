package com.nguacon.kiemthu.luongdulieu;

public class CellularBillCalculator {

	public double calculateBill(int usage) {
		double bill = 0;
		
		if (usage > 0) {
			bill = 40;
		}

		if (usage > 100) {
			if (usage <= 200) {
				bill = (usage - 100) * 0.5;
			} else {
				bill += 50 + (usage - 200) * 0.1;
				
				if (bill >= 100) {
					bill = bill * 0.9;
				}
			}
		}
		
		return bill;
	}

}
