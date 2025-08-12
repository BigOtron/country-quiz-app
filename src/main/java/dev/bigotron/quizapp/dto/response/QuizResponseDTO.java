package dev.bigotron.quizapp.dto.response;

import java.util.List;

public record QuizResponseDTO(
        Long id,
        String title,
        List<QuestionResponseDTO> questions
) {}
