package com.kafein.bookstockapp.dao;

import java.util.List;

import com.kafein.bookstockapp.model.Author;

public interface AuthorRepository {

	public Author findByFullName(String fullName);

	public void create(Author author);

	public List<Author> findAll();

	public void delete(String fullName);

}