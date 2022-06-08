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
		Customer customer = new Customer(custId);
		Order order = new Order(customer, orderId);
		return order;
	}
	
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT o.order_id, c.customer_id, c.first_name, c.surname, i.item_id, i.item_name, i.item_cost, oi.item_quantity FROM orders o LEFT OUTER JOIN order_items oi ON o.order_id = oi.fk_order_id LEFT OUTER JOIN items i ON i.item_id = oi.fk_item_id LEFT OUTER JOIN customers c ON c.customer_id = o.fk_customer_id ORDER BY order_id");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
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
		return null;
	}
	
	public Order addItem(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items (fk_item_id, item_quantity) VALUES (?, ?) WHERE fk_order_id = ?");) {
			statement.setLong(1, order.getOrderId());
		}  catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return order;
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