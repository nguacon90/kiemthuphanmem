package vn.com.nguacon.kiemthu.integrationtesting.bottomup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import vn.com.nguacon.kiemthu.integrationtesting.bottomup.driver.OrderServiceDriver;
import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolService;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolServiceImpl;
import vn.com.nguacon.kiemthu.integrationtesting.validator.PriceValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.QuantityValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.SymbolValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.ValidateExeption;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class OrderServiceTest {
	private Order order;
	private OrderServiceDriver orderServiceDriver;
	private SymbolService symbolService;
	private Validator symbolValidator;
	private Validator priceValidator;
	private Validator quantityValidator;

	@Before
	public void setUp() {
		symbolService = new SymbolServiceImpl();
		symbolValidator = new SymbolValidator(symbolService);
		priceValidator = new PriceValidator(symbolService);
		quantityValidator = new QuantityValidator();

		orderServiceDriver = new OrderServiceDriver(symbolValidator,
				priceValidator, quantityValidator);
	}

//	@Test
//	public void test_PlaceOrder_WithAAAA_ShouldReceiveInvalidSymbolMessage() {
//		Order order = new Order("AAAA", 0, 0);
//		try {
//			orderServiceDriver.place(order);
//			fail("must throw exception");
//		} catch (ValidateExeption e) {
//			assertEquals(OrderStatus.INVALID_SYMBOL.getMessage(),
//					e.getMessage());
//		}
//	}
//
//	@Test
//	public void kiemtra_giathap_hon_giasan_voima_VND() {
//		order = new Order("VND", 8000, 500);
//		try {
//			orderServiceDriver.place(order);
//			fail("must throw exception");
//		} catch (ValidateExeption e) {
//			assertEquals(OrderStatus.PRICE_NOT_RANGE.getMessage(),
//					e.getMessage());
//		}
//	}
//
//	@Test
//	public void kiemtra_trongbiendotransan_voima_VND() throws ValidateExeption {
//		order = new Order("VND", 11000, 500);
//		orderServiceDriver.place(order);
//		assertTrue(true);
//	}
//
//	@Test
//	public void kiemtra_khoiluongKhongHopLe_voima_VND() {
//		order = new Order("VND", 9200, -100);
//		try {
//			orderServiceDriver.place(order);
//			fail("must throw exception");
//		} catch (ValidateExeption e) {
//			assertEquals(OrderStatus.INVALID_QUANTITY.getMessage(),
//					e.getMessage());
//		}
//	}

	@Test
	public void kiemtra_khoiluong_KhongChanLo_voima_VND() {
		order = new Order("VND", 9200, 235);
		try {
			orderServiceDriver.place(order);
			fail("must throw exception");
		} catch (ValidateExeption e) {
			assertEquals(OrderStatus.INVALID_STEP_QUANTITY.getMessage(),
					e.getMessage());
		}

	}

	@Test
	public void kiemtra_khoiluongHople_voima_VND_1000000() throws ValidateExeption {
		order = new Order("VND", 9200, 1000000);
		orderServiceDriver.place(order);
		assertTrue(true);
	}
}
