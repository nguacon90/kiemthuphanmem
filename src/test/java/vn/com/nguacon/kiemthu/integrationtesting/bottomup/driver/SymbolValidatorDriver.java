package vn.com.nguacon.kiemthu.integrationtesting.bottomup.driver;

import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolService;
import vn.com.nguacon.kiemthu.integrationtesting.validator.ValidateExeption;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class SymbolValidatorDriver implements Validator {

	private SymbolService symbolService;
	
	public SymbolValidatorDriver(SymbolService symbolService) {
		this.symbolService = symbolService;
	}

	public void validate(Order order) throws ValidateExeption {
		if(!symbolService.isBelongToHNX(order.getSymbol())) {
			throw new ValidateExeption(OrderStatus.INVALID_SYMBOL.getMessage());
		}
	}
}
