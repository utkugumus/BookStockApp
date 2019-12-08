package com.kafein.bookstockapp.service;

import java.util.List;

import com.kafein.bookstockapp.exception.BookNotFoundException;
import com.kafein.bookstockapp.model.Book;

public interface BookService {

	public void addBook(Book book);
	
	public Book findBook(String name) throws BookNotFoundException;
	
	public List<Book> findBooks(String name) throws BookNotFoundException;

	
	public List<Book> findBooks();
	
}
