package dev.bigotron.quizapp.repository;

import dev.bigotron.quizapp.entity.AttemptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswer, Long> {}