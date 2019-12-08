package com.kafein.bookstockapp.service;

import java.util.List;

import com.kafein.bookstockapp.exception.AuthorNotFoundException;
import com.kafein.bookstockapp.model.Author;

public interface AuthorService {

	public Author findAuthor(String fullName) throws AuthorNotFoundException;

	public List<Author> findAuthors();

	public void addAuthor(Author author);

	public void deleteAuthor(String fullName) throws AuthorNotFoundException;

}
