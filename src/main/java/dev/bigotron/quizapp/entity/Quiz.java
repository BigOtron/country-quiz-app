package dev.bigotron.quizapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private String difficultyLevel;
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<QuizAttempt> attempts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quiz quiz)) return false;
        return Objects.equals(id, quiz.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
