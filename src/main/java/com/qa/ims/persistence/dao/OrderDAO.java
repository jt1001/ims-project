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
		Long customerID = resultSet.getLong("o.Customer_ID");
		String firstName = resultSet.getString("c.First_Name");
		String lastName = resultSet.getString("c.Last_Name");
		String contactNumber = resultSet.getString("c.Contact_Number");

		Customer c = new Customer(customerID, firstName, lastName, contactNumber);

		List<Item> items = new ArrayList<>();

		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet itemResults = statement.executeQuery(
						"SELECT oi.Order_ID, oi.Item_ID, i.Item_Name, i.Item_Price FROM ordered_items oi JOIN items i ON oi.Item_ID=i.Item_ID");) {
			while (itemResults.next()) {
				if (orderID == itemResults.getLong("oi.Order_id")) {
					Long itemId = itemResults.getLong("oi.Item_ID");
					String itemname = itemResults.getString("i.Item_Name");
					Double price = itemResults.getDouble("i.Item_Price");
					Item item = new Item(itemId, itemname, price);
					items.add(item);
				}
			}
		}
		return new Order(orderID, c, items);
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT o.Order_ID, o.Customer_ID, c.First_Name, c.Last_Name, c.Contact_number\r\n"
								+ "FROM orders o\r\n" + "Join Customers c ON o.Customer_ID=c.Customer_ID;");) {
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
					return modelFromResultSet();
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
			statement.setLong(1, order.getCustomerID());
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
