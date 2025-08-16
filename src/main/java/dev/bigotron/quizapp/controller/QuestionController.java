package dev.bigotron.quizapp.controller;

import dev.bigotron.quizapp.dto.request.ApiConsumerDTO;
import dev.bigotron.quizapp.dto.response.QuestionResponseDTO;
import dev.bigotron.quizapp.service.impl.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping()
    public ResponseEntity<List<QuestionResponseDTO>> questions() {
        return ResponseEntity.ok().body(questionService.generateQuestions());
    }
}
