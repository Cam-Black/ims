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
		Long orderId = resultSet.getLong("Order ID");
		
		Long itemId = resultSet.getLong("item_id");
		String itemName = resultSet.getString("Product");
		double itemCost = resultSet.getDouble("Item Price");
		
		Long custId = resultSet.getLong("customer_id");
		String custFName = resultSet.getString("First Name");
		String custLName = resultSet.getString("Last Name");
		
		int itemQuantity = resultSet.getInt("Quantity");
		
		Customer customer = new Customer(custId, custFName, custLName);
		Item item = new Item(itemId, itemName, itemCost);
		
		return new Order(orderId, customer, item, itemQuantity);
	}
	
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT customer_id, item_id, customers.first_name AS `First Name`, customers.surname AS `Last Name`, orders.order_id AS `Order ID`, items.item_name AS Product, SUM(order_items.item_quantity) AS Quantity, items.item_cost AS `Item Price` FROM order_items INNER JOIN orders ON orders.order_id = order_items.fk_order_id INNER JOIN items ON items.item_id = order_items.fk_item_id INNER JOIN customers ON customers.customer_id = orders.fk_customer_id;");) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order create(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE order_items_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}