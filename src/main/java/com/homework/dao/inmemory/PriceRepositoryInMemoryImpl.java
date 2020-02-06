package com.homework.dao.inmemory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.homework.dao.PriceRepository;
import com.homework.model.Price;

@Service
public class PriceRepositoryInMemoryImpl implements PriceRepository {

	private Map<Long, Price> priceCategories = new HashMap<>();
	
	@Override
	public List<Price> getAllPrice() {
		
		return null;
	}

	@Override
	public Price create(double price) {
	
		Price price2 = new Price(price);
		priceCategories.put(1L, price2);
		return price2;
	}

}
