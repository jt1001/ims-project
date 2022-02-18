package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(orders);
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("To create order please enter todays date dd/mm/yyyy");
		String orderDate = utils.getString();
		LOGGER.info("Which customer will be ordering? Enter their customer ID");
		Long CustomerID = utils.getLong();
		Order order = orderDAO.create(new Order(null, orderDate, CustomerID, null));
		LOGGER.info("Order created! Your Order ID is " + order.getOrderId());
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long orderId = utils.getLong();
		LOGGER.info("Please enter the new order status( processing/ delivered)"); // ask if they want to do this
		String orderStatus = utils.getString();
		Order order = orderDAO.update(new Order(orderId, null, null, orderStatus));
		LOGGER.info("Order Updated");
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long orderId = utils.getLong();
		LOGGER.info("Order deleted!");
		return orderDAO.delete(orderId);
	}

}
