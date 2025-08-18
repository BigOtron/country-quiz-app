package dev.bigotron.quizapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String capital;
    private String flagUrl;
    private String region;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> languages = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country country)) return false;
        return Objects.equals(id, country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
