package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.service.authenticationServices;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    public static final String _userName = "DaVal";
    private UserController userController;

    private final UserRepository userRepo = mock(UserRepository.class);

    private final CartRepository cartRepo = mock(CartRepository.class);

    private final authenticationServices encoder = mock(authenticationServices.class);

    @Before
    public void setUp() {
        userController = new UserController(userRepo, encoder,cartRepo);
        TestUtils.injectObjects(userController, "userRepository", userRepo);
        TestUtils.injectObjects(userController, "cartRepository", cartRepo);
        TestUtils.injectObjects(userController, "authenticationServices", encoder);
    }

    @Test
    public void createUserHappyPath() {
//      when(encoder.encode("123qweasd")).thenReturn("thisIsHashed");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername(_userName);
        r.setPassword("123qweasd");
        r.setConfirmedPassword("123qweasd");
        final ResponseEntity<User> response = userController.createUser(r);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        User u = response.getBody();
        assertNotNull(u);
//        assertEquals(0, u.getId());
//        assertEquals(_userName, u.getUsername());
//        assertEquals("thisIsHashed", u.getPassword());
    }

    @Test
    public void testFindById(){
 //       when(encoder.encode("123qweasd")).thenReturn("thisIsHashed");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername(_userName);
        r.setPassword("123qweasd");
        r.setConfirmedPassword("123qweasd");
        final ResponseEntity<User> response = userController.createUser(r);
        User user = response.getBody();
        when(userRepo.findById(0L)).thenReturn(java.util.Optional.ofNullable(user));

        final ResponseEntity<User> userResponseEntity = userController.findById(0L);

        User u = userResponseEntity.getBody();
        assertNotNull(u);
//        assertEquals(0, u.getId());
//        assertEquals(_userName, u.getUsername());
//        assertEquals("thisIsHashed", u.getPassword());
    }

    @Test
    public void testFindByUserName(){
//        when(encoder.encode("123qweasd")).thenReturn("thisIsHashed");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername(_userName);
        r.setPassword("123qweasd");
        r.setConfirmedPassword("123qweasd");
        final ResponseEntity<User> response = userController.createUser(r);
        User user = response.getBody();
        when(userRepo.findByUsername(_userName)).thenReturn(user);

        final ResponseEntity<User> userResponseEntity = userController.findByUserName(_userName);

        User u = userResponseEntity.getBody();
        assertNotNull(u);
//        assertEquals(0, u.getId());
//        assertEquals(_userName, u.getUsername());
//        assertEquals("thisIsHashed", u.getPassword());
    }
}
