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
import vn.com.nguacon.kiemthu.integrationtesting.topdown.stubs.QuantityValidatorStub;
import vn.com.nguacon.kiemthu.integrationtesting.topdown.stubs.SymbolServiceStub;
import vn.com.nguacon.kiemthu.integrationtesting.validator.PriceValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.SymbolValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class OrderServiceTest2_PriceOrderValidator {
	private OrderService orderService;
	private SymbolService symbolService;
	private Validator symbolValidator;
	private Validator priceValidator;
	private Validator quantityValidator;
	private Order order;
	
	@Before
	public void setUp() {
		symbolService = new SymbolServiceStub();
		symbolValidator = new SymbolValidator(symbolService);
		priceValidator = new PriceValidator(symbolService);
		quantityValidator = new QuantityValidatorStub();
		orderService = new OrderServiceImpl(symbolValidator, priceValidator,
				quantityValidator);
	}
	
	//--START test case sau khi kiem tra tich hop module kiem tra ma CK
	@Test
	public void test_PlaceOrder_WithAAAA_ShouldReceiveInvalidSymbolMessage(){
		Order order = new Order("AAAA", 0, 0);
		OrderResult orderResult = orderService.place(order);
		assertEquals(OrderStatus.INVALID_SYMBOL.getMessage(), orderResult.getMessage());
	}

	//Test case bi anh huong boi tich hop module kiem tra gia 
//	@Test
//	public void test_PlaceOrder_WithVND_ShouldBeSuccess(){
//		Order order = new Order("VND", 0, 0);
//		OrderResult orderResult = orderService.place(order);
//		assertEquals(OrderStatus.OK.getMessage(), orderResult.getMessage());
//	}
	//--END test case sau khi kiem tra tich hop module kiem tra ma CK
	
	@Test
	public void kiemtra_giathap_hon_giasan_voima_VND() {
		order = new Order("VND", 10999, 500);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.PRICE_NOT_RANGE.getMessage(),
				orderResult.getMessage());
	}

	@Test
	public void kiemtra_trongbiendotransan_voima_VND() {
		order = new Order("VND", 12000, 500);
		OrderResult orderResult = orderService.place(order);
		
		assertEquals(OrderStatus.OK.getMessage(),
				orderResult.getMessage());
	}
}
