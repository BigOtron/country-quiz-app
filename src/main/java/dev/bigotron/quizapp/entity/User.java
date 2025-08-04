package dev.bigotron.quizapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String passwordHash;
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "user")
    private List<QuizAttempt> attempts;
}
