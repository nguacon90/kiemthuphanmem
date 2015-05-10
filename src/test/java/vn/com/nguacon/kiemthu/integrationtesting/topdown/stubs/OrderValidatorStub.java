package vn.com.nguacon.kiemthu.integrationtesting.topdown.stubs;

import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.validator.ValidateExeption;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class OrderValidatorStub implements Validator {

	public void validate(Order order) throws ValidateExeption {
		if(!"VND".equalsIgnoreCase(order.getSymbol())){
			throw new ValidateExeption(OrderStatus.INVALID_SYMBOL.getMessage());
		}
	}

}
