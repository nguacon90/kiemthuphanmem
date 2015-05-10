package vn.com.nguacon.kiemthu.integrationtesting;

import vn.com.nguacon.kiemthu.integrationtesting.model.Order;
import vn.com.nguacon.kiemthu.integrationtesting.model.OrderResult;


public interface OrderService {

	OrderResult place(Order order);

}
