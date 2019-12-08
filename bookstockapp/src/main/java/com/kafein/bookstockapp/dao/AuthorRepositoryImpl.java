package com.kafein.bookstockapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.kafein.bookstockapp.model.Author;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void create(Author author) {
		entityManager.persist(author);
	}

	@Override
	public List<Author> findAll() {
		return entityManager.createQuery("from Author", Author.class).getResultList();
	}

	@Override
	public void delete(String fullName) {
		entityManager.remove(entityManager.getReference(Author.class, fullName));
	}

	@Override
	public Author findByFullName(String fullName) {
		return entityManager.find(Author.class, fullName);
	}

}
