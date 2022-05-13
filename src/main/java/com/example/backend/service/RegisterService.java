package com.example.backend.service;

import com.example.backend.error.UserPasswordIncorrectException;
import com.example.backend.error.UsernameAlreadyExistsException;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User register(String firstName, String lastName,String username, String password){
        if(firstName==null || password==null || lastName==null || username==null)
        return null;

        if(userRepository.findByUserName(username)!=null)
            throw new UsernameAlreadyExistsException("Username already exists!");

        else {
            User user =new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setPassword(encoder.encode(password));
            return userRepository.save(user);
        }

    }

}
