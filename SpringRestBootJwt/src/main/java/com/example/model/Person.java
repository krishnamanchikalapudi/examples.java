package com.example.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@XmlRootElement
public class Person {

	private Long id;

	private String username;
	private String role;
	private String firstName, lastName, phoneNumber;

	public Person() {
	}

	public Person(Long id, String firstName) {
		this.id = id;
		this.firstName = firstName;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("         ");
		sb.append("Id: ");
		sb.append(getId());
		sb.append("         ");
		sb.append("Username: ");
		sb.append(getUsername());
		sb.append("         ");
		sb.append("Role: ");
		sb.append(getRole());

		return sb.toString();
	}
}
