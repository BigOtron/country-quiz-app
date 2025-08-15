package dev.bigotron.quizapp.controller;

import dev.bigotron.quizapp.dto.request.UserRegistrationRequestDTO;
import dev.bigotron.quizapp.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

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
}
