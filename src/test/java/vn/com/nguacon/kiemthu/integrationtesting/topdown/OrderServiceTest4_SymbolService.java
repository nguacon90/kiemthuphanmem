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
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolServiceImpl;
import vn.com.nguacon.kiemthu.integrationtesting.validator.PriceValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.QuantityValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.SymbolValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class OrderServiceTest4_SymbolService {
	private OrderService orderService;
	private SymbolService symbolService;
	private Validator symbolValidator;
	private Validator priceValidator;
	private Validator quantityValidator;
	private Order order;

	@Before
	public void setUp() {
		symbolService = new SymbolServiceImpl();
		symbolValidator = new SymbolValidator(symbolService);
		priceValidator = new PriceValidator(symbolService);
		quantityValidator = new QuantityValidator();
		orderService = new OrderServiceImpl(symbolValidator, priceValidator,
				quantityValidator);
	}

	@Test
	public void test_PlaceOrder_WithAAAA_ShouldReceiveInvalidSymbolMessage() {
		Order order = new Order("AAAA", 0, 0);
		OrderResult orderResult = orderService.place(order);
		assertEquals(OrderStatus.INVALID_SYMBOL.getMessage(),
				orderResult.getMessage());
	}

	//Test case bi anh huong khi tich hop voi module OrderService
	//Sai buoc gia
	@Test
	public void kiemtra_giathap_hon_giasan_voima_VND() {
		order = new Order("VND", 10999, 500);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.PRICE_NOT_RANGE.getMessage(),
				orderResult.getMessage());
	}
	
	@Test
	public void kiemtra_giathap_hon_giasan_voima_VND_updated() {
		order = new Order("VND", 9000, 500);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.PRICE_NOT_RANGE.getMessage(),
				orderResult.getMessage());
	}

	//Test case bi anh huong khi tich hop voi module OrderService
	//Gia nam ngoai bien do tran san
	@Test
	public void kiemtra_trongbiendotransan_voima_VND() {
		order = new Order("VND", 12000, 500);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.OK.getMessage(), orderResult.getMessage());
	}
	
	@Test
	public void kiemtra_trongbiendotransan_voima_VND_updated() {
		order = new Order("VND", 9200, 500);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.OK.getMessage(), orderResult.getMessage());
	}

	//Test case bi anh huong khi tich hop voi module OrderService
	//Gia nam ngoai bien do tran san
	@Test
	public void kiemtra_khoiluongKhongHopLe_voima_VND() {
		order = new Order("VND", 12000, -100);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.INVALID_QUANTITY.getMessage(),
				orderResult.getMessage());
	}
	
	@Test
	public void kiemtra_khoiluongKhongHopLe_voima_VND_updated() {
		order = new Order("VND", 9200, -100);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.INVALID_QUANTITY.getMessage(),
				orderResult.getMessage());
	}

	//Test case bi anh huong khi tich hop voi module OrderService
	//Gia khong nam trong bien do tran san
	@Test
	public void kiemtra_khoiluong_KhongChanLo_voima_VND() {
		order = new Order("VND", 12000, 235);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.INVALID_STEP_QUANTITY.getMessage(),
				orderResult.getMessage());
	}
	
	@Test
	public void kiemtra_khoiluong_KhongChanLo_voima_VND_updated() {
		order = new Order("VND", 9200, 235);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.INVALID_STEP_QUANTITY.getMessage(),
				orderResult.getMessage());
	}
	
	//Test case bi anh huong khi tich hop voi module OrderService
	//Gia khong nam trong bien do tran san
	@Test
	public void kiemtra_khoiluongHople_voima_VND_1000000() {
		order = new Order("VND", 12000, 1000000);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.OK.getMessage(), orderResult.getMessage());
	}
	
	@Test
	public void kiemtra_khoiluongHople_voima_VND_1000000_updated() {
		order = new Order("VND", 9200, 1000000);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.OK.getMessage(), orderResult.getMessage());
	}
}
