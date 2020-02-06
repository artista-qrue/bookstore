package com.homework.web;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.homework.dao.CategoryRepository;
import com.homework.dao.inmemory.CategoryInMemoryImpl;
import com.homework.model.Book;
import com.homework.model.BookStore;
import com.homework.model.Category;
import com.homework.model.Price;
import com.homework.services.BookStoreServices;

@RestController
@RequestMapping("/rest")
public class BookStoreRestController {

	@Autowired
	private BookStoreServices bookStoreServices;

	@Autowired
	private CategoryRepository category = new CategoryInMemoryImpl();

	@RequestMapping(method = RequestMethod.POST, value = "/book")
	public ResponseEntity<URI> createBook(@RequestBody Book book) {
		try {
			bookStoreServices.createBook(book);

			long id = book.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/category")
	public ResponseEntity<URI> createCategory(@RequestBody Category category) {
		try {
			bookStoreServices.createCategory(category);

			long id = category.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/bookstore")
	public ResponseEntity<URI> createBookstore(@RequestBody BookStore bookStore) {
		try {
			bookStoreServices.createBookstore(bookStore);

			long id = bookStore.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/bookstores")
	public ResponseEntity<List<BookStore>> getBookStores() {
		List<BookStore> bookstores = bookStoreServices.getAllBookstores();
		return ResponseEntity.ok(bookstores);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = bookStoreServices.getAllBooks();
		return ResponseEntity.ok(books);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/categories")
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> categories = bookStoreServices.getAllCategories();
		return ResponseEntity.ok(categories);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/bookstore/{id}")
	public ResponseEntity<?> deleteBookstore(@PathVariable("id") long id) {
		try {
			bookStoreServices.deleteBookstore(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/category/id")
	public ResponseEntity<?> updateCategory(@PathVariable("id")long id,@RequestBody Category categoryRequest ){
		
		try {
			Category category =bookStoreServices.findCategoryById(id);
			
			category.setName("Macera");
			bookStoreServices.updateCategory(category);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/bookstores")
	public ResponseEntity<?> addAbookToBookstore(@RequestParam("bsn") String bookstoreName){
		
		
		try {
			BookStore bookStore =bookStoreServices.findByNameBookstore(bookstoreName);
			Book book = new Book();
			book.setName("The Whell of Time");
			book.setId(new Date().getTime());
			book.setPrice(new Price(5));
			book.setCategory(category.getCategory("Fantasy"));
			bookStoreServices.updateBookstoreToBook(bookStore,book);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
