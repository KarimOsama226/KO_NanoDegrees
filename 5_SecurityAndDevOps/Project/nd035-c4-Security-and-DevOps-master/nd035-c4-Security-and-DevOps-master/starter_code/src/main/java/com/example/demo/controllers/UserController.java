package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.JwtUtil;


@RestController
@RequestMapping("/api/user")
public class UserController {

    final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository;
    private CartRepository cartRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationService authenticationService;


    public UserController(UserRepository userRepository, CartRepository cartRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        logger.debug("Attempt to get User ID {}.", id);
        return ResponseEntity.of(userRepository.findById(id));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        logger.debug("Getting Username {}.", username);
        if (user == null) {
            logger.error("User not found!");
            return ResponseEntity.notFound().build();
        } else {
            logger.debug("User Found!");
            return ResponseEntity.ok(user);

        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        System.out.println("12Password is : " + createUserRequest.getPassword());
        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        logger.debug("Password: {}", createUserRequest.getPassword());
        System.out.println("Password is : " + createUserRequest.getPassword());
        authenticationService.hashPassword(createUserRequest.getPassword(),user);
        Cart cart = new Cart();
        cartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping ("/login")
    public ResponseEntity<String> login(@RequestBody CreateUserRequest createUserRequest) {
        User user = userRepository.findByUsername(createUserRequest.getUsername());
        if (user == null || !authenticationService.matchPassword(user,createUserRequest.getPassword())) {
            logger.error("Invalid Username or Password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        String token = JwtUtil.generateToken(user.getUsername());
        logger.debug("Logged in !, Token: {}",token);
        return ResponseEntity.ok(token);
    }
}

