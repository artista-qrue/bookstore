package com.homework.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homework.dao.BookRepository;
import com.homework.dao.BookStoreRepository;
import com.homework.dao.CategoryRepository;
import com.homework.model.Book;
import com.homework.model.BookStore;
import com.homework.model.Category;

@Service
public  class BookStoreServiceImpl implements BookStoreServices {

	private BookRepository bookRepository;
	private CategoryRepository categoryReposiyory;
	private BookStoreRepository bookstoreRepository;

	@Autowired
	public void setBookstoreRepository(BookStoreRepository bookstoreRepository) {
		this.bookstoreRepository = bookstoreRepository;
	}

	@Autowired
	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Autowired
	public void setCategoryReposiyory(CategoryRepository categoryReposiyory) {
		this.categoryReposiyory = categoryReposiyory;
	}

	@Override
	public List<Book> getAllBooks() {

		return bookRepository.getAllBooks();
	}

	@Override
	public void createBook(Book book) {
		bookRepository.create(book);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryReposiyory.getAllCategories();
	}

	@Override
	public void createCategory(Category category) {
		categoryReposiyory.create(category);

	}

	@Override
	public List<BookStore> getAllBookstores() {

		return bookstoreRepository.getAllBookStore();
	}

	@Override
	public void createBookstore(BookStore bookStore) {
		bookstoreRepository.create(bookStore);

	}

	@Override
	public BookStore findByBookstore(long id) {
	
		return bookstoreRepository.findByBookstore(id);
	}

	@Override
	public void deleteBookstore(long id) {
		
		bookstoreRepository.deleteBookstore(id);
		
	}
	
	@Override
	public Category updateCategory(Category category) {
		
		return categoryReposiyory.update(category);
	}

	@Override
	public Category findCategoryById(long id) {
	
		return categoryReposiyory.findCategoryById(id);
	}

	@Override
	public BookStore findByNameBookstore(String name) {
	
		return bookstoreRepository.findByName(name);
	}

	@Override
	public BookStore updateBookstoreToBook(BookStore bookStore,Book book) {
	
		return bookstoreRepository.update(bookStore,book);
	}
	

}
