package com.example.backend.service;

import com.example.backend.error.UserPasswordIncorrectException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.backend.repository.UserRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User login(String username, String pwd) throws Exception {
        User user = validateAndGet(username, pwd);
        if (user == null)
            throw new UserPasswordIncorrectException("User or password incorrect!");
        String token = getJWTToken(username, user.getIdUser());
        user.setToken(token);

        return user;
    }

    public User validateAndGet(String username, String pwd) {
        User user = userRepository.findByUserName(username);
        if (user == null)
            return null;
        if (encoder.matches(pwd, user.getPassword())){
            return user;
        }
        return null;
    }

    private String getJWTToken(String username, Long id) {
        String secretKey = "mySecretKey";
        User user = userRepository.findById(id).get();
        Set<String> permissions = new HashSet<>();

        String auth = String.join(",", permissions);
        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(auth);
        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorityList.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return token;
    }
}
