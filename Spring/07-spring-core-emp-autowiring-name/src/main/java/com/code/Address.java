package com.code;

public class Address {
	String city;
	String state;

	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
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



	public Address(String city, String state) {
		super();
		this.city = city;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + "]";
	}
	
	
	
}
