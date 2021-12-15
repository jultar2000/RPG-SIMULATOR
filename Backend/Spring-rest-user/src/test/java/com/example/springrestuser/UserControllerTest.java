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
    public void canGetListOfUsersTest() throws Exception {
        String uri = "/api/users";
        mvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    public void canGetSingleUserTest() throws Exception {
        User user = User.builder()
                .login("user")
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
                .build();

        mvc.perform(MockMvcRequestBuilders.get("/api/users/{login}", "adam"))
                .andExpect(status().isOk());
    }

    @Test
    public void canAddUserTest() throws Exception {
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
    @Disabled
    public void canDeleteUserTest() throws Exception {

        User user = User.builder()
                .login("user")
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
                .build();

        userService.add(user);

        mvc.perform(MockMvcRequestBuilders.delete("/api/users/{login}","user"))
                .andExpect(status().isAccepted());
    }

    @Test
    @Disabled
    public void canPutUserTest() throws Exception {

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
                .content(objectMapper.writeValueAsString(user)));

        user.setName("new_User");
        mvc.perform(MockMvcRequestBuilders.put("/api/users/{login}","user"))
                .andExpect(status().isAccepted());
    }
}

