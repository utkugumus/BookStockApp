package com.kafein.bookstockapp.dao;

import java.util.List;

import com.kafein.bookstockapp.model.Book;

public interface BookRepository {

	public void createBook(Book book);
	
	public Book findByName(String name);

	public List<Book> find(String name);
	
	public List<Book> findAll();
 
}
