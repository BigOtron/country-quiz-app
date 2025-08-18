package dev.bigotron.quizapp.dto.response;

import java.util.List;

public record QuestionResponseDTO(
        Long id,
        String text,
        String type,
        List<AnswerOptionResponseDTO> options
) {}
