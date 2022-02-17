package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long itemId;
	private String itemName;
	private String description;
	private Double itemPrice;

	public Item() {
		super();
	}

	public Item(Long itemId, String itemName, String description, Double itemPrice) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.itemPrice = itemPrice;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", description=" + description
				+ ", itemPrice=" + itemPrice + "]";
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, itemId, itemName, itemPrice);
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
		return Objects.equals(description, other.description) && Objects.equals(itemId, other.itemId)
				&& Objects.equals(itemName, other.itemName) && Objects.equals(itemPrice, other.itemPrice);
	}

}
