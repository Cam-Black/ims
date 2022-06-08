package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("order_id");

		Long itemId = resultSet.getLong("item_id");
		String itemName = resultSet.getString("item_name");
		double itemCost = resultSet.getDouble("item_cost");

		Long custId = resultSet.getLong("customer_id");
		String custFName = resultSet.getString("first_name");
		String custLName = resultSet.getString("surname");

		int itemQuantity = resultSet.getInt("item_quantity");

		Customer customer = new Customer(custId, custFName, custLName);
		Item item = new Item(itemId, itemName, itemCost);

		return new Order(orderId, customer, item, itemQuantity);
	}
	
	public Order ordersFromResultSet(ResultSet rs) throws SQLException {
		Long orderId = rs.getLong("order_id");
		Long custId = rs.getLong("fk_customer_id");
//		String custFName = rs.getString("first_name");
//		String custLName = rs.getString("surname");
		Customer customer = new Customer(custId);
		Order order = new Order(customer, orderId);
//		order.setCustomerId(custId);
		return order;
	}
	
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
//				ResultSet resultSet = statement.executeQuery("SELECT orders.order_id, customers.customer_id, customers.first_name, customers.surname, items.item_id, items.item_name, order_items.item_quantity, items.item_cost FROM orders INNER JOIN order_items ON orders.order_id = order_items.fk_order_id INNER JOIN items ON items.item_id = order_items.fk_item_id INNER JOIN customers ON customers.customer_id = orders.fk_customer_id ORDER BY order_id;");) {
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(ordersFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return ordersFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders (fk_customer_id) VALUES (?)");) {
			statement.setLong(1, order.getCustomer().getCustomerId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order update(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}