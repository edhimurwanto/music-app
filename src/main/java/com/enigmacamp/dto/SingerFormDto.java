package com.enigmacamp.dto;

import java.sql.Date;

import com.enigmacamp.enums.Gender;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DTO class for insert new singer")
public class SingerFormDto {

	@ApiModelProperty(notes = "First Name of singer", required = true, position = 0)
	private String firstName;
	
	@ApiModelProperty(notes = "Last Name of singer", required = true, position = 1)
	private String lastName;
	
	@ApiModelProperty(notes = "Birth date of singer", required = true, position = 2)
	private Date birthDate;
	
	@ApiModelProperty(notes = "Gender of singer", required = true, position = 3)
	private Gender gender;

	public SingerFormDto() {
		super();
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
