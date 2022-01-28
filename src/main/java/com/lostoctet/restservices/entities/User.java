package com.lostoctet.restservices.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

//Entity Declaration. Entity is one row of the database.
@Entity
@Table(name ="users")
@Data
@Schema(description = "User Entity Schema")
//@JsonIgnoreProperties({"firstname", "lastname"})		//Static Json Filtering
//@JsonFilter(value = "userFilter")						//Used for MappingJacksonValue Filtering
public class User extends RepresentationModel<User> {
	
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	@Schema(description = "Auto-Generated ID", name = "userid", required = true, example = "1")
	private Long userid;

	@NotEmpty(message = "Username Cant be Empty")
	@Column(name = "USER_NAME", length=50, nullable=false, unique=true)
	@JsonView(Views.External.class)
	@Schema(description = "Unique Username", name = "username", required = true)
	private String username;

	@Size(min = 2, max = 50, message ="Minimum Firstname Length is 2")
	@Column(name = "FIRST_NAME", length=50, nullable=false)
	@JsonView(Views.External.class)
	@Schema(description = "User First Name", name = "firstname", required = true, minLength = 2, maxLength = 50, example = "Tom")
	private String firstname;
	
	@Column(name = "LAST_NAME", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String lastname;
	
	@Column(name = "EMAIL_ADDRESS", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String email;
	
	@Column(name = "ROLE", length=50, nullable=false)
	@JsonView(Views.Internal.class)
	private String role;
	
	@Column(name = "SSN", length=50, nullable=false, unique=true)
	@JsonView(Views.Internal.class)
	//@JsonIgnore		//Static Json Filtering
	private String ssn;

	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;

	@Column(name = "ADDRESS")
	private String address;


//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}

	//No argument Constructor- Mandatory in JPA
//	public User() {
//	}
	
	
	//Field Constructor
//	public User(Long userid, String username, String firstname, String lastname, String email, String role, String ssn, List<Order> orders) {
//		this.userid = userid;
//		this.username = username;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.email = email;
//		this.role = role;
//		this.ssn = ssn;
//		this.orders = orders;
//	}

	//Getters & Setters- Mandatory in JPA
//	public Long getUserid() {
//		return userid;
//	}
//
//	public void setUserid(Long userid) {
//		this.userid = userid;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//
//	public String getFirstname() {
//		return firstname;
//	}
//
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//
//	public String getLastname() {
//		return lastname;
//	}
//
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
//
//	public String getEmail() {
//		return email;
//	}
//
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//
//	public String getRole() {
//		return role;
//	}
//
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//
//	public String getSsn() {
//		return ssn;
//	}
//
//
//	public void setSsn(String ssn) {
//		this.ssn = ssn;
//	}

	
	//To String- Optional Useful in Bean Logging
//	@Override
//	public String toString() {
//		return "User [id=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
//				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
//	}
	
}
