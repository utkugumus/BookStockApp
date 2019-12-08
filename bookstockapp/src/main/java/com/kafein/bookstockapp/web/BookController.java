package com.kafein.bookstockapp.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kafein.bookstockapp.exception.BookNotFoundException;
import com.kafein.bookstockapp.model.Book;
import com.kafein.bookstockapp.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@RequestMapping(method = RequestMethod.GET, value = "/book/{name}")
	public ResponseEntity<Book> getBook(@PathVariable("name") String name) {
		try {
			return ResponseEntity.ok(bookService.findBook(name));
		} catch (BookNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/books")
	public ResponseEntity<List<Book>> getBooks() {
		try {
			return ResponseEntity.ok(bookService.findBooks());
		} catch (BookNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findBook")
	public ResponseEntity<?> findBooks(@RequestParam("name") String name) {
		try {
			return ResponseEntity.ok(bookService.findBooks(name));
		} catch (BookNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/book")
	public ResponseEntity<URI> addBook(@RequestBody Book book) {
		try {
			bookService.addBook(book);
			String name = book.getName();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/findBook?name=" + name).buildAndExpand(name)
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
