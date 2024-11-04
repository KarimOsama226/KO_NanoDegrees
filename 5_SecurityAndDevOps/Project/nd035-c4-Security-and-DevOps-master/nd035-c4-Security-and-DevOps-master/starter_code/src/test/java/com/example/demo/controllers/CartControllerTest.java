package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
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

public class CartControllerTest {
        private CartController cartController;
    private UserRepository userRepository = mock(UserRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);
    private ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp () {
        cartController = new CartController(userRepository, cartRepository, itemRepository);
        TestUtils.injectObjects(cartController, "userRepository", userRepository);
        TestUtils.injectObjects(cartController, "cartRepository", cartRepository);
        TestUtils.injectObjects(cartController, "itemRepository", itemRepository);
    }

    @Test
    public void testAddToCart(){
        /* Creating a new User */
        User user =new User();
        //Set Username
        user.setUsername("Karim");
        //Create an Item, that's first index from the data.sql
        Item item=new Item(0L, "Round Widget", new BigDecimal("2.99"), "A widget is round");
        //Create a new Cart
        Cart cart=new Cart();
        //Set CartId to 0
        cart.setId(0L);
        //create a list of items to add to the cart
        List<Item> itemList=new ArrayList<>(); itemList.add(item);
        //Add the items to the Cart
        cart.setItems(itemList);
        //set the total to be 2.99 for user"Karim" with the cart id 0 created before
        cart.setTotal(new BigDecimal("2.99")); cart.setUser(user); user.setCart(cart);
        // Mock the User repo, to return the user when requesting to find the user with name "Karim"
        when(userRepository.findByUsername("Karim")).thenReturn(user);
        // Mock the Item repo to return the item created before when we request to find Item with ID = 0
        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(item));
        // Create a new Cart Request
        ModifyCartRequest request=new ModifyCartRequest();
        //The request with Item ID = 0
        request.setItemId(0L);
        //The request with Item Quantity = 1
        request.setQuantity(1);
        //The request with UserName = Karim
        request.setUsername("Karim");
        // Then take the response from the unmocked controller
        ResponseEntity<Cart> response = cartController.addTocart(request);
        // Make sure the response is not null
        assertNotNull(response);
        //Make sure the value is 200 for the response status code
        assertEquals(200,response.getStatusCodeValue());
        // get the Whole body of the response
        Cart cartAdded=response.getBody();
        // make sure it is not null
        assertNotNull(cartAdded);
        // Make sure the id of the Cart is 0 as set already
        assertEquals(0L, cartAdded.getId());
        // Get the Items
        List<Item> items=cartAdded.getItems();
        //Make sure it is not null
        assertNotNull(items);
        //Make sure it has 2 items
        assertEquals(2,items.size());
        // Chech the first item
        Item item1=items.get(0);
        // Make sure it is not null
        assertNotNull(item1);
        // Make sure its total value is 5.98 (2.99+2.99)
        assertEquals(new BigDecimal("5.98"), cartAdded.getTotal());
        //Make sure the user of the cart, the one we make before
        assertEquals(user, cartAdded.getUser());
    }

    @Test
    public void testRemoveFromCart(){
        //Create a new User
        User user =new User();
        //Called Karim
        user.setUsername("Karim");
        // Adding this item initially
        Item item=new Item(0L, "Round Widget", new BigDecimal("2.99"), "A widget is round");
        // and create a new Cart to contain it
        Cart cart=new Cart();
        //then the id is 0
        cart.setId(0L);
        // Create a new list
        List<Item> itemList=new ArrayList<>(); itemList.add(item);
        // and the item is added to that cart
        cart.setItems(itemList);
        // Let the total now 2.99
        cart.setTotal(new BigDecimal("2.99")); cart.setUser(user); user.setCart(cart);
        // If a request to search for "Karim" is made, mock the user repo adn return the user we created before
        when(userRepository.findByUsername("Karim")).thenReturn(user);
        // then to get index 0 of the items, mock the repo  as well and return the item index 0
        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(item));
        // This is a new request to modify the cart
        ModifyCartRequest request=new ModifyCartRequest();
        // the request Id is 0
        request.setItemId(0L);
        // and the quantity is 1
        request.setQuantity(1);
        //for user "Karim"
        request.setUsername("Karim");
        //and the request is to remove that item from Cart
        ResponseEntity<Cart> response = cartController.removeFromcart(request);
        // Make sure the response is not null
        assertNotNull(response);
        // and equals 200
        assertEquals(200,response.getStatusCodeValue());
        // then get the body
        Cart cartAdded=response.getBody();
        // make sure it is not null
        assertNotNull(cartAdded);
        //Make sure this is our user
        assertEquals(user, cartAdded.getUser());
        // and the ID of the cart should be 0
        assertEquals(0L, cartAdded.getId());
        // then get the items to make sure it is deleted
        List<Item> items=cartAdded.getItems();
        //it should not be null
        assertNotNull(items);
        //but the list should  be empty
        assertEquals(0,items.size());
        // and the total is regard should be 0
        assertEquals(new BigDecimal("0.00"), cartAdded.getTotal());
    }

}
