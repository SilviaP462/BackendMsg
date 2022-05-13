package com.example.backend.service;

import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.backend.repository.UserRepository;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public List<User> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().toList();
    }

    public User findUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UsernameNotFoundException("User could not be found!");

        return user;
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException("User " + username + " could not be found!");

        return user;
    }





}
