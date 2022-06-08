package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		LOGGER.info("");
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter the customer ID for the new order");
		Long id = utils.getLong();
		Customer customer = new Customer();
		customer.setCustomerId(id);
		Order order = orderDAO.create(new Order(customer));
		LOGGER.info("Order Created\n");
		LOGGER.info(order + "\n");
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
//		LOGGER.info("Would you like to add or delete an item from an order");
		LOGGER.info("Please enter the ID of the item you wish to add");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter the quantity of the item to add");
		int quantity = utils.getInt();
		Item item = new Item(itemId);
		System.out.println(item);
		Order order = orderDAO.addItem(new Order(item, quantity, id));
		LOGGER.info("Order Updated\n");
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the Order you would like to delete");
		Long id = utils.getLong();
		LOGGER.info("Order Deleted\n");
		return orderDAO.delete(id);
	}
	
}
