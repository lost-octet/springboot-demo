package com.lostoctet.restservices.Hello;

public class UserDetails {

	private String firstname;
	private String lastName;
	private String City;
	
	//To String
	@Override
	public String toString() {
		return "UserDetails [firstname=" + firstname + ", lastName=" + lastName + ", City=" + City + "]";
	}

	//Constructor
	public UserDetails(String firstname, String lastName, String city) {
		this.firstname = firstname;
		this.lastName = lastName;
		City = city;
	}
	
	//Getters & Setters
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	
	
}
