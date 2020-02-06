package com.homework.dao;

import java.util.List;

import com.homework.model.Price;

public interface PriceRepository {

	List<Price> getAllPrice();
	
	Price create(double price); 
}
