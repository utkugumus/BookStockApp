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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kafein.bookstockapp.exception.AuthorNotFoundException;
import com.kafein.bookstockapp.model.Author;
import com.kafein.bookstockapp.service.AuthorService;

@RestController
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@RequestMapping(method = RequestMethod.GET, value = "/author/{fullName}")
	public ResponseEntity<?> getAuthor(@PathVariable("fullName") String fullName) {
		try {
			return ResponseEntity.ok(authorService.findAuthor(fullName));
		} catch (AuthorNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/authors")
	public ResponseEntity<List<Author>> getAuthors() {
			return ResponseEntity.ok(authorService.findAuthors());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/author")
	public ResponseEntity<URI> createAuthor(@RequestBody Author author) {
		try {
			authorService.addAuthor(author);
			String fullName = author.getFullName();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{fullName}").buildAndExpand(fullName)
					.toUri();

			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/author/{fullName}")
	public ResponseEntity<?> deleteOwner(@PathVariable("fullName") String fullName) {
		try {
			authorService.deleteAuthor(fullName);
			return ResponseEntity.ok().build();
		} catch (AuthorNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
