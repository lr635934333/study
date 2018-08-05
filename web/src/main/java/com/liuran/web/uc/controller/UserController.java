package com.liuran.web.uc.controller;

import com.liuran.web.uc.domain.User;
import com.liuran.web.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v0.1/user")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }
}
