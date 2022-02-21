package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private static Long itemID;
	private String itemName;
	private Double itemPrice;

	public Item(Long itemID, String itemName, Double itemPrice) {
		Item.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public static long getItemID() {
		return itemID;
	}

	public void setItemID(long itemID) {
		Item.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemID, itemName, itemPrice);
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
		return itemID == Item.itemID && Objects.equals(itemName, other.itemName)
				&& Objects.equals(itemPrice, other.itemPrice);
	}

	@Override
	public String toString() {
		return "Item ID: " + itemID + ", Item: " + itemName + ", Price: £" + itemPrice + ".";
	}

}
