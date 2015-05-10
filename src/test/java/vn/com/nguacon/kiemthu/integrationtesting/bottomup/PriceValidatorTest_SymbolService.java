package vn.com.nguacon.kiemthu.integrationtesting.bottomup;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vn.com.nguacon.kiemthu.integrationtesting.bottomup.driver.PriceValidatorDriver;
import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolService;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolServiceImpl;
import vn.com.nguacon.kiemthu.integrationtesting.validator.ValidateExeption;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class PriceValidatorTest_SymbolService {
	private SymbolService symbolService;
	private Validator priceValidatorDriver;
	
	@Before
	public void setUp() {
		symbolService = new SymbolServiceImpl();
		priceValidatorDriver = new PriceValidatorDriver(symbolService);
	}
	
	@Test
	public void testPriceSmallerFloorPrice() {
		Order order = new Order("VND", 9000, 100);
		try {
			priceValidatorDriver.validate(order);
			fail("must throw exception");
		} catch (ValidateExeption e) {
			assertEquals(OrderStatus.PRICE_NOT_RANGE.getMessage(), e.getMessage());
		}
	}
}
