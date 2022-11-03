package com.cog.user.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cog.user.model.Book;
import com.cog.user.model.ERole;
import com.cog.user.model.User;
import com.cog.user.serviceimpl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
	
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PreAuthorize("hasRole('READER')")
	@GetMapping("/search")
	public ResponseEntity<List<Book>> searchBooks(@RequestParam("title")  String bookTitle,
			@RequestParam("category") String bookCategory,
			@RequestParam("author") String bookAuthor) {
		ResponseEntity<List<Book>> response;
		log.info("title is :"+bookTitle);
		List<Book> listOfBooks =  userServiceImpl.searchBooks(bookTitle, bookCategory, bookAuthor);
		response = new ResponseEntity<>(listOfBooks, HttpStatus.OK);
		return response;
	}
	
	@PreAuthorize("hasRole('READER')")
	@GetMapping("/home")
	public String home() {
		return "Home";
	}
	

	@PostMapping("/author/{authorId}/books")
	@PreAuthorize("hasRole('AUTHOR')")
	public ResponseEntity<Integer> saveBook(@PathVariable("authorId") int authorId, @Valid @RequestBody Book book) {
		log.info("book data :"+book);
			Book book1 = userServiceImpl.saveBook(book,authorId);
			int bookId = book1.getId();
			return new ResponseEntity<>(bookId, HttpStatus.CREATED);
	}
	
	
	

}
