package com.liuran.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BaseController {
    @GetMapping
    public String get(){
        return "home page";
    }

    @PostMapping
    public String post(){
        return "home page";
    }
}
