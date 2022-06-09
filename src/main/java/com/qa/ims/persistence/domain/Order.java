package com.qa.ims.persistence.domain;

public class Order {
	private Long orderId;
	private Customer customer;
	private Item item;
	private int itemQuantity;
	private Long customerId;
	private Long itemId;
	
	
	public Order(Customer customer) {
		this.customer = customer;
	}

	public Order(Customer customer, Long orderId) {
		this.customer = customer;
		this.orderId = orderId;
	}

	public Order(Long id) {
		this.customerId = id;
	}

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

	public Order(Long itemId, int quantity, Long orderId) {
		this.itemId = itemId;
		this.itemQuantity = quantity;
		this.orderId = orderId;
	}

	public Order(Item item, Long orderItemsId) {
		this.item = item;
		this.orderId = orderItemsId;
	}

	public Order(Item item) {
		this.item = item;
	}

	public Order() {}

	public Long getCustomerId() {
		System.out.println(this.customerId);
		return this.customerId;
	}

	public int getItemQuantity() {
		return this.itemQuantity;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Item getItem() {
		return this.item;
	}

	public Customer getCustomer() {
		return this.customer;
	}
	 public Long getItemId() {
		 return this.itemId;
	 }
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		String name = customer.getFirstName() + " " + customer.getSurname();
		if (item == null) {
			return "Order ID: " + orderId + ", Customer ID: " + customer.getCustomerId();
		}
		else {
			return "Order ID: " + orderId + ", Customer ID: " + customer.getCustomerId() + ", Customer Name: " + name + ", Item ID: " + item.getItemID() + ", Item Name: " + item.getItemName() + ", Quantity: " + itemQuantity + ", Total Cost: " + (item.getItemCost() * itemQuantity);
		}
	}
}