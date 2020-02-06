package com.homework.model;

public class Price implements Cloneable{
	
	private double price;


	
	public Price() {
		
	}
	public Price(double price) {
		super();
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
	    return super.clone();
	}

}
