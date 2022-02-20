package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Customer {

	private Long customerID;
	private String firstName;
	private String lastName;
	private String contactNumber;

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactnumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Customer(Long customerID, String firstName, String lastName, String contactNumber) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, customerID, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(contactNumber, other.contactNumber) && Objects.equals(customerID, other.customerID)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "Customer ID " + customerID + ", First Name: " + firstName + ", Last Name: " + lastName
				+ ", Contact number: " + contactNumber + "]";
	}

}
