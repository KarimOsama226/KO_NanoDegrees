package com.udacity.jwdnd.course1.cloudstorage.security;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**")
                .permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")  // Redirects to log in with error parameter on failure
                .permitAll();
        http.formLogin()
                .defaultSuccessUrl("/home", true);
        http.logout()
                .logoutUrl("/logout")  // Specify the path to the logout URL
                .logoutSuccessUrl("/login")  // Redirect to the login page with a logout message
                .permitAll();  // Allow everyone to log out
        http.csrf().disable(); // For Testing why Post is not propagated to the App

    }
}