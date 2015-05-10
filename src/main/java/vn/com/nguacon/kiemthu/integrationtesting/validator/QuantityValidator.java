package vn.com.nguacon.kiemthu.integrationtesting.validator;

import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;

public class QuantityValidator implements Validator {

	public void validate(Order order) throws ValidateExeption {
		int quantity = order.getQuantity();
		if (quantity <= 0) {
			throw new ValidateExeption(
					OrderStatus.INVALID_QUANTITY.getMessage());
		}

		if (quantity > 1000000) {
			throw new ValidateExeption(
					OrderStatus.QUANTITY_TOO_BIG.getMessage());
		}

		if (quantity % 100 != 0) {
			throw new ValidateExeption(
					OrderStatus.INVALID_STEP_QUANTITY.getMessage());
		}
	}

}
