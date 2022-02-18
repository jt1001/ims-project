package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Customer {

	private Long customerId;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String emailAddress;
	private String addressLine1;
	private String addressLine2;
	private String postcode;
	private String city;
	private String country;

	public Customer() {
		super();
	}

	public Customer(Long customerId, String firstName, String lastName, String contactNumber, String emailAddress,
			String addressLine1, String addressLine2, String postcode, String city, String country) {
		this.customerId = customerId; // correct way
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.emailAddress = emailAddress;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.postcode = postcode;
		this.city = city;
		this.country = country;

	}

	public Long getcustomerId() {
		return customerId;
	}

	public void setcustomerId(Long customerId) {
		this.customerId = customerId;
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

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void getLastName(String lastName) {
		this.lastName = lastName;
	}

	public Customer(String firstName, String lastName, String contactNumber) {
		this.setFirstName(firstName);
		this.getLastName(lastName);
		this.getContactNumber();
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", contactNumer=" + contactNumber + ", emailAddress=" + emailAddress + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", postcode=" + postcode + ", city=" + city
				+ ", country=" + country + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, city, contactNumber, country, customerId, emailAddress,
				firstName, lastName, postcode);
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
		return Objects.equals(customerId, other.customerId) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(contactNumber, other.contactNumber)
				&& Objects.equals(emailAddress, other.emailAddress) && Objects.equals(addressLine1, other.addressLine1)
				&& Objects.equals(addressLine2, other.addressLine2) && Objects.equals(postcode, other.postcode)
				&& Objects.equals(city, other.city) && Objects.equals(country, other.country);
	}

}
