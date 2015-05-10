package vn.com.nguacon.kiemthu.integrationtesting.bottomup.driver;

import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.model.Symbol;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolService;
import vn.com.nguacon.kiemthu.integrationtesting.validator.ValidateExeption;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class PriceValidatorDriver implements Validator {
	
	private SymbolService symbolService;
	
	public PriceValidatorDriver(SymbolService symbolService) {
		this.symbolService = symbolService;
	}

	public void validate(Order order) throws ValidateExeption {
		Symbol symbol = symbolService.getSymbol(order.getSymbol());
		if(symbol.getFloorPrice() > order.getPrice() || 
				symbol.getCeilingPrice() < order.getPrice()){
			throw new ValidateExeption(OrderStatus.PRICE_NOT_RANGE.getMessage());
		}
	}

}
