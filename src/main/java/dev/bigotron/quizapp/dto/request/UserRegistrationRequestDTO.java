package dev.bigotron.quizapp.dto.request;

public record UserRegistrationRequestDTO(
        String username,
        String email,
        String password
) {
}
