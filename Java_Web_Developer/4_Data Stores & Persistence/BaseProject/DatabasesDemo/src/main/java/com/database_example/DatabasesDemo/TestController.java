package com.database_example.DatabasesDemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test(){
        return "Data Structures and Persistence are pretty good.";
    }
}