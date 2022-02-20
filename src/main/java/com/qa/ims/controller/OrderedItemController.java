package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderedItemDAO;
import com.qa.ims.persistence.domain.OrderedItem;
import com.qa.ims.utils.Utils;

public class OrderedItemController implements CrudController<OrderedItem>{
	
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderedItemDAO orderedItemDAO;
	private Utils utils;
	
	@Override
	public List<OrderedItem> readAll() {
		List<OrderedItem> orderedItems = orderedItemDAO.readAll();
		for (OrderedItem ordereditem : orderedItems) {
			LOGGER.info(ordereditem);
		}
		return null;
	}
	@Override
	public OrderedItem create() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OrderedItem update() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	} 
	

}
