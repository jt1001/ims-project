package com.qa.ims.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;
	private ItemDAO itemDAO = new ItemDAO();

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	@Override
	public Order create() { //// NEEDS ADJUSTING
		LOGGER.info("Please enter the Customer ID");
		Long customerID = utils.getLong();
		List<Item> items = new ArrayList<>();
		LOGGER.info("Please enter an item ID to add to order");
		Long itemID = utils.getLong();
		Item item = itemDAO.read(itemID);
		items.add(item);
		Order order = orderDAO.create(new Order(null, customerID, null, items));
		LOGGER.info("Order created");
		return null;
	}

	@Override  //// // NEEDS ADJUSTING
	public Order update() {
		LOGGER.info("Please enter the Order ID you want to amend");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter an item ID to add to order");
		Long itemID = utils.getLong();
		Item item = itemDAO.read(itemID);
		LOGGER.info("Order updated");
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
