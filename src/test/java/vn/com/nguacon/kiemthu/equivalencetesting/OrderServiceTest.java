package vn.com.nguacon.kiemthu.equivalencetesting;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import vn.com.nguacon.kiemthu.integrationtesting.OrderService;
import vn.com.nguacon.kiemthu.integrationtesting.OrderServiceImpl;
import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderResult;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderStatus;
import vn.com.nguacon.kiemthu.integrationtesting.service.SymbolService;
import vn.com.nguacon.kiemthu.integrationtesting.topdown.stubs.SymbolServiceStub;
import vn.com.nguacon.kiemthu.integrationtesting.validator.PriceValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.QuantityValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.SymbolValidator;
import vn.com.nguacon.kiemthu.integrationtesting.validator.Validator;

/*
 * Mô tả bài toán:
 * - Kiểm tra service đặt lệnh mua chứng khoán sàn HNX
 * - Object Order: price, quantity, symbol
 * - �?i�?u kiện: floorPrice <= price <= ceilingPrice của mã chứng khoán tương ứng; price % 100 = 0
 * 				0 < quantity <= 1.000.000; quantity % 100 = 0
 * 				symbol: thuộc sản HNX 
 * 
 * - �?ầu ra: 
 * 			 - symbol: nếu mã không thuộc sàn HNX -> báo lỗi "Mã chứng khoán không đúng" 
 * 			 - nếu giá không nằm trong biên độ trần sàn trả v�?: "Giá không nằm trong biên độ trần sàn"
 * 			 - nếu giá trong biên độ trần sàn nhưng không chia hêt cho 100 thì báo "Sai bước giá"
 * 			 - quantity <= 0 báo "Số lượng phải lớn hơn 0"
 * 			 - quantity > 1.000.000 báo "Số lượng không được vượt quá 1000000"
 * 			 - 0 < quantity <= 1000000 nhưng không chia hết cho 100 thông báo "Số lượng không đúng"
 */

public class OrderServiceTest {
	private OrderService orderService;
	private Validator symbolValidator;
	private Validator priceValidator;
	private Validator quantityValidator;
	private SymbolService symbolService;
	private Order order;

	@Before
	public void setUp() {
		symbolService = new SymbolServiceStub();
		symbolValidator = new SymbolValidator(symbolService);
		priceValidator = new PriceValidator(symbolService);
		quantityValidator = new QuantityValidator();
		orderService = new OrderServiceImpl(symbolValidator, priceValidator, quantityValidator);
	}

	// symbol: ABCD, price=10000, quantity=5000 => "Mã chứng khoán không đúng" (nom, nom, nom)
	@Test
	public void kiemtra_maCKkhongdung() {
		order = new Order("ACBD", 10000, 5000);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.INVALID_SYMBOL.getMessage(),
				orderResult.getMessage());
	}

	// symbol: VND, price=11000, quantity=500 => "�?ặt lệnh thành công" (nom, min, nom)
	@Test
	public void kiemtra_giabanggiasan_voima_VND() {
		order = new Order("VND", 11000, 500);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.OK.getMessage(),
				orderResult.getMessage());
	}
	

	// symbol: VND, price=13500, quantity=500 => "�?ặt lệnh thành công" (nom, max, nom)
	@Test
	public void kiemtra_giabanggiatran_voima_VND() {
		order = new Order("VND", 13500, 500);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.OK.getMessage(),
				orderResult.getMessage());
	}
	
	// symbol: VND, price=11001, quantity=500 => "Sai bước giá" (nom, min+, nom)
	@Test
	public void kiemtra_giatrongbiendo_khongchiahet100_voima_VND() {
		order = new Order("VND", 11001, 500);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.INVALID_STEP_PRICE.getMessage(),
				orderResult.getMessage());
	}
	
	// symbol: VND, price=10999, quantity=500 => "Sai bước giá" (nom, min-, nom)
	@Test
	public void kiemtra_giathaphongiasan_voima_VND() {
		order = new Order("VND", 10999, 500);
		OrderResult orderResult = orderService.place(order);

		assertEquals(OrderStatus.PRICE_NOT_RANGE.getMessage(),
				orderResult.getMessage());
	}

	// symbol: VND, price=13499, quantity=500 => "Sai bước giá" (nom, max-, nom)
	@Test
	public void kiemtra_giatrongbiendo_khongchiahet100_case2_voima_VND() {
		order = new Order("VND", 13499, 500);
		OrderResult orderResult = orderService.place(order);
		
		assertEquals(OrderStatus.INVALID_STEP_PRICE.getMessage(),
				orderResult.getMessage());
	}
	
	// symbol: VND, price=15501, quantity=500 => "Sai bước giá" (nom, max+, nom)
	@Test
	public void kiemtra_giacaohongiatran_voima_VND() {
		order = new Order("VND", 15501, 500);
		OrderResult orderResult = orderService.place(order);
		
		assertEquals(OrderStatus.PRICE_NOT_RANGE.getMessage(),
				orderResult.getMessage());
	}
	
	// symbol: VND, price=12000, quantity=1 => "Số lượng không đúng" (nom, nom, min)
	@Test
	public void kiemtra_khoiluongBang1_voima_VND() {
		order = new Order("VND", 12000, 1);
		OrderResult orderResult = orderService.place(order);
		
		assertEquals(OrderStatus.INVALID_STEP_QUANTITY.getMessage(),
				orderResult.getMessage());
	}

	// symbol: VND, price=12000, quantity=2 => "Số lượng không đúng" (nom, nom, min+)
	@Test
	public void kiemtra_khoiluong2_voima_VND() {
		order = new Order("VND", 12000, 2);
		OrderResult orderResult = orderService.place(order);
		
		assertEquals(OrderStatus.INVALID_STEP_QUANTITY.getMessage(),
				orderResult.getMessage());
	}
	
	// symbol: VND, price=12000, quantity=1000000 => "Đặt lệnh thành công" (nom, nom, max)
	@Test
	public void kiemtra_khoiluong1000000_voima_VND() {
		order = new Order("VND", 12000, 1000000);
		OrderResult orderResult = orderService.place(order);
		
		assertEquals(OrderStatus.OK.getMessage(),
				orderResult.getMessage());
	}

	// symbol: VND, price=12000, quantity=99999 => "Số lượng không đúng" (nom, nom, max-)
	@Test
	public void kiemtra_khoiluong99999_voima_VND() {
		order = new Order("VND", 12000, 99999);
		OrderResult orderResult = orderService.place(order);
		
		assertEquals(OrderStatus.INVALID_STEP_QUANTITY.getMessage(),
				orderResult.getMessage());
	}
	
	// symbol: VND, price=12000, quantity=0 => "Số lượng phải lớn hơn 0" (nom, nom, min-)
	@Test
	public void kiemtra_khoiluong0_voima_VND() {
		order = new Order("VND", 12000, 0);
		OrderResult orderResult = orderService.place(order);
		
		assertEquals(OrderStatus.INVALID_QUANTITY.getMessage(),
				orderResult.getMessage());
	}

	// symbol: VND, price=12000, quantity=1000001 => "Số lượng không được vượt quá 1000000" (nom, nom, max+)
	@Test
	public void kiemtra_khoiluong1000001_voima_VND() {
		order = new Order("VND", 12000, 1000001);
		OrderResult orderResult = orderService.place(order);
		
		assertEquals(OrderStatus.QUANTITY_TOO_BIG.getMessage(),
				orderResult.getMessage());
	}

}
