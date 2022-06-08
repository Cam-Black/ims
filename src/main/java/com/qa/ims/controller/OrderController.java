package com.qa.ims.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;
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
		CustomerDAO customerDAO = new CustomerDAO();
		LOGGER.info("Please enter the customer ID for the new order");
		Long id = utils.getLong();
		customerDAO.read(id);
		Customer customer = new Customer();
		customer.setCustomerId(id);
		Order order = orderDAO.create(new Order(customer));
		LOGGER.info("order created\n");
		LOGGER.info(order + "\n");
		return order;
	}

	@Override
	public Order update() {
		OrderDAO orderDAO = new OrderDAO();
		LOGGER.info("Select the order id of the order you wish to update");
		Long id = utils.getLong();
		LOGGER.info("Would you like to add or delete an item from an order");
		String addOrDelete = utils.getString();
		
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the Order you would like to delete");
		Long id = utils.getLong();
		LOGGER.info("");
		return orderDAO.delete(id);
	}
	
}
