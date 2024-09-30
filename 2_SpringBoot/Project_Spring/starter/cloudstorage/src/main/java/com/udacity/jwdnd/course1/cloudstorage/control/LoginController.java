package com.udacity.jwdnd.course1.cloudstorage.control;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Controller
@RequestMapping()
public class LoginController {
    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute("user", new User()); // Add an empty User object to the model
        model.addAttribute("loginSuccess", false);
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        String loginError = null;
        System.out.println(" Posting : "+ user.getUsername());

        try {
            // Attempt to authenticate the user
            Authentication authentication = authenticationService.authenticate(
                    new UsernamePasswordAuthenticationToken( user.getUsername(), user.getPassword())

            );
            System.out.println(" The Error is : "+ loginError);

            if (authentication.isAuthenticated()) {
                // Set the authenticated user in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println(" Success !!!");
                model.addAttribute("loginSuccess", true);
                return "redirect:/home";  // Redirect to the home page after successful login
            } else {
                loginError = "Invalid username or password.";
                System.out.println(" the error is : "+ loginError);
            }
        } catch (Exception e) {
            System.out.println(" Errorss is : "+ loginError);
            loginError = "An error occurred during login. Please try again.";
        }
        System.out.println(" Error is : "+ loginError);

        model.addAttribute("loginError", loginError);
        return "login";
    }
}
