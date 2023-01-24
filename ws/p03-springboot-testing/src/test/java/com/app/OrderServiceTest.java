package com.app;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.app.dao.OrderDao;
import com.app.exceptions.OrderException;
import com.app.models.Order;
import com.app.services.OrderService;

@SpringBootTest
public class OrderServiceTest {

	@MockBean
	private OrderDao orderDao;
	@Autowired
	private OrderService service;
	
	@Test
	public void shouldPlaceOrder() throws OrderException, SQLException {
		System.out.println(service);
		Order order = new Order();
		
		Mockito
			.when(orderDao.saveOrder(order))
			.thenReturn(new Order(23, "Laptop", 85000));
		
		
		boolean isPlaced = service.placeOrder(order);
		
		Assertions.assertTrue(isPlaced);
	}
	
	@Test
	public void shouldNotPlaceOrder() throws OrderException, SQLException {
		System.out.println(service);
		Order order = new Order(23, "Laptop", 85000);
		
		Mockito.when(orderDao.saveOrder(order)).thenReturn(new Order(-23, "Laptop", 85000));
		
		
		boolean isPlaced = service.placeOrder(order);
		
		Assertions.assertFalse(isPlaced);
	}
	
	@Test
	public void shouldNotPlaceOrderGetsNull() throws OrderException, SQLException {
		System.out.println(service);
		Order order = new Order(23, "Laptop", 85000);
		
		Mockito.when(orderDao.saveOrder(order)).thenReturn(null);
		
		Assertions.assertThrows(OrderException.class, ()->{			
			service.placeOrder(order);
		});
		
		
	}
	
	@Test
	public void shouldNotPlaceOrderWhenDbErrorOccurs() throws OrderException, SQLException {
		System.out.println(service);
		Order order = new Order(23, "Laptop", 85000);
		
		Mockito.when(orderDao.saveOrder(order)).thenThrow(SQLException.class);
		
		Assertions.assertThrows(OrderException.class, ()->{			
			service.placeOrder(order);
		});
		
	}
	
}
