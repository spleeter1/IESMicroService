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
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(unique = true,nullable = true,length = 50)
        private String username;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false)
        private String role;

        private LocalDateTime createdAt;
}
