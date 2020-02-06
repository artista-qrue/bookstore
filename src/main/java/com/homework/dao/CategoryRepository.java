package com.homework.dao;

import java.util.List;

import com.homework.model.Category;

public interface CategoryRepository {
	//get all category
	List<Category> getAllCategories();
	
	//creater a new category
	void create(Category category);
	//get a category
	Category getCategory(String categoryName);
	
	//update category
	Category update(Category category);

	//find  category by id 
	Category findCategoryById(long id);
}
