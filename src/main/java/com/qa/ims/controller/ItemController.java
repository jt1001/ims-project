package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

/**
 * Takes in Item details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	@Override
	public Item create() {
		LOGGER.info("Please enter the item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter a description of the item");
		String description = utils.getString();
		LOGGER.info("Please enter the price of this item");
		Double itemPrice = utils.getDouble();
		Item item = itemDAO.create(new Item(null, itemName, description, itemPrice));
		LOGGER.info("Item created");
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter the new item name or leave blank and press enter");
		String itemName = utils.getString();
		LOGGER.info("Please enter the new description or leave blank and press enter");
		String description = utils.getString();
		LOGGER.info("Please enter the new item price or leave blank and press enter");
		Double itemPrice = utils.getDouble();
		Item item = itemDAO.update(new Item(itemId, itemName, description, itemPrice));
		LOGGER.info("Item Updated");
		return item;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		LOGGER.info("Item deleted!");
		return itemDAO.delete(id);
	}

}



