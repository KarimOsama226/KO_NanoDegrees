package com.example.demo.controllers;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    public static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private UserRepository userRepository;

    private OrderRepository orderRepository;


    public OrderController(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/submit/{username}")
    public ResponseEntity<UserOrder> submit(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("Cannot find {}!", username);
            return ResponseEntity.notFound().build();
        }
        UserOrder order = UserOrder.createFromCart(user.getCart());
        orderRepository.save(order);
        log.info("The Order has been added successfully.");
        log.debug("with items: {}", order.getItems());
        return ResponseEntity.ok(order);
    }

    @GetMapping("/history/{username}")
    public ResponseEntity<List<UserOrder>> getOrdersForUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("Cannot find user: {}!", username);
            return ResponseEntity.notFound().build();
        }
        log.info("History Found!.");
        log.debug("Cart is: {} ", user.getCart());
        return ResponseEntity.ok(orderRepository.findByUser(user));
    }
}
