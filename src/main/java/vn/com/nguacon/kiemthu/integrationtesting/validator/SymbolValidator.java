package vn.com.nguacon.kiemthu.integrationtesting.validator;

import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolService;

public class SymbolValidator implements Validator{

	private SymbolService symbolService;
	
	public SymbolValidator(SymbolService symbolService) {
		this.symbolService = symbolService;
	}
	
	public void validate(Order order) throws ValidateExeption {
		if(!symbolService.isBelongToHNX(order.getSymbol())){
			throw new ValidateExeption(OrderStatus.INVALID_SYMBOL.getMessage());
		}
	}

}
