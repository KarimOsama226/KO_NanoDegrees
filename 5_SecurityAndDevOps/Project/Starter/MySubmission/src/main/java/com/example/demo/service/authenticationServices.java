package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.model.persistence.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import static ch.qos.logback.core.joran.action.ActionConst.NULL;
@Service
public class authenticationServices {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile("[!@#$%^&*()-+]");
    private static final Logger logger = LoggerFactory.getLogger(authenticationServices.class);

    public boolean isPasswordValid(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }
        Matcher hasUppercase = UPPERCASE_PATTERN.matcher(password);
        Matcher hasLowercase = LOWERCASE_PATTERN.matcher(password);
        Matcher hasDigit = DIGIT_PATTERN.matcher(password);
        Matcher hasSpecialChar = SPECIAL_CHARACTER_PATTERN.matcher(password);

        return hasUppercase.find() && hasLowercase.find() && hasDigit.find() && hasSpecialChar.find();
    }
    public void hashPassword (CharSequence rawPassword,User user)
    {
        String salt;

        if (!isPasswordValid(rawPassword.toString())) {
            logger.error("Exception :: Password is Not valid");
            throw new IllegalArgumentException("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }
        else
        {
            salt = BCrypt.gensalt();
            user.setSalt(salt);
            logger.info("Salt: {}", salt);
            logger.debug("rawPassword: {}", rawPassword);
        }
        String hashedPassword = BCrypt.hashpw(rawPassword.toString(),salt);
        logger.debug("hashedPassword: {}", hashedPassword);
        user.setSecuredPassword(hashedPassword);
    }
    public boolean matchPassword (User user, String rawPassword)
    {
        boolean result;
        result = BCrypt.checkpw(rawPassword,user.getSecuredPassword());
        return result;
    }

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
