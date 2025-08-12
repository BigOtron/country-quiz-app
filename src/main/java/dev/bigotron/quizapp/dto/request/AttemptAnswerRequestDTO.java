package dev.bigotron.quizapp.dto.request;

public record AttemptAnswerRequestDTO(
        Long questionId,
        Long selectedOptionId
) {
}
