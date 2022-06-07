package com.qa.ims.persistence.domain;

public class Item {
	private Long item_id;
	private String item_name;
	private double item_cost;
	
	public Item(String item_name, double item_cost) {
		this.setItem_name(item_name);
		this.setItem_cost(item_cost);
	}
	
	

	public Item(Long item_id, String item_name, double item_cost) {
		this.setItem_id(item_id);
		this.setItem_name(item_name);
		this.setItem_cost(item_cost);
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getItem_cost() {
		return item_cost;
	}

	public void setItem_cost(double item_cost) {
		this.item_cost = item_cost;
	}



	@Override
	public String toString() {
		return "item_id: " + item_id + ", item_name: " + item_name + ", item_cost: " + item_cost;
	}
}