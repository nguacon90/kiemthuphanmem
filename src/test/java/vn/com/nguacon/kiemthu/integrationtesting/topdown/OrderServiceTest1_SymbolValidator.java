package vn.com.nguacon.kiemthu.integrationtesting.topdown;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import vn.com.nguacon.kiemthu.integrationtesting.OrderService;
import vn.com.nguacon.kiemthu.integrationtesting.OrderServiceImpl;
import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderResult;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolService;
import vn.com.nguacon.kiemthu.integrationtesting.topdown.stubs.PriceValidatorStub;
import vn.com.nguacon.kiemthu.integrationtesting.topdown.stubs.QuantityValidatorStub;
import vn.com.nguacon.kiemthu.integrationtesting.topdown.stubs.SymbolServiceStub;
import vn.com.nguacon.kiemthu.integrationtesting.validator.SymbolValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class OrderServiceTest1_SymbolValidator {
	private OrderService orderService;
	private SymbolService symbolService;
	private Validator symbolValidator;
	private Validator priceValidator;
	private Validator quantityValidator;
	
	@Before
	public void setUp() {
		symbolService = new SymbolServiceStub();
		symbolValidator = new SymbolValidator(symbolService);
		priceValidator = new PriceValidatorStub();
		quantityValidator = new QuantityValidatorStub();
		orderService = new OrderServiceImpl(symbolValidator, priceValidator,
				quantityValidator);
	}
	
	@Test
	public void test_PlaceOrder_WithAAAA_ShouldReceiveInvalidSymbolMessage(){
		Order order = new Order("AAAA", 0, 0);
		OrderResult orderResult = orderService.place(order);
		assertEquals(OrderStatus.INVALID_SYMBOL.getMessage(), orderResult.getMessage());
	}
	
	@Test
	public void test_PlaceOrder_WithVND_ShouldBeSuccess(){
		Order order = new Order("VND", 0, 0);
		OrderResult orderResult = orderService.place(order);
		assertEquals(OrderStatus.OK.getMessage(), orderResult.getMessage());
	}
}
