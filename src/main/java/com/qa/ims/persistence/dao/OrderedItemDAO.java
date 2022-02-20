package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderedItem;
import com.qa.ims.utils.DBUtils;

public class OrderedItemDAO implements Dao<OrderedItem> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public OrderedItem modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderedItemId = resultSet.getLong("Ordered_Item_ID");
		Long itemId = resultSet.getLong("Item_ID");
		Long orderId = resultSet.getLong("Order_ID");
		return new OrderedItem(orderedItemId, itemId, orderId);
	}

	@Override
	public List<OrderedItem> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement(); // Statement is for reading from the DB!
				ResultSet resultSet = statement.executeQuery("SELECT * FROM ordered_items");) {
			List<OrderedItem> orderedItemId = new ArrayList<>();
			while (resultSet.next()) {
				orderedItemId.add(modelFromResultSet(resultSet));
			}
			return orderedItemId;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public OrderedItem read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM ordered_items WHERE Ordered_Item_ID = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) { // executeQuery is for reading from DB
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderedItem create(OrderedItem ordereditem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO ordered_items(Item_ID, Order_ID, Ordered_Item_ID) VALUES (?, ?, ?)");) {
			statement.setLong(1, ordereditem.getItemId());
			statement.setLong(2, ordereditem.getOrderId());
			statement.setLong(3, ordereditem.getOrderedItemId());
			statement.executeUpdate();
			return (OrderedItem) readAll();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderedItem update(OrderedItem ordereditem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE customers SET First_Name = ?, Last_Name = ? WHERE Ordered_Item_ID = ?");) {
			statement.setLong(1, ordereditem.getItemId());
			statement.setLong(2, ordereditem.getOrderId());
			statement.setLong(3, ordereditem.getOrderedItemId());
			statement.executeUpdate();
			statement.executeUpdate();
			return read(ordereditem.getOrderedItemId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
