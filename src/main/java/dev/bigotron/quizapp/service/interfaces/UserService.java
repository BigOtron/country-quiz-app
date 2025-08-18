package dev.bigotron.quizapp.service.interfaces;

import dev.bigotron.quizapp.dto.request.UserRegistrationRequestDTO;
import dev.bigotron.quizapp.dto.response.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserResponseDTO registerUser(UserRegistrationRequestDTO request);
    UserResponseDTO loadUserById(Long id);
    UserDetails loadUserByUsername(String username);
    List<UserResponseDTO> loadAllUsers();
    // FIXME - I should create separate dto for user update request
    UserResponseDTO updateUser(Long id, UserRegistrationRequestDTO request);
    void deleteUser(Long id);
}