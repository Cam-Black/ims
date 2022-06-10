package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		
		final Long C_ID= 1L;
		Customer customer = new Customer();
		customer.setCustomerId(C_ID);
		Order created = new Order(customer);
		Mockito.when(utils.getLong()).thenReturn(C_ID);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> Orders = new ArrayList<>();
		Orders.add(new Order(1L, 1L));

		Mockito.when(dao.readAll()).thenReturn(Orders);

		assertEquals(Orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testAddItem() {
		Long O_ID = 1L;
		String add = "add";
		Long I_ID = 1L;
		int quantity = 1;
		Item item = new Item(I_ID);
		ItemDAO iDao = new ItemDAO();
		item = iDao.read(item.getItemID());
		Order order = dao.addItem(new Order(item.getItemID(), quantity, O_ID));
		
		Mockito.when(utils.getLong()).thenReturn(O_ID, I_ID);
		Mockito.when(utils.getString()).thenReturn(add);
		Mockito.when(utils.getInt()).thenReturn(quantity);
		
		assertEquals(order, controller.update());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getInt();
		Mockito.verify(utils, Mockito.times(1)).getString();
	}
	
	@Test
	public void testRemoveItem() {
		Long O_ID = 1L;
		String delete = "delete";
		Long I_ID = 1L;
		Order order = new Order();
		order.setOrderId(O_ID);
		order.setItemId(I_ID);
		dao.removeItem(order);
		
		Mockito.when(utils.getLong()).thenReturn(O_ID, I_ID);
		Mockito.when(utils.getString()).thenReturn(delete);
		
		assertEquals(order, controller.update());
		
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}
}