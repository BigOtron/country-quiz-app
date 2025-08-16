package dev.bigotron.quizapp.dto.request;

public record LoginRequest(
        String username,
        String password
) {
}
