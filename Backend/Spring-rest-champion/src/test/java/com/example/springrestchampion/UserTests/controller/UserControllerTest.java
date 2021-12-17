package com.example.springrestchampion.UserTests.controller;

import com.example.springrestchampion.user.controller.UserController;
import com.example.springrestchampion.user.entity.User;
import com.example.springrestchampion.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void shouldAddUser() throws Exception {
        User user = User.builder()
                .login("user")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldDeleteUserIfFound() throws Exception {
        // Arrange
        String login = "user";
        User user = User.builder()
                .login(login)
                .build();
        doReturn(Optional.of(user)).when(this.userService).find(login);

        // Act & Assert
        mvc.perform(MockMvcRequestBuilders.delete("/api/users/{login}", login))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotDeleteUserIfNotFound() throws Exception {
        // Arrange
        String login = "user";
        doReturn(Optional.empty()).when(this.userService).find(login);

        // Act & Assert
        mvc.perform(MockMvcRequestBuilders.delete("/api/users/{login}", login))
                .andExpect(status().isNotFound());
    }
}
