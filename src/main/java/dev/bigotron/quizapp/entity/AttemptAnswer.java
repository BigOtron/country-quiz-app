package dev.bigotron.quizapp.entity;

import jakarta.persistence.*;

@Entity
public class AttemptAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private QuizAttempt attempt;

    @ManyToOne
    private Question question;

    @ManyToOne
    private AnswerOption selectedOption;

    private boolean isCorrect;
}
