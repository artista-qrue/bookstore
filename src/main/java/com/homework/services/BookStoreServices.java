package com.homework.services;

import java.util.List;

import com.homework.model.Book;
import com.homework.model.BookStore;
import com.homework.model.Category;

public interface BookStoreServices {

	List<Book> getAllBooks();

	void createBook(Book book);

	List<Category> getAllCategories();

	void createCategory(Category category);

	List<BookStore> getAllBookstores();

	void createBookstore(BookStore bookStore);

	BookStore findByBookstore(long id);

	void deleteBookstore(long id);
	
	Category updateCategory(Category category);

	Category findCategoryById(long id);
	
	BookStore findByNameBookstore(String name);
	BookStore updateBookstoreToBook(BookStore bookStore,Book book);
}
