package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;

import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	private OrderDAO orderDAO;
	private Utils utils;
	private ItemDAO itemDAO = new ItemDAO();

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Long customerID = utils.getLong();
		List<Item> items = new ArrayList<>();
		LOGGER.info("Please enter the ID of the item you want to add to the order");
		Long itemID = utils.getLong();
		Item item = itemDAO.read(itemID);
		items.add(item);
		Order order = orderDAO.create(new Order(null, customerID, items));
		LOGGER.info("Order " + order.getOrderID() + " created!" );

		return order;
	}

	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long orderID = utils.getLong();
		LOGGER.info("Order deleted!");
		return orderDAO.delete(orderID);
	}

}
