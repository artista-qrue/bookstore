package com.homework.model;

public class Book implements Cloneable {

	private long id;
	private String name;
	private Category category;
	private Price price;

	public Book() {
		
	}
	
	
	public Book(long id, String name, Price price, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	public Price getPrice() {
		return price;
	}


	public void setPrice(Price price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + "]";
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Book cloned = (Book)super.clone();
	    cloned.setPrice((Price)cloned.getPrice().clone());   
	    return cloned;
	}

}
