package dev.bigotron.quizapp.controller;

import dev.bigotron.quizapp.dto.request.ApiConsumerDTO;
import dev.bigotron.quizapp.dto.request.UserRegistrationRequestDTO;
import dev.bigotron.quizapp.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final RestTemplate restTemplate;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequestDTO request) {
        try {
            userService.registerUser(request);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("howdy")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok().body("first url");
    }

    @GetMapping("howdy2")
    public ResponseEntity<String> ok2() {
        return ResponseEntity.ok().body("second url");
    }

    @GetMapping("howdy3")
    public ResponseEntity<List<ApiConsumerDTO>> ok3(
            @RequestParam String codes
    ) {
//        ResponseEntity<List<QuizDTO>> response = restTemplate.exchange(
//                "http://api.example.com/quizzes",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<QuizDTO>>() {}
//        );

        return restTemplate.exchange(
                "https://restcountries.com/v3.1/alpha?codes=" + codes,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
    }
}
