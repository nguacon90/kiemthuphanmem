package vn.com.nguacon.kiemthu.integrationtesting.validator;

import vn.com.nguacon.kiemthu.integrationtesting.model.Order;

public interface Validator {
	void validate(Order order) throws ValidateExeption;
}
