package com.example.springrestuser;

import com.example.springrestuser.user.controller.UserController;
import com.example.springrestuser.user.entity.User;
import com.example.springrestuser.user.security.Sha256;
import com.example.springrestuser.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
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
    public void shouldGetListOfUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetUserIfFound() throws Exception {
        String login = "user";
        User user = User.builder()
                .login(login)
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
                .build();
        doReturn(Optional.of(user)).when(this.userService).find(login);

        mvc.perform(MockMvcRequestBuilders.get("/api/users/{login}", login))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotGetUserIfNotFound() throws Exception {
        String login = "user";
        doReturn(Optional.empty()).when(this.userService).find(login);

        mvc.perform(MockMvcRequestBuilders.get("/api/users/{login}", login))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAddUser() throws Exception {
        User user = User.builder()
                .login("user")
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateUserIfFound() throws Exception {
        // Arrange
        String login = "user";
        User user = User.builder()
                .login(login)
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
                .build();
        doReturn(Optional.of(user)).when(this.userService).find(login);
        user.setName("updated_user");

        // Act & Assert
        mvc.perform(MockMvcRequestBuilders.put("/api/users/{login}", login)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotUpdateUserIfNotFound() throws Exception {
        // Arrange
        String login = "user";
        User user = User.builder()
                .login(login)
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
                .build();
        doReturn(Optional.empty()).when(this.userService).find(login);
        user.setName("updated_user");

        // Act & Assert
        mvc.perform(MockMvcRequestBuilders.put("/api/users/{login}", login)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteUserIfFound() throws Exception {
        // Arrange
        String login = "user";
        User user = User.builder()
                .login(login)
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
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