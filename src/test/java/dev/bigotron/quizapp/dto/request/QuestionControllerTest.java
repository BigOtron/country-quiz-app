package dev.bigotron.quizapp.dto.request;

import dev.bigotron.quizapp.controller.QuestionController;
import dev.bigotron.quizapp.dto.response.AnswerOptionResponseDTO;
import dev.bigotron.quizapp.dto.response.QuestionResponseDTO;
import dev.bigotron.quizapp.service.impl.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
@AutoConfigureMockMvc(addFilters = false)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private QuestionService questionService;

    @Test
    @WithMockUser
    void shouldReturnQuestions() throws Exception {
        List<QuestionResponseDTO> mockQuestions = List.of(
                new QuestionResponseDTO(
                        1L,
                        "What is the capital of France?",
                        "multiple-choice",
                        List.of(new AnswerOptionResponseDTO(1L, "Paris"))
                )
        );

        when(questionService.generateQuestions()).thenReturn(mockQuestions);

        mockMvc.perform(get("/api/questions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].text").value("What is the capital of France?"))
                .andExpect(jsonPath("$[0].type").value("multiple-choice"))
                .andExpect(jsonPath("$[0].options[0].text").value("Paris"));
    }
}