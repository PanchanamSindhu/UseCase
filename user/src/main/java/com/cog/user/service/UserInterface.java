package com.cog.user.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cog.user.model.Book;
import com.cog.user.model.ERole;
import com.cog.user.model.User;

public interface UserInterface {
	
	User getUser(int userId, ERole roleUser);

	List<Book> searchBooks(String title, String category, String author);

}
