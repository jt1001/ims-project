package com.qa.ims.persistence.domain;

import java.util.List;
import java.util.Objects;

public class Order {

	private Long orderID;
	private Customer c;
	private Long customerID;
	private List<Item> items;

	public Order(Long orderID, Long customerID, Customer c, List<Item> items) {
		super();
		this.orderID = orderID;
		this.c = c;
		this.items = items;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(c, items, orderID);
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
		return Objects.equals(c, other.c) && Objects.equals(items, other.items)
				&& Objects.equals(orderID, other.orderID);
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", c=" + c + ", customerID=" + customerID + ", items=" + items + "]";
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

}
