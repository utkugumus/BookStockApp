package com.kafein.bookstockapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kafein.bookstockapp.dao.BookRepository;
import com.kafein.bookstockapp.exception.BookNotFoundException;
import com.kafein.bookstockapp.model.Book;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public void addBook(Book book) {
		bookRepository.createBook(book);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Book> findBooks(String name)  throws BookNotFoundException{
		List<Book> books = bookRepository.find(name);
		
		if (books.size() == 0) {
			throw new BookNotFoundException("Book Not Found!");
		} else {
			return books;
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Book> findBooks() {
		return bookRepository.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Book findBook(String name) throws BookNotFoundException {
		Book book = bookRepository.findByName(name);

		if (book == null) {
			throw new BookNotFoundException("Book Not Found!");
		} else {
			return book;
		}
	}

}
