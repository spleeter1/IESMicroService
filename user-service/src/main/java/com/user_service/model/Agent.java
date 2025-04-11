package com.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="agents")
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = true,length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length =100)
    private String address;

    @Column(nullable = false,length = 11)
    private String phoneNumber;

    private LocalDateTime createdAt;

}
