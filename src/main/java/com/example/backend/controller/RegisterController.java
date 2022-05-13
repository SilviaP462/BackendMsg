package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", originPatterns = "*", allowedHeaders = "*")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> register(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        return new ResponseEntity<>(registerService.register(firstName, lastName,username, password), HttpStatus.OK);
    }



}
