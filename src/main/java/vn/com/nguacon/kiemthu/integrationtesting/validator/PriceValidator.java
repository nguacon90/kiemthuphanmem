package vn.com.nguacon.kiemthu.integrationtesting.validator;

import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.model.Symbol;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolService;

public class PriceValidator implements Validator{
	private SymbolService symbolService;
	
	public PriceValidator(SymbolService symbolService){
		this.symbolService = symbolService;
	}
	
	public void validate(Order order) throws ValidateExeption {
		Symbol symbol = symbolService.getSymbol(order.getSymbol());
		double price = order.getPrice();
		
		if(price < symbol.getFloorPrice() || price > symbol.getCeilingPrice()) {
			throw new ValidateExeption(OrderStatus.PRICE_NOT_RANGE.getMessage());
		}
		
		if(price % 100 != 0){
			throw new ValidateExeption(OrderStatus.INVALID_STEP_PRICE.getMessage());
		}
	}

}
