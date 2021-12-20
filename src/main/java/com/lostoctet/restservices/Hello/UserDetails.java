package com.lostoctet.restservices.Hello;

public class UserDetails {

	private String firstName;
	private String lastName;
	private String City;
	
	
	// To String

	@Override
	public String toString() {
		//return "UserDetails [FirstName=" + firstname + ", LastName=" + lastName + ", City=" + City + "]";
		return firstName + lastName + City ;
	}
	

	//Constructor
	public UserDetails(String firstname1, String lastName1, String city1) {
		this.firstName = firstname1;
		this.lastName = lastName1;
		City = city1;
	}
	
	//Getters & Setters
	
	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname2) {
		this.firstName = firstname2;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName2) {
		this.lastName = lastName2;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city2) {
		City = city2;
	}
	
}
