package dev.bigotron.quizapp.service.interfaces;

import dev.bigotron.quizapp.dto.request.UserRegistrationRequestDTO;
import dev.bigotron.quizapp.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO registerUser(UserRegistrationRequestDTO request);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO getUserByUsername(String username);
    List<UserResponseDTO> getAllUsers();
    // FIXME - I should create separate dto for user update request
    UserResponseDTO updateUser(Long id, UserRegistrationRequestDTO request);
    void deleteUser(Long id);
}