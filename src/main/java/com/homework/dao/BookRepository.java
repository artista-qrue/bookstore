package com.homework.dao;

import java.util.List;

import com.homework.model.Book;

public interface BookRepository {

	List<Book> getAllBooks(); //tüm kitapları dönderir.

	void create(Book book); //bir kitap yaratmak için

	Book getABook(String name); //istenilen isimde bir kitap dönderir.

	
}
