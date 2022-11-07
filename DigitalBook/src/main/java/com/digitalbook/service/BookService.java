package com.digitalbook.service;

import java.math.BigDecimal;
import java.util.List;

import com.digitalbook.entity.Book;

/**
 * 
 * @author sindhu
 * This is BookService interface which used for defining book details methods
 *
 */
public interface BookService {
	public Book saveBook(Book book,Integer AuthorId);

	public Book getBook(String title);

	public List<Book> searchBooks(String title, String category, String author);

	public Book bookUpdate(Book book, Integer authorId, Integer bookId);

}
