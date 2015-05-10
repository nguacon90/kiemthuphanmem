package vn.com.nguacon.kiemthu.integrationtesting.model;


public class OrderResult {
	private String message;
	private String code;

	public boolean isSuccessfull(){
		return OrderStatus.OK.getMessage().equalsIgnoreCase(message);
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
