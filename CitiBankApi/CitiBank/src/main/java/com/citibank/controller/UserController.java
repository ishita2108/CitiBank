package com.citibank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citibank.repository.UserRepository;
import com.citibank.user.Status;
import com.citibank.user.User;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/users/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User newUser) {
		List<User> users = userRepository.findAll();

		System.out.println("New user: " + newUser.toString());

		for (User user : users) {
			System.out.println("Registered user: " + newUser.toString());

			if (user.getUsername().equals(newUser.getUsername())) {
				System.out.println("User Already exists!");
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		}

		userRepository.save(newUser);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@PostMapping("/users/login")
	public ResponseEntity<User> loginUser(@Valid @RequestBody User user) {
		List<User> users = userRepository.findAll();

		for (User other : users) {
			if (other.equals(user)) {
				user.setLoggedIn(true);
				userRepository.save(user);
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(user, HttpStatus.FORBIDDEN);
	}

	@PostMapping("/users/logout")
	public Status logUserOut(@Valid @RequestBody User user) {
		List<User> users = userRepository.findAll();

		for (User other : users) {
			if (other.equals(user)) {
				user.setLoggedIn(false);
				userRepository.save(user);
				return Status.SUCCESS;
			}
		}

		return Status.FAILURE;
	}
}
