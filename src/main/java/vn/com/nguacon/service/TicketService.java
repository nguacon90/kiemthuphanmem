package vn.com.nguacon.service;


/*
 * Input: 
 * 		age: integer [5, 100]: age<=15 || age >= 65 => giảm 50%
 * 		loai khach hang: VIP, NORMAL  VIP: giảm 10%
 * 
 * Output: 
 * 		age < 5 hoặc age > 100: "invalid age"
 * 		trả về % giảm giá "N%"	
 */
public class TicketService {
	
	public String getPromotion(int age, boolean isVIP){
		int promotion = 0;
		if(age <= 5 || age >= 100) {
			return "invalid age";
		}
		
		if(age <=15 || age >= 65) {
			promotion += 50;
		}
		
		if(isVIP) {
			promotion += 10;
		}
			
		return promotion + "%";
	}
	
}
