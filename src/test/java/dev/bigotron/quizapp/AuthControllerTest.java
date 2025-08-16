package dev.bigotron.quizapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bigotron.quizapp.controller.AuthController;
import dev.bigotron.quizapp.dto.request.UserRegistrationRequestDTO;
import dev.bigotron.quizapp.service.interfaces.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/auth/register - success")
    void registerUser_success() throws Exception {
        UserRegistrationRequestDTO request = new UserRegistrationRequestDTO(
                "james",
                "hello@gmail.com",
                "1234"
        );

        when(userService.registerUser(Mockito.any(UserRegistrationRequestDTO.class))).thenReturn(null);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    @DisplayName("POST /api/auth/register - failure (IllegalArgumentException)")
    void registerUser_failure() throws Exception {
        UserRegistrationRequestDTO request = new UserRegistrationRequestDTO(
                "james",
                "hello@gmail.com",
                "1234"
        );

        doThrow(new IllegalArgumentException("User already exists"))
                .when(userService).registerUser(Mockito.any(UserRegistrationRequestDTO.class));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User already exists"));
    }

    @Test
    @DisplayName("GET /api/auth/howdy")
    void testHowdy() throws Exception {
        mockMvc.perform(get("/api/auth/howdy"))
                .andExpect(status().isOk())
                .andExpect(content().string("first url"));
    }

    @Test
    @DisplayName("GET /api/auth/howdy2")
    void testHowdy2() throws Exception {
        mockMvc.perform(get("/api/auth/howdy2"))
                .andExpect(status().isOk())
                .andExpect(content().string("second url"));
    }
}