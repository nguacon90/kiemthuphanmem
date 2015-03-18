package vn.com.nguacon;

public class OrderServiceImpl implements OrderService {
	
	
	private OrderValidator orderValidator;
	
	public OrderServiceImpl(OrderValidator orderValidator){
		this.orderValidator = orderValidator;
	}
	
	public OrderResult place(Order order) {
		OrderResult orderResult = new OrderResult();
		try {
			orderValidator.validate(order);
			orderResult.setMessage(OrderStatus.OK.getMessage());
			
		} catch (ValidateExeption e){
			orderResult.setMessage(e.getMessage());
		}
		
		return orderResult;
	}

}
