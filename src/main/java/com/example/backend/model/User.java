package com.example.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    /*@SequenceGenerator(name = "users_gen", sequenceName = "users_seq", allocationSize = 1)*/
    private Long idUser;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true)
    private String username;

    private String password;
    private String token;


}

