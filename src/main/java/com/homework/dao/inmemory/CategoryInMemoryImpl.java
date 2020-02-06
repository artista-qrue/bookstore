package com.homework.dao.inmemory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.homework.dao.CategoryRepository;
import com.homework.model.Category;

@Repository
public class CategoryInMemoryImpl  implements CategoryRepository{

	private Map<Long, Category> mapCategories = new HashMap<>();
	
	public CategoryInMemoryImpl() {
	Category category = new Category(1L, "Edebiyat");
	Category category2 = new Category(2L,"Sanat");
	Category category3 = new Category(3L,"Fantasy");
	Category category4 = new Category(4L,"Tarih");
	
	mapCategories.put(category.getId(), category);
	mapCategories.put(category2.getId(), category2);
	mapCategories.put(category3.getId(),category3);
	mapCategories.put(category4.getId(), category4);
	}
	@Override
	public List<Category> getAllCategories() {


		return new ArrayList<>(mapCategories.values());
	}

	@Override
	public void create(Category category) {
		category.setId(new Date().getTime());
		mapCategories.put(category.getId(), category);		
	}
	@Override
	public Category getCategory(String categoryName) {
		
		List<Category> categories = new ArrayList<Category>(mapCategories.values());
		
		for (Category category : categories) {
			if (categoryName.equals(category.getName())) {
				return category;
			}
			
		}
		return null;
	
	}
	@Override
	public Category update(Category category) {
		mapCategories.replace(category.getId(), category);
		return category;
		
	}
	@Override
	public Category findCategoryById(long id) {
	
		return mapCategories.get(id);
	}

}
