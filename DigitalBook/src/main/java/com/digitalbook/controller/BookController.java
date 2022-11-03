package com.digitalbook.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbook.entity.Book;
import com.digitalbook.entity.BookAuthor;
import com.digitalbook.entity.ERole;
import com.digitalbook.entity.User;
import com.digitalbook.service.BookService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author sindhu This is BookController which run methods for book api
 */

@RestController
@RequestMapping("/api/v1/digitalbooks")
@Slf4j
public class BookController {

	@Autowired
	BookService bookService;


	@GetMapping("/search")
	public List<Book> searchBooks(@RequestParam("title")  String bookTitle,
			@RequestParam("category") String bookCategory,
			@RequestParam("author") String bookAuthor) {
		
		
		List<Book> listOfBooks = bookService.searchBooks(bookTitle, bookCategory, bookAuthor);
		// response = new ResponseEntity<>(listOfBooks, HttpStatus.OK);
		return listOfBooks;
	}
	
	@PostMapping("/author/{authorId}/books")
	public Book saveBook(@PathVariable("authorId") int authorId, @Valid @RequestBody Book book) {
	
			Book book1 = bookService.saveBook(book);
		
		return book1;
		
	}
	
	

}
