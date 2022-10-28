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
		
		System.out.println("title: "+bookTitle);
		List<Book> listOfBooks = bookService.searchBooks(bookTitle, bookCategory, bookAuthor);
		// response = new ResponseEntity<>(listOfBooks, HttpStatus.OK);
		return listOfBooks;
	}
	
	@PostMapping("/author/{authorId}/books")
	public Integer saveBook(@PathVariable("authorId") int authorId, @Valid @RequestBody Book book) {
		
		BookAuthor bookAuthor = new BookAuthor();
		Book book1 = bookService.getBook(book.getTitle());
		if (book1 == null) {
			int bookId = 0;
			User user = userService.getUser(authorId, ERole.ROLE_AUTHOR);
			book.setAuthorName(user.getName());
			book.setAuthorUserName(user.getUserName());
			book = bookService.saveBook(book);
			bookId = book.getId();
			return bookId
			bookAuthor.setBook(book);
			bookAuthor.setEmailId(user.getEmailId());
		} else {
			response = new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return response;
	}
	
	

}
