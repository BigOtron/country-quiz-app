package dev.bigotron.quizapp.repository;

import dev.bigotron.quizapp.entity.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
}