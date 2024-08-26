package com.udacity.jwdnd.course1.cloudstorage.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Home")
public class HomeController {

    @GetMapping()
    public String loginView() {
        return "Home";
    }
}