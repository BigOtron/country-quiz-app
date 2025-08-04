package dev.bigotron.quizapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String capital;
    private String flagUrl;
    private String region;

    @ElementCollection
    private List<String> languages;
}
