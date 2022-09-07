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
	    public ResponseEntity<String> loginUser(@Valid @RequestBody User user) {
	        List<User> users = userRepository.findAll();

	        for (User other : users) {
	            if (other.getUsername().equals(user.getUsername()) && other.getPassword().equals(user.getPassword())) {
	            	//user.setId(other.getId());
	                other.setLoggedIn(true);
	                userRepository.save(other);
	                return new ResponseEntity<>(HttpStatus.OK);
	            }
	        }

	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }

	@PostMapping("/users/logout")
	public ResponseEntity<String> logUserOut(@Valid @RequestBody User user) {
		List<User> users = userRepository.findAll();

		for (User other : users) {
			if (other.getUsername().equals(user.getUsername()) && other.getPassword().equals(user.getPassword())) {
				other.setLoggedIn(false);
				userRepository.save(other);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
