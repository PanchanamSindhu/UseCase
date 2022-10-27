package com.cog.user.serviceimpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cog.user.model.Book;
import com.cog.user.model.ERole;
import com.cog.user.model.Role;
import com.cog.user.model.User;
import com.cog.user.repository.RoleRepository;
import com.cog.user.repository.UserRepository;
import com.cog.user.service.UserInterface;

@Service
public class UserServiceImpl implements UserInterface {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User getUser(int userId, ERole roleUser) {
		User user = null;
		Role userRole = roleRepository.findByName(roleUser)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()
				&& userOptional.get().getUserRole().stream().anyMatch(r -> r.getName().equals(userRole.getName()))) {
			user = userOptional.get();
		}
		return user;
	}

	@Override
	public List<Book> searchBooks(String title, String category, String author) {

		@SuppressWarnings("unchecked")
		List<Book> response=restTemplate.getForObject("http://localhost:8082/api/v1/digitalbooks/search", List.class, title, category,
				author);
		return response;

	}

}
