package com.example.demo.controllers;

import java.util.Optional;

import com.example.demo.service.JwtUtil;
import com.example.demo.service.authenticationServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private authenticationServices authServices;

	@Autowired
	private CartRepository cartRepository;

	public UserController(UserRepository userRepository, authenticationServices authServices, CartRepository cartRepository) {
		this.userRepository = userRepository;
		this.authServices = authServices;
		this.cartRepository = cartRepository;
	}


	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		return ResponseEntity.of(userRepository.findById(id));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		logger.error("UserName is:{}",username);

		return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
		System.out.println("12Password is : " + createUserRequest.getPassword());
		User user = new User();
		user.setUsername(createUserRequest.getUsername());
		logger.debug("Password: {}", createUserRequest.getPassword());
		System.out.println("Password is : " + createUserRequest.getPassword());
		authServices.hashPassword(createUserRequest.getPassword(),user);
		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}
	@PostMapping ("/login")
	public ResponseEntity<String> login(@RequestBody CreateUserRequest createUserRequest) {
		User user = userRepository.findByUsername(createUserRequest.getUsername());
		if (user == null || !authServices.matchPassword(user,createUserRequest.getPassword())) {
			logger.error("Invalid Username or Password");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}

		String token = JwtUtil.generateToken(user.getUsername());
		logger.debug("Logged in !, Token: {}",token);
		return ResponseEntity.ok(token);
	}
	
}
