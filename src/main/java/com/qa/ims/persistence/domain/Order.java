package com.qa.ims.persistence.domain;

import java.util.List;

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
	public String toString() {
		return "Order ID: " + orderID + ", Customer ID: " + customerID + ", Items ordered: " + items + "]";
	}

}
