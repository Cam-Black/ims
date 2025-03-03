package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
	private final OrderDAO DAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		Customer customer = new Customer(1L);
		final Order created = new Order(customer, 2L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		Item item = new Item(0L);
		Customer customer = new Customer(1L, "jordan", "harrison");
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, customer, item));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		Customer customer = new Customer(1L);
		assertEquals(new Order(customer, 1L), DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		
		Long oId = 1L;
		Long iId = 1L;
		String iName = "Call of Duty";
		double iCost = 25.99;
		Item item = new Item(iId, iName, iCost);
		Order order = new Order(item, oId);
		System.out.println(order);
		
		assertEquals(DAO.read(1L), null);
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void testReadException() {
	DAO.delete(1L);
	assertNull(DAO.read(1L));
	}
	
	@Test 
	public void testCreateException() {
		Order order = new Order();
		assertNull(DAO.create(order));
	}
	
	@Test
	public void testReadLatestException() {
		DAO.delete(1L);
		assertNull(DAO.readLatest());
	}
	
	@Test
	public void testUpdate() {
		Order order = new Order();
		assertNull(DAO.update(order));
	}
}
