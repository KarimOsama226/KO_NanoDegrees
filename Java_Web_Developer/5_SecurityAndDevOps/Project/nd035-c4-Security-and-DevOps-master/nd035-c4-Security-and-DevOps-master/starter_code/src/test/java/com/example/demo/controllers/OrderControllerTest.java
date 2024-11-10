package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    private OrderController orderController;
    private final UserRepository userRepo = mock(UserRepository.class);
    private final OrderRepository orderRepo = mock(OrderRepository.class);

    @Before
    public void setUp(){
        orderController = new OrderController(userRepo, orderRepo);
        TestUtils.injectObjects(orderController, "userRepository", userRepo);
        TestUtils.injectObjects(orderController, "orderRepository", orderRepo);
    }
    @Test
    public void testgetOrdersForUser(){
        User user =new User();
        user.setUsername("Karim");
        Item item=new Item(0L, "Whey", new BigDecimal("6.0"), "Isolate");
        Cart cart=new Cart();
        cart.setId(0L);
        List<Item> itemList=new ArrayList<>(); itemList.add(item);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("6.0")); cart.setUser(user); user.setCart(cart);
        when(userRepo.findByUsername("Karim")).thenReturn(user);
        orderController.submit("Karim");
        ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser("Karim");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        List<UserOrder> obtainedOrders = response.getBody();
        assertNotNull(obtainedOrders);

    }

    @Test
    public void testSubmit(){
        User user =new User();
        user.setUsername("Karim");
        Item item=new Item(0L, "Creatine", new BigDecimal("5.5"), "Monohydrate");
        Cart cart=new Cart();
        cart.setId(0L);
        List<Item> itemList=new ArrayList<>(); itemList.add(item);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("5.5")); cart.setUser(user); user.setCart(cart);

        when(userRepo.findByUsername("Karim")).thenReturn(user);

        ResponseEntity<UserOrder> response=orderController.submit("Karim");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        UserOrder order = response.getBody();
        assertNotNull(order);
        User responsedUser = order.getUser();
        assertEquals(user, responsedUser);
        assertEquals(itemList, order.getItems());
        assertEquals(new BigDecimal("5.5"), order.getTotal());
    }
}
