package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Customer {

	private Long customerId;
	private String firstName;
	private String surname;

	public Customer(String firstName, String surname) {
		this.setFirstName(firstName);
		this.setSurname(surname);
	}

	public Customer(Long customerId, String firstName, String surname) {
		this.setCustomerId(customerId);
		this.setFirstName(firstName);
		this.setSurname(surname);
	}

	public Customer() {}

	public Customer(Long custId) {
		this.customerId = custId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long id) {
		this.customerId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Customer ID: " + customerId + " First Name: " + firstName + " Surname: " + surname;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, firstName, surname);
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
				&& Objects.equals(surname, other.surname);
	}
}