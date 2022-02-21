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

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderID = resultSet.getLong("Order_ID");
		Long customerID = resultSet.getLong("Customer_ID");

		List<Item> items = new ArrayList<>();

		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet itemResults = statement.executeQuery(
						"SELECT oi.Order_ID, oi.Item_ID, i.Item_Name, i.Item_Price FROM ordered_items oi JOIN items i ON oi.Item_ID=i.Item_ID");) {
			while (itemResults.next()) {
				if (orderID == itemResults.getLong("Order_id")) {
					Long itemId = itemResults.getLong("Item_id");
					String itemName = itemResults.getString("Item_Name");
					Double price = itemResults.getDouble("Item_Price");
					Item item = new Item(itemId, itemName, price);
					items.add(item);
				}
			}
		}
		return new Order(orderID, customerID, items);
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT oi.Order_ID, oi.Item_ID, i.Item_Name, i.Item_Price FROM ordered_items oi JOIN items i ON oi.Item_ID=i.Item_ID");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE Order_ID = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Long readNewOrderID() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY Order_ID DESC LIMIT 1;");) {
			resultSet.next();
			return resultSet.getLong("Order_ID");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readOrderWItems() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT o.Order_ID, c.Customer_ID, c.First_Name, c.Last_Name, c.Contact_Number, oi.Item_ID, i.Item_Name, i.Item_Price \r\n"
								+ "FROM ordered_items oi\r\n" + "JOIN items i ON i.Item_ID = oi.Item_id\r\n"
								+ "Join orders o ON oi.Order_ID = o.Order_ID\r\n"
								+ "JOIN customers c ON c.Customer_ID = o.Customer_ID");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(Customer_ID) VALUES ( ?)");) {
			statement.setLong(1, order.getCustomerID());
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		List<Item> items = order.getItems();
		for (int i = 0; i < items.size(); i++) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection
							.prepareStatement("INSERT INTO ordered_items(Order_ID, Item_ID) VALUES (?, ?)");) {
				Long orderID = readNewOrderID();
				Long itemID = items.get(i).getItemID();
				statement.setLong(1, orderID);
				statement.setLong(2, itemID);
				statement.executeUpdate();
				if (i + 1 == items.size()) {
					return readOrderWItems();
				}
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
		}
		return null;
	}

	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE ordered_items SET Item_ID WHERE Order_ID = ?");) {
			statement.setLong(1, Item.getItemID());
			statement.setLong(2, order.getOrderID());
			statement.executeUpdate();
			return read(order.getOrderID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE Order_ID = ?");
				PreparedStatement statement1 = connection
						.prepareStatement("DELETE FROM ordered_items WHERE Order_ID = ?");) {
			statement.setLong(1, id);
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
