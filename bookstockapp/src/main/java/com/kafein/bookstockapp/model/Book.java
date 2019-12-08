package com.kafein.bookstockapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "publisher")
	private String publisher;

	@Column(name = "edition_year")
	private int editionYear;

	@Column(name = "category")
	private String category;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "author_name")
	private Author author;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getEditionYear() {
		return editionYear;
	}

	public void setEditionYear(int editionYear) {
		this.editionYear = editionYear;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", publisher=" + publisher + ", editionYear=" + editionYear + ", category="
				+ category + ", author=" + author + "]";
	}

}
