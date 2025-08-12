package dev.bigotron.quizapp.dto.response;

public record AttemptAnswerResponseDTO(
        Long questionId,
        Long selectedOptionId,
        boolean isCorrect) {
}
