package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private UserController userController;

    private final UserRepository userRepo = mock(UserRepository.class);

    private final CartRepository cartRepo = mock(CartRepository.class);

    private final BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

    @Before
    public void setUp() {
        userController = new UserController(userRepo, cartRepo, encoder,null);
        TestUtils.injectObjects(userController, "userRepository", userRepo);
        TestUtils.injectObjects(userController, "cartRepository", cartRepo);
        TestUtils.injectObjects(userController, "bCryptPasswordEncoder", encoder);
    }
    @Test
    public void createUserHappyPath() {
        when(encoder.encode("Admin!123")).thenReturn("SaltedHash");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername("Karim");
        r.setPassword("Admin!123");
        r.setConfirmedPassword("Admin!123");
        final ResponseEntity<User> response = userController.createUser(r);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        User u = response.getBody();
        assertNotNull(u);
        assertEquals(0, u.getId());
        assertEquals("Karim", u.getUsername());
        assertEquals("SaltedHash", u.getPassword());
    }
    @Test
    public void createUserWrongPassword() {
        when(encoder.encode("Admin!125")).thenReturn("SaltedHash");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername("Karim");
        r.setPassword("Admin!123");
        r.setConfirmedPassword("Admin!123");
        final ResponseEntity<User> response = userController.createUser(r);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        User u = response.getBody();
        assertNotNull(u);
        assertEquals(0, u.getId());
        assertEquals("Karim", u.getUsername());
        assertNotEquals("SaltedHash", u.getPassword());
    }
    @Test
    public void createUserPassNotEqualConfirmPass() {
        when(encoder.encode("Admin!123")).thenReturn("SaltedHash");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername("Karim");
        r.setPassword("Admin!123");
        r.setConfirmedPassword("Admin!124");
        final ResponseEntity<User> response = userController.createUser(r);
        assertNotNull(response);
        //Response 400, as the password not equal the confirm password
        assertEquals(400, response.getStatusCodeValue());
    }
    @Test
    public void createUserPassViolateTheRules() {
        when(encoder.encode("Admin!123")).thenReturn("SaltedHash");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername("Karim");
        r.setPassword("123");
        r.setConfirmedPassword("123");
        final ResponseEntity<User> response = userController.createUser(r);
        assertNotNull(response);
        //Response 400, as the password not equal the confirm password
        assertEquals(400, response.getStatusCodeValue());
    }
    @Test
    public void testFindById(){
        when(encoder.encode("Admin!123")).thenReturn("SaltedHash");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername("Karim");
        r.setPassword("Admin!123");
        r.setConfirmedPassword("Admin!123");
        final ResponseEntity<User> response = userController.createUser(r);
        User user = response.getBody();
        when(userRepo.findById(0L)).thenReturn(java.util.Optional.ofNullable(user));

        final ResponseEntity<User> userResponseEntity = userController.findById(0L);

        User u = userResponseEntity.getBody();
        assertNotNull(u);
        assertEquals(0, u.getId());
        assertEquals("Karim", u.getUsername());
        assertEquals("SaltedHash", u.getPassword());
    }

    @Test
    public void testFindByUserName(){
        when(encoder.encode("Admin!123")).thenReturn("SaltedHash");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername("Karim");
        r.setPassword("Admin!123");
        r.setConfirmedPassword("Admin!123");
        final ResponseEntity<User> response = userController.createUser(r);
        User user = response.getBody();
        when(userRepo.findByUsername("Karim")).thenReturn(user);

        final ResponseEntity<User> userResponseEntity = userController.findByUserName("Karim");

        User u = userResponseEntity.getBody();
        assertNotNull(u);
        assertEquals(0, u.getId());
        assertEquals("Karim", u.getUsername());
        assertEquals("SaltedHash", u.getPassword());
    }
}
