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
		Long OrderID = resultSet.getLong("Order_ID");
		Long CustomerID = resultSet.getLong("Customer_ID");

		List<Item> items = new ArrayList<>();

		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet1 = statement.executeQuery(
						"SELECT oi.Order_ID, oi.Item_ID, i.Item_Name, i.Item_Price FROM ordered_items oi JOIN items i ON oi.Item_ID=i.Item_ID");) {
			while (resultSet1.next()) {
				if (OrderID == resultSet1.getLong("Order_ID")) {
					Long ItemId = resultSet1.getLong("Item_ID");
					String ItemName = resultSet1.getString("Item_Name");
					Double ItemPrice = resultSet1.getDouble("Item_Price");
					Item item = new Item(ItemId, ItemName, ItemPrice);
					items.add(item);
				}
			}
		}
		return new Order(OrderID, CustomerID, items);
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders");) {
			List<Order> Orders = new ArrayList<>();
			while (resultSet.next()) {
				Orders.add(modelFromResultSet(resultSet));
			}
			return Orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Long readNewOrderIDCreated() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY Order_ID DESC LIMIT 1");) {
			resultSet.next();
			return resultSet.getLong("Order_ID");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readLatest() {
		Order result;
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT o.Order_ID, o.Customer_ID, c.First_Name, c.Last_Name, c.Contact_Number\r\n"
								+ "FROM orders o\r\n" + "JOIN customers c ON o.Customer_ID=c.Customer_ID");) {
			resultSet.next();
			result = modelFromResultSet(resultSet);
			return result;
		} catch (

		Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
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

	@Override
	public Order create(Order Order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO Orders(Customer_ID) VALUES (?)");) {
			statement.setLong(1, Order.getCustomerID());
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		List<Item> items = Order.getItems();
		for (int i = 0; i < items.size(); i++) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection
							.prepareStatement("INSERT INTO ordered_items(Order_ID, Item_ID) VALUES (?, ?)");) {
				Long orderID = readNewOrderIDCreated();
				Long itemId = items.get(i).getItemID();
				statement.setLong(1, orderID);
				statement.setLong(2, itemId);
				statement.executeUpdate();
				if (i + 1 == items.size()) {
					return readLatest();
				}
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
		}
		return null;

	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement1 = connection.prepareStatement("DELETE FROM orders WHERE Order_ID = ?");
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM ordered_items WHERE Order_ID = ?");) {
			statement.setLong(1, id);
			statement1.setLong(1, id);
			statement.executeUpdate();
			return statement1.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Order update(Order Order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE Ordered_Items SET Item_ID = ? WHERE Order_ID = ?");) {
			statement.setLong(1, Item.getItemID());
			statement.setLong(2, Order.getOrderID());
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		List<Item> items = Order.getItems();
		for (int i = 0; i < items.size(); i++) {
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection
							.prepareStatement("INSERT INTO ordered_items(Order_ID, Item_ID) VALUES (?, ?)");) {
				Long orderID = readNewOrderIDCreated();
				Long itemId = items.get(i).getItemID();
				statement.setLong(1, orderID);
				statement.setLong(2, itemId);
				statement.executeUpdate();
				if (i + 1 == items.size()) {
					return readLatest();
				}
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
		}
		return null;
	}

	public Order updateOrder(Order Order, Item Item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE Ordered_Items SET Item_ID = ? WHERE Order_ID = ?");) {
			statement.setLong(1, Item.getItemID());
			statement.setLong(2, Order.getOrderID());
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
//		
		return null;
	}

}
