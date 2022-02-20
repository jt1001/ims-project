package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private long itemID;
	private String itemName;
	private Double itemPrice;

	public Item() {
		super();
	}

	public Item(long itemID, String itemName, Double itemPrice) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public long getItemID() {
		return itemID;
	}

	public void setItemID(long itemID) {
		this.itemID = itemID;
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
		return itemID == other.itemID && Objects.equals(itemName, other.itemName)
				&& Objects.equals(itemPrice, other.itemPrice);
	}

	@Override
	public String toString() {
		return "Item [Item ID = " + itemID + ", Item = " + itemName + ", Price = £" + itemPrice + "]";
	}

}
