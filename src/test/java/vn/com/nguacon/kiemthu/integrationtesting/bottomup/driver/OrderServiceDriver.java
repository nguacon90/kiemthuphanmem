package vn.com.nguacon.kiemthu.integrationtesting.bottomup.driver;

import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.validator.ValidateExeption;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class OrderServiceDriver {

	private Validator symValidator;
	private Validator priceValidator;
	private Validator quantityValidator;

	public OrderServiceDriver(Validator symbolValidator,
			Validator priceValidator, Validator quantityValidator) {
		this.priceValidator = priceValidator;
		this.quantityValidator = quantityValidator;
		this.symValidator = symbolValidator;
	}

	public void place(Order order) throws ValidateExeption {
		symValidator.validate(order);
		priceValidator.validate(order);
		quantityValidator.validate(order);
	}

}
