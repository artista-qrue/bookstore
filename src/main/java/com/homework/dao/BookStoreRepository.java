package com.homework.dao;

import java.util.List;

import com.homework.model.Book;
import com.homework.model.BookStore;

public interface BookStoreRepository {

	//Tüm bookstore'ları döndürür
	List<BookStore> getAllBookStore();

	//yeni bir bookstore yaratımı
	void create(BookStore bookStore);

	//mağazaya göre fiyat değiştirme.
	Book chancePriceForBook(String book);
	
	//id'ye göre bookstore bulma
	BookStore findByBookstore(long id );
	
	//verilen id'de bookstore silme
	void deleteBookstore(long id);
	
	//verilen isimde bookstore bulma
	BookStore findByName(String name);

	//verilen değişkenlere göre bookstore update etme
	BookStore update(BookStore bookStore, Book book);
	

	

}
