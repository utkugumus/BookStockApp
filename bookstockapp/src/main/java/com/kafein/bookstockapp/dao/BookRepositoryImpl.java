package com.kafein.bookstockapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kafein.bookstockapp.model.Author;
import com.kafein.bookstockapp.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	AuthorRepository authorRepository;

	@Override
	public void createBook(Book book) {
		Author author = authorRepository.findByFullName(book.getAuthor().getFullName());

		if (author != null) {
			book.setAuthor(author);
			entityManager.persist(book);
		} else {
			entityManager.persist(book);
		}
	}

	@Override
	public List<Book> find(String name) {
		return entityManager.createQuery("from Book where name like '%" + name + "%'", Book.class).getResultList();
	}

	@Override
	public List<Book> findAll() {
		return entityManager.createQuery("from Book", Book.class).getResultList();
	}

	@Override
	public Book findByName(String name) {
		return entityManager.find(Book.class, name);
	}

}
