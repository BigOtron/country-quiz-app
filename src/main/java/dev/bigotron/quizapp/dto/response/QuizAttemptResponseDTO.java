package dev.bigotron.quizapp.dto.response;

import java.util.List;

public record QuizAttemptResponseDTO(
        Long id,
        Long quizId,
        Double score,
        List<AttemptAnswerResponseDTO> answers
) {}
