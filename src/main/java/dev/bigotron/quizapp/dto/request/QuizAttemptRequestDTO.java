package dev.bigotron.quizapp.dto.request;

import java.util.List;

public record QuizAttemptRequestDTO(
        Long quizId,
        List<AttemptAnswerRequestDTO> answers
) {
}
