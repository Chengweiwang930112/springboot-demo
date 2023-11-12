package com.demo.coding.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Data
@NoArgsConstructor
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    public UserDAO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
