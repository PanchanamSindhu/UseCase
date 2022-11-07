package com.cog.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cog.user.model.Book;
import com.cog.user.serviceimpl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1/author")
@Slf4j
@CrossOrigin("http://localhost:4200")
public class AuthorController {
	
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PreAuthorize("hasRole('AUTHOR')")
	@GetMapping("/home")
	public String home() {
		return "Home of Author";
	}
	

	
	

}
