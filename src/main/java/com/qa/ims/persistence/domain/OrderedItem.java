package com.qa.ims.persistence.domain;

import java.util.Objects;

public class OrderedItem {

	private Long orderedItemId;
	private Long itemId;
	private Long orderId;

	public OrderedItem(Long orderedItemId, Long itemId, Long orderId) {
		super();
		this.orderedItemId = orderedItemId;
		this.itemId = itemId;
		this.orderId = orderId;
	}

	public Long getOrderedItemId() {
		return orderedItemId;
	}

	public void setOrderedItemId(Long orderedItemId) {
		this.orderedItemId = orderedItemId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemId, orderId, orderedItemId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedItem other = (OrderedItem) obj;
		return Objects.equals(itemId, other.itemId) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(orderedItemId, other.orderedItemId);
	}

	@Override
	public String toString() {
		return "OrderedItem [orderedItemId=" + orderedItemId + ", itemId=" + itemId + ", orderId=" + orderId + "]";
	}

}
