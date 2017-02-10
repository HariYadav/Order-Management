package xyz.cloudblog.order;

import java.util.List;

import xyz.cloudblog.order.bean.Order;

public interface OrderService {

	public Order createOrder(Order order);
	
	public Order updateOrder(Order order);
	
	public Boolean deleteOrder(Integer orderId);
	
	public Order fetchOrder(Integer orderId);
	
	public List<Order> fetchAllOrders();
	
}
