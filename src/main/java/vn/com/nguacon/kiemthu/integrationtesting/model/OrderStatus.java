package vn.com.nguacon.kiemthu.integrationtesting.model;

public enum OrderStatus {
	INVALID_SYMBOL("Mã chứng khoán không đúng"), OK("Đặt lệnh thành công"),
	INVALID_STEP_PRICE("Sai bước giá"), 
	PRICE_NOT_RANGE("Giá không nằm trong biên độ trần sàn"), 
	INVALID_QUANTITY("Số lượng phải lớn hơn 0"), INVALID_STEP_QUANTITY("Số lượng không đúng"), 
	QUANTITY_TOO_BIG("Số lượng không được vượt quá 1000000");
	
	private String message;
	private OrderStatus(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}
