package com.qa.ims.persistence.domain;

public class Order {
	private Long orderId;
	private Customer customer;
	private Item item;
	private int itemQuantity;
	
	public Order(Customer customer, Item item, int itemQuantity) {
		this.customer = customer;
		this.item = item;
		this.itemQuantity = itemQuantity;
	}

	public Order(Long orderId, Customer customer, Item item, int itemQuantity) {
		this.orderId = orderId;
		this.customer = customer;
		this.item = item;
		this.itemQuantity = itemQuantity;
	}
	
	@Override
	public String toString() {
		String name = customer.getFirstName() + " " + customer.getSurname();
		return "orderId: " + orderId + ", Customer Name: " + name + ", Product: " + item.getItemName() + ", Quantity: " + itemQuantity + ", Total Cost: " + (item.getItemCost() * itemQuantity);
	}
}