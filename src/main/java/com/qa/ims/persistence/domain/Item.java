package com.qa.ims.persistence.domain;

import java.util.Objects;

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
	
	@Override
	public int hashCode() {
		return Objects.hash(itemID, itemName, itemCost);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(itemID, other.itemID) && Objects.equals(itemName, other.itemName)
				&& Objects.equals(itemCost, other.itemCost);
	}
}