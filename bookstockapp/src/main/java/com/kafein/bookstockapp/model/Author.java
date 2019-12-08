package com.kafein.bookstockapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "author")
public class Author {

	@Id
	@Column(name = "fullName")
	private String fullName;

	@JsonIgnore
	@OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
	private Set<Book> books;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [fullName=" + fullName + "]";
	}

}
