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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cog.user.model.Book;
import com.cog.user.serviceimpl.UserServiceImpl;


@RestController("/api/v1/user")
public class UserController {
	
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/search")
	@PreAuthorize("hasRole('READER')")
	public ResponseEntity<List<Book>> searchBooks(@RequestParam("title")  String bookTitle,
			@RequestParam("category") String bookCategory,
			@RequestParam("author") String bookAuthor) {
		ResponseEntity<List<Book>> response;
		List<Book> listOfBooks =  userServiceImpl.searchBooks(bookTitle, bookCategory, bookAuthor);
		response = new ResponseEntity<>(listOfBooks, HttpStatus.OK);
		return response;
	}
	
	

}
