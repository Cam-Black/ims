package com.qa.ims.persistence.domain;

public class Item {
	private Long itemID;
	private String itemName;
	private double itemCost;
	
	public Item(String itemName, double itemCost) {
		this.setItemName(itemName);
		this.setItemCost(itemCost);
	}
	
	

	public Item(Long itemID, String itemName, double itemCost) {
		this.setItemID(itemID);
		this.setItemName(itemName);
		this.setItemCost(itemCost);
	}

	public Item() {}

	public Item(Long itemId) {
		this.itemID = itemId;
	}

	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemCost() {
		return itemCost;
	}

	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}

	@Override
	public String toString() {
		return "itemID: " + itemID + ", itemName: " + itemName + ", itemCost: " + itemCost;
	}
}