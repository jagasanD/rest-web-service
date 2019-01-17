package com.rest.webservice.bean;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description=" basic details of AppUser Bean")
//@JsonIgnoreProperties(value= {"lastName","password"})
@JsonFilter("filterId")
public class AppUserBean {
	
	@ApiModelProperty(notes=" update time Id should not be null")
	private Long Id;
	
	@Size(min=2, message=" First name must be atleast two char. ")
	@ApiModelProperty(notes=" first Name mandatory ",required=true)
	private String firstName;
	private String lastName;
	
	//@JsonIgnore
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Past
	@ApiModelProperty(notes=" Bith day should less then current date ")
	private Date dateOfBirth;
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	
	
	
}
