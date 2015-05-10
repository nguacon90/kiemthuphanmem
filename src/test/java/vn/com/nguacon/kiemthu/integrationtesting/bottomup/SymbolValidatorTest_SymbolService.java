package vn.com.nguacon.kiemthu.integrationtesting.bottomup;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vn.com.nguacon.kiemthu.integrationtesting.bottomup.driver.SymbolValidatorDriver;
import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolService;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolServiceImpl;
import vn.com.nguacon.kiemthu.integrationtesting.validator.ValidateExeption;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

public class SymbolValidatorTest_SymbolService {
	
	private SymbolService symbolService;
	private Validator symbolValidatorDriver;
	private Order order;
	
	@Before
	public void setUp() {
		symbolService = new SymbolServiceImpl();
		symbolValidatorDriver = new SymbolValidatorDriver(symbolService);
	}
	
	@Test
	public void testSymbolNotInHnx() {
		order = new Order("AAAA", 0, 0);
		try {
			symbolValidatorDriver.validate(order);
			fail("must throw exception");
		} catch (ValidateExeption e) {
			assertEquals(OrderStatus.INVALID_SYMBOL.getMessage(), e.getMessage());
		}
	}
	
	@Test
	public void testSymbolInHnx() throws ValidateExeption {
		order = new Order("AAA", 0, 0);
		symbolValidatorDriver.validate(order);
	}
}
