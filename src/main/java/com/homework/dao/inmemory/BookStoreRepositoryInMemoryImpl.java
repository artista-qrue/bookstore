package com.homework.dao.inmemory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.homework.dao.BookStoreRepository;
import com.homework.model.Book;
import com.homework.model.BookStore;

@Repository
public class BookStoreRepositoryInMemoryImpl implements BookStoreRepository {

	public BookRepositoryInMemoryImpl inMemoryImpl = new BookRepositoryInMemoryImpl();
	private Map<Long, BookStore> bookStoreMap = new HashMap<>();

	public BookStoreRepositoryInMemoryImpl() {
		BookStore bookStore = new BookStore();
		bookStore.setName("Ankara");
		bookStore.setId(1L);

		List<Book> books = new ArrayList<Book>();
		books.add(inMemoryImpl.getABook("Danzo"));
		books.add(inMemoryImpl.getABook("Great planet"));

		bookStore.setBooks(books);

		BookStore bookStore2 = new BookStore();
		bookStore2.setName("Adana");
		bookStore2.setId(2L);

		List<Book> books2 = new ArrayList<Book>();
		books2.add(inMemoryImpl.getABook("Danzo"));
		books2.add(inMemoryImpl.getABook("Danzo"));
		books2.add(inMemoryImpl.getABook("Great planet"));
		books2.add(inMemoryImpl.getABook("Great planet"));
		bookStore2.setBooks(books2);

		bookStoreMap.put(bookStore.getId(), bookStore);
		bookStoreMap.put(bookStore2.getId(), bookStore2);

	}

	public void addBookToBookstore(Book book) {

	}

	@Override
	public List<BookStore> getAllBookStore() {

		return new ArrayList<BookStore>(bookStoreMap.values());
	}

	@Override
	public void create(BookStore bookStore) {

		bookStore.setId(new Date().getTime());
		bookStoreMap.put(bookStore.getId(), bookStore);

	}

	@Override
	public Book chancePriceForBook(String book) {

		return null;

	}

	@Override
	public BookStore findByBookstore(long id) {

		return bookStoreMap.get(id);
	}

	@Override
	public void deleteBookstore(long id) {

		bookStoreMap.remove(id);

	}

	@Override
	public BookStore findByName(String name) {

		return bookStoreMap.values().stream().filter(o -> o.getName().equals(name)).findAny().orElse(null);

	}

	@Override
	public BookStore update(BookStore bookStore,Book book) {
		
		List<Book>  books = bookStore.getBooks();
		books.add(book);
		bookStore.setBooks(books);
		bookStoreMap.replace(bookStore.getId(), bookStore);
		return bookStoreMap.get(bookStore.getId());
	}

}
