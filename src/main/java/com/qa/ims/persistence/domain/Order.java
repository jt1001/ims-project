package com.qa.ims.persistence.domain;

import java.util.List;
import java.util.Objects;

public class Order {

	private Long orderID;
	private Long customerID;
	private List<Item> items;

	public Order(Long orderID, Long customerID, List<Item> items) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.items = items;
	}

	public Order(Long orderID2, Long customerID2, String firstName, String lastName, List<Item> items2) {
		// TODO Auto-generated constructor stub
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerID, items, orderID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(customerID, other.customerID) && Objects.equals(items, other.items)
				&& Objects.equals(orderID, other.orderID);
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", customerID=" + customerID + ", items=" + items + "]";
	}

}
