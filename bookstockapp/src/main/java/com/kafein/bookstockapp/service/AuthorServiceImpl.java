package com.kafein.bookstockapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kafein.bookstockapp.dao.AuthorRepository;
import com.kafein.bookstockapp.exception.AuthorNotFoundException;
import com.kafein.bookstockapp.model.Author;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Author findAuthor(String fullName) throws AuthorNotFoundException{
		Author author = authorRepository.findByFullName(fullName);
		
		if(author == null) {
			throw new AuthorNotFoundException("Author Not Found!");
		} else {
			return author;
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Author> findAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public void addAuthor(Author author) {
		authorRepository.create(author);
	}

	@Override
	public void deleteAuthor(String fullName) throws AuthorNotFoundException {	
		if (authorRepository.findByFullName(fullName) == null) {
			throw new AuthorNotFoundException("Author Not Found!");
		}else {
			authorRepository.delete(fullName);
		}
	}

}
