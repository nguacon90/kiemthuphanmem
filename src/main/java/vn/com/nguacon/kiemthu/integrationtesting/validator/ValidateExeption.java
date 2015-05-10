package vn.com.nguacon.kiemthu.integrationtesting.validator;

@SuppressWarnings("serial")
public class ValidateExeption extends Exception {
	private String message;
	
	public ValidateExeption(String message){
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
