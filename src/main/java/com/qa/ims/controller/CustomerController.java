package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class CustomerController implements CrudController<Customer> {

	public static final Logger LOGGER = LogManager.getLogger();

	private CustomerDAO customerDAO;
	private Utils utils;

	public CustomerController(CustomerDAO customerDAO, Utils utils) {
		super();
		this.customerDAO = customerDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Customer> readAll() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer);
		}
		return customers;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Customer create() {
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String lastName = utils.getString();
		LOGGER.info("Please enter a contact number");
		String contactNumber = utils.getString();
		LOGGER.info("Please enter an email address");
		String emailAddress = utils.getString();
		LOGGER.info("Please enter the first line of address");
		String addressLine1 = utils.getString();
		LOGGER.info("Please enter the second line of address (leave blank and press enter if not applicable");
		String addressLine2 = utils.getString();
		LOGGER.info("Please enter the postcode");
		String postcode = utils.getString();
		LOGGER.info("Please enter the address city");
		String city = utils.getString();
		LOGGER.info("Please enter the country of address");
		String country = utils.getString();
		Customer customer = customerDAO.create(new Customer(null, firstName, lastName, contactNumber, emailAddress, addressLine1, addressLine2, postcode,
				city, country));
		LOGGER.info("Customer created");
		return customer;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Customer update() {
		LOGGER.info("Please enter the id of the customer you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Enter a new first name if you want to change this or else leave blank and press enter");
		String firstName = utils.getString();
		LOGGER.info("Please enter a new surname or leave blank and press enter");
		String lastName = utils.getString();
		LOGGER.info("Please enter a new contact number or leave blank and press enter");
		String contactNumber = utils.getString();
		LOGGER.info("Please enter a new email address or leave blank and press enter");
		String emailAddress = utils.getString();
		LOGGER.info("Please enter a new first line of address or leave blank and press enter");
		String addressLine1 = utils.getString();
		LOGGER.info("Please enter a new second line of address  or leave blank and press enter");
		String addressLine2 = utils.getString();
		LOGGER.info("Please enter the new postcode or leave blank and press enter");
		String postcode = utils.getString();
		LOGGER.info("Please enter the new address city or leave blank and press enter");
		String city = utils.getString();
		LOGGER.info("Please enter the new country of address or leave blank and press enter");
		String country = utils.getString();
		Customer customer = customerDAO.update(new Customer(id,firstName, lastName, contactNumber, emailAddress, addressLine1, addressLine2, postcode, city, country));
		LOGGER.info("Customer Updated");
		return customer;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		LOGGER.info("Customer deleted!");
		return customerDAO.delete(id);
	}

}
