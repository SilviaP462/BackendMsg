package com.example.backend.controller;

import com.example.backend.error.UserPasswordIncorrectException;
import com.example.backend.model.User;
import com.example.backend.security.AuthenticationResponse;
import com.example.backend.security.JwtUtil;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.example.backend.service.LoginService;

@RestController
@CrossOrigin(origins = "*", originPatterns = "*", allowedHeaders = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

 /*   @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> login(@RequestParam("user") String username, @RequestParam("password") String pwd) throws Exception {
        return new ResponseEntity<>(loginService.login(username, pwd), HttpStatus.OK);
    }*/

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestParam("user") String username, @RequestParam("password") String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (BadCredentialsException e) {
            throw new UserPasswordIncorrectException("User or password incorrect!");
        }


            User foundUser = userService.findUserByUsername(username);

            final String jwt = jwtTokenUtil.generateToken(foundUser);

            foundUser.setToken(jwt);
            //User userDto = loginService.updateUserToken(foundUser);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
