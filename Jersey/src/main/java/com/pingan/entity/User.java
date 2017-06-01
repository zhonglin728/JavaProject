package com.pingan.entity;

import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	public User() {
		super();
	}
	

}
