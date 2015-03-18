package vn.com.nguacon;

public interface OrderValidator {
	void validate(Order order) throws ValidateExeption;
}
