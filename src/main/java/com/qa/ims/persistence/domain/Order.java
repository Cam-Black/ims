package com.qa.ims.persistence.domain;

public class Order {
	private Long order_id;
	private Customer customer;
	
	public Order(Customer customer) {
		super();
		this.customer = customer;
	}

	public Order(Long order_id, Customer customer) {
		this.order_id = order_id;
		this.customer = customer;
	}
}