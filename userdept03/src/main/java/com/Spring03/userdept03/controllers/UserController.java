package com.Spring03.userdept03.controllers;


import com.Spring03.userdept03.entities.User;
import com.Spring03.userdept03.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired

    private UserRepository repository;

    @GetMapping
    public List<User>findAll(){
        List<User> result = repository.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public  User findById(@PathVariable Long id){
        User resul = repository.findById(id).get();
        return resul;
    }

    @PostMapping
    public User insert(@RequestBody User user){
        User result = repository.save(user);
        return result;
    }

}
