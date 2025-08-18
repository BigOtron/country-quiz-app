package dev.bigotron.quizapp.service.impl;

import dev.bigotron.quizapp.dto.request.UserRegistrationRequestDTO;
import dev.bigotron.quizapp.dto.response.UserResponseDTO;
import dev.bigotron.quizapp.entity.User;
import dev.bigotron.quizapp.exception.ResourceNotFoundException;
import dev.bigotron.quizapp.repository.UserRepository;
import dev.bigotron.quizapp.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO registerUser(UserRegistrationRequestDTO request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        // TODO - I should add validate annotations on the dto fields
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRegistrationDate(LocalDateTime.now());

        userRepository.save(user);
        return toResponseDTO(user);
    }

    @Override
    public UserResponseDTO loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found: " + id));
        return toResponseDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("Username not found: " + username)
        );
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .build();
    }

    @Override
    public List<UserResponseDTO> loadAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRegistrationRequestDTO request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found: " + id)
        );
        if (request.username() != null && !request.username().isBlank()) {
            user.setUsername(request.username());
        }
        if (request.email() != null && !request.email().isBlank()) {
            user.setEmail(request.email());
        }
        if (request.password() != null && !request.password().isBlank()) {
            user.setPasswordHash(passwordEncoder.encode(request.password()));
        }

        return toResponseDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found: " + id)
        );
        userRepository.delete(user);
    }

    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRegistrationDate()
        );
    }
}