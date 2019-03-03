package com.sango.microservices.client.model;

import java.util.Date;
import java.util.UUID;

public class StudentDetailResponse {

	private UUID id;
	private String name;
	private Date dob;
	private String street;
	private String street2;
	private String city;
	private String state;
	private String zip;
	private String course;
	
	public StudentDetailResponse() {
		// TODO Auto-generated constructor stub
	}

	public StudentDetailResponse(UUID id, String name, Date dob, String street, String street2, String city, String state,
			String zip, String course) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.street = street;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.course = course;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}
