package vn.com.nguacon;

public class OrderValidatorImpl implements OrderValidator {

	private SymbolService symbolService;
	public OrderValidatorImpl(SymbolService symbolService) {
		this.symbolService = symbolService;
	}
	
	public void validate(Order order) throws ValidateExeption {
		if(!symbolService.isBelongToHNX(order.getSymbol())){
			throw new ValidateExeption(OrderStatus.INVALID_SYMBOL.getMessage());
		}

		Symbol symbol = symbolService.getSymbol(order.getSymbol());
		double price = order.getPrice();
		
		if(price <= symbol.getFloorPrice() || price > symbol.getCeilingPrice()) {
			throw new ValidateExeption(OrderStatus.PRICE_NOT_RANGE.getMessage());
		}
		
		if(price % 100 != 0){
			throw new ValidateExeption(OrderStatus.INVALID_STEP_PRICE.getMessage());
		}
		
		int quantity = order.getQuantity();
		if(quantity <= 0){
			throw new ValidateExeption(OrderStatus.INVALID_QUANTITY.getMessage());
		}
		
		if(quantity > 1000000){
			throw new ValidateExeption(OrderStatus.QUANTITY_TOO_BIG.getMessage());
		}
		
		if(quantity % 100 != 0) {
			throw new ValidateExeption(OrderStatus.INVALID_STEP_QUANTITY.getMessage());
		}
	}

}
