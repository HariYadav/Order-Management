/**
 * 
 */
package xyz.cloudblog.order;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xyz.cloudblog.order.bean.Order;
import xyz.cloudblog.order.bean.OrderItem;

/**
 * @author hari.yadav
 * 
 */
public class OrderServiceTest {

	private OrderService orderService;

	@Before
	public void setUp() throws Exception {
		orderService = new OrderServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		orderService = null;
	}

	@Test
	public void testCreateOrder() {
		Order actualOrder = populateOrder();
		
		Order expectedOrder = orderService.createOrder(actualOrder);

		assertNotNull(expectedOrder.getOrderId());
		assertEquals(expectedOrder, actualOrder);
	}

	@Test
	public void testUpdateOrder(){
		Order actualOrder = populateOrder();
		
		Order expectedOrder = orderService.createOrder(actualOrder);
		
		expectedOrder.setCustomerId("cust9999");
		expectedOrder.getOrderItems()[0].setDiscount(19.00);
		Order newExpectedOrder = orderService.updateOrder(expectedOrder);
		
		assertNotEquals(newExpectedOrder.getCustomerId(), expectedOrder.getCustomerId());
		assertEquals(newExpectedOrder.getCustomerId(), "cust9999");
		assertEquals(newExpectedOrder.getOrderId(), expectedOrder.getOrderId());
		assertEquals(newExpectedOrder.getOrderItems()[0].getDiscount(), new Double(19.00));
		assertEquals(newExpectedOrder.getOrderItems().length, expectedOrder.getOrderItems().length);
	}
	
	@Test
	public void testDeleteOrder(){
		Order order = populateOrder();
		
		Order actualOrder = orderService.createOrder(order);
		Boolean isOrderDeleted = orderService.deleteOrder(actualOrder.getOrderId());
		assertEquals(Boolean.TRUE, isOrderDeleted); 
	}
	
	@Test
	public void testFetchOrder(){
		Order order = populateOrder();
		
		Order newOrder = orderService.createOrder(order);
		Order actualOrder = orderService.fetchOrder(newOrder.getOrderId());
		assertEquals(actualOrder.getCustomerId(), newOrder.getCustomerId()); 
		assertEquals(actualOrder.getOrderItems().length, newOrder.getOrderItems().length); 
	}
	
	@Test
	public void testFetchAllOrder(){
		Order order = populateOrder();
		
		orderService.createOrder(order);
		List<Order> actualOrders = orderService.fetchAllOrders();
		 
		assertEquals(actualOrders.size(), 1); 
	}
	
	protected Order populateOrder() {
		Order order = new Order();
		order.setOrderId(null);
		order.setCustomerId("cust12345");
		order.setOrderDate(new Date());
		order.setOrderItems(populateOrderItems());

		return order;
	}

	protected OrderItem[] populateOrderItems() {
		OrderItem[] orderItems = new OrderItem[2];
				
		OrderItem item = new OrderItem();
		item.setItemId(1234);
		item.setSku("sku2545");
		item.setPrice(234.324);
		item.setDiscount(2.0);
		orderItems[0] = item;
		
		OrderItem item1 = new OrderItem();
		item1.setItemId(2545);
		item1.setSku("sku1234");
		item1.setPrice(25435.45);
		item1.setDiscount(5.0);
		orderItems[1] = item1;
		
		return orderItems;
	}
}
