package com.cog.user.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cog.user.model.ERole;
import com.cog.user.model.MessageRespone;
import com.cog.user.model.Role;
import com.cog.user.model.SignUpRequest;
import com.cog.user.model.User;
import com.cog.user.repository.RoleRepository;
import com.cog.user.repository.UserRepository;

@RestController
@RequestMapping("/reg")
public class RegistrationController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (Boolean.TRUE.equals(userRepository.existsByUserName(signUpRequest.getUserName()))) {
			return ResponseEntity.badRequest().body(new MessageRespone("Error: Username is already taken!"));
		}

		if (Boolean.TRUE.equals(userRepository.existsByEmailId(signUpRequest.getEmailId()))) {
			return ResponseEntity.badRequest().body(new MessageRespone("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUserName(), signUpRequest.getEmailId(),
				passwordEncoder.encode(signUpRequest.getPassword()));

		//System.out.println("signup reg "+signUpRequest.toString());
		Set<Role> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_READER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				if (role.getName().equals(ERole.ROLE_AUTHOR)) {
					Role authorRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(authorRole);
				} else {
					Role readerRole = roleRepository.findByName(ERole.ROLE_READER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(readerRole);
				}
			});
		}

		user.setUserRole(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageRespone("User registered successfully!"));
	}
}
