package com.example.demo.model;

public class Coffee {
	
	private Integer id ;
	private String brand ;
	private String origin ;
	private String characteristics ;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Coffee()
	{
		
	}

	public Coffee(Integer id, String brand, String origin, String characteristics) {
		this.id = id;
		this.brand = brand;
		this.origin = origin;
		this.characteristics = characteristics;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	
	
	
	

}
