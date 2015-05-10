package vn.com.nguacon.kiemthu.integrationtesting;

import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderResult;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.validator.ValidateExeption;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class OrderServiceImpl implements OrderService {
	private Validator symbolValidator;
	private Validator priceValidator;
	private Validator quantityValidator;

	public OrderServiceImpl(Validator symbolValidator,
			Validator priceValidator, Validator quantityValidator) {
		this.symbolValidator = symbolValidator;
		this.priceValidator = priceValidator;
		this.quantityValidator = quantityValidator;
	}

	public OrderResult place(Order order) {
		OrderResult orderResult = new OrderResult();
		try {
			symbolValidator.validate(order);
			priceValidator.validate(order);
			quantityValidator.validate(order);
			orderResult.setMessage(OrderStatus.OK.getMessage());

		} catch (ValidateExeption e) {
			orderResult.setMessage(e.getMessage());
		}

		return orderResult;
	}

}
