package com.digitalbook.controller;

import java.math.BigDecimal;
import java.util.List;

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
import com.digitalbook.service.BookService;

/**
 * 
 * @author sindhu This is BookController which run methods for book api
 */

@RestController
@RequestMapping("/api/v1/digitalbooks")
public class BookController {

	@Autowired
	BookService bookService;

//	@PostMapping("/savebook/{authorId}/books")
//	public ResponseEntity<String> saveBook(@PathVariable("authorId") int authorId, @RequestBody Book book) {
//		ResponseEntity<Integer> response;
//		BookAuthor bookAuthor = new BookAuthor();
//		
//		
//		Book bookData = bookService.getBook(book.getTitle());
//		return null;
//	}

	@GetMapping("/search")
	public List<Book> searchBooks(@RequestParam("title")  String bookTitle,
			@RequestParam("category") String bookCategory,
			@RequestParam("author") String bookAuthor) {
		ResponseEntity<List<Book>> response;
		System.out.println("title: "+bookTitle);
		List<Book> listOfBooks = bookService.searchBooks(bookTitle, bookCategory, bookAuthor);
		// response = new ResponseEntity<>(listOfBooks, HttpStatus.OK);
		return listOfBooks;
	}

}
