package com.example.springrestchampion.championTests.controller;

import com.example.springrestchampion.champion.controller.UserChampionController;
import com.example.springrestchampion.champion.dto.CreateChampionRequest;
import com.example.springrestchampion.champion.dto.UpdateChampionRequest;
import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.champion.service.ChampionService;
import com.example.springrestchampion.champion.service.RaceService;
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

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserChampionController.class)
public class UserChampionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ChampionService championService;

    @MockBean
    private RaceService raceService;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnChampionsOfUserIfUserFound() throws Exception {
        String login = "user";

        User user = User.builder()
                .login(login)
                .build();

        doReturn(Optional.of(user)).when(this.userService).find(login);

        mvc.perform(MockMvcRequestBuilders.get("/api/users/{login}/champions", login))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotReturnChampionsOfUserIfUserNotFound() throws Exception {
        String login = "user";

        doReturn(Optional.empty()).when(this.userService).find(login);

        mvc.perform(MockMvcRequestBuilders.get("/api/users/{login}/champions", login))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnChampionOfUserIfUserAndChampionFound() throws Exception {
        String login = "user";
        Long id = 1L;

        User user = User.builder()
                .login(login)
                .build();

        Champion champion = Champion.builder()
                .id(id)
                .user(user)
                .build();

        doReturn(Optional.of(user)).when(this.userService).find(login);
        doReturn(Optional.of(champion)).when(this.championService).find(id, user);

        mvc.perform(MockMvcRequestBuilders.get("/api/users/{login}/champions/{id}", login, id))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotReturnChampionOfUserIfUserNotFound() throws Exception {
        String login = "user";
        Long id = 1L;

        User user = User.builder()
                .login(login)
                .build();

        Champion champion = Champion.builder()
                .id(id)
                .user(user)
                .build();

        doReturn(Optional.empty()).when(this.userService).find(login);
        doReturn(Optional.of(champion)).when(this.championService).find(id, user);

        mvc.perform(MockMvcRequestBuilders.get("/api/users/{login}/champions/{id}", login, id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotReturnChampionOfUserIfChampionNotFound() throws Exception {
        String login = "user";
        Long id = 1L;

        User user = User.builder()
                .login(login)
                .build();

        doReturn(Optional.of(user)).when(this.userService).find(login);
        doReturn(Optional.empty()).when(this.championService).find(id, user);

        mvc.perform(MockMvcRequestBuilders.get("/api/users/{login}/champions/{id}", login, id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAddChampionToUserIfUserAndRaceFound() throws Exception {
        String login = "user";
        String name = "race";

        User user = User.builder()
                .login(login)
                .build();

        Race race = Race.builder()
                .name(name)
                .build();

        CreateChampionRequest championRequest = CreateChampionRequest.builder()
                .name("championRequest")
                .backstory("noone")
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .race(name)
                .build();

        doReturn(Optional.of(user)).when(this.userService).find(login);
        doReturn(Optional.of(race)).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.post("/api/users/{login}/champions", login)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(championRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldNotAddChampionToUserIfUserNotFound() throws Exception {
        String login = "user";
        String name = "race";

        Race race = Race.builder()
                .name(name)
                .build();

        CreateChampionRequest championRequest = CreateChampionRequest.builder()
                .name("championRequest")
                .backstory("noone")
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .race(name)
                .build();

        doReturn(Optional.empty()).when(this.userService).find(login);
        doReturn(Optional.of(race)).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.post("/api/users/{login}/champions", login)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(championRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotAddChampionToUserIfRaceNotFound() throws Exception {
        String login = "user";
        String name = "race";

        User user = User.builder()
                .login(login)
                .build();

        CreateChampionRequest championRequest = CreateChampionRequest.builder()
                .name("championRequest")
                .backstory("noone")
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .race(name)
                .build();

        doReturn(Optional.of(user)).when(this.userService).find(login);
        doReturn(Optional.empty()).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.post("/api/users/{login}/champions", login)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(championRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateUserChampionIfUserAndChampionFound() throws Exception {
        String login = "user";
        Long id = 1L;

        User user = User.builder()
                .login(login)
                .build();

        Champion champion = Champion.builder()
                .id(id)
                .user(user)
                .build();

        UpdateChampionRequest championRequest = UpdateChampionRequest.builder()
                .name("championRequest")
                .backstory("noone")
                .experience(0L)
                .level(1)
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .build();

        doReturn(Optional.of(user)).when(this.userService).find(login);
        doReturn(Optional.of(champion)).when(this.championService).find(id, user);

        mvc.perform(MockMvcRequestBuilders.put("/api/users/{login}/champions/{id}", login, id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(championRequest)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotUpdateChampionToUserIfUserNotFound() throws Exception {
        String login = "user";
        Long id = 1L;

        User user = User.builder()
                .login(login)
                .build();

        Champion champion = Champion.builder()
                .id(id)
                .user(user)
                .build();

        UpdateChampionRequest championRequest = UpdateChampionRequest.builder()
                .name("championRequest")
                .backstory("noone")
                .experience(0L)
                .level(1)
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .build();

        doReturn(Optional.empty()).when(this.userService).find(login);
        doReturn(Optional.of(champion)).when(this.championService).find(id, user);

        mvc.perform(MockMvcRequestBuilders.put("/api/users/{login}/champions/{id}", login, id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(championRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotUpdateChampionToUserIfChampionNotFound() throws Exception {
        String login = "user";
        Long id = 1L;

        User user = User.builder()
                .login(login)
                .build();

        UpdateChampionRequest championRequest = UpdateChampionRequest.builder()
                .name("championRequest")
                .backstory("noone")
                .experience(0L)
                .level(1)
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .build();

        doReturn(Optional.of(user)).when(this.userService).find(login);
        doReturn(Optional.empty()).when(this.championService).find(id, user);

        mvc.perform(MockMvcRequestBuilders.put("/api/users/{login}/champions/{id}", login, id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(championRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteUserChampionIfUserAndChampionFound() throws Exception {
        String login = "user";
        Long id = 1L;

        User user = User.builder()
                .login(login)
                .build();

        Champion champion = Champion.builder()
                .id(id)
                .user(user)
                .build();

        doReturn(Optional.of(user)).when(this.userService).find(login);
        doReturn(Optional.of(champion)).when(this.championService).find(id, user);

        mvc.perform(MockMvcRequestBuilders.delete("/api/users/{login}/champions/{id}", login, id))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotDeleteUserChampionIfUserNotFound() throws Exception {
        String login = "user";
        Long id = 1L;

        User user = User.builder()
                .login(login)
                .build();

        Champion champion = Champion.builder()
                .id(id)
                .user(user)
                .build();

        doReturn(Optional.empty()).when(this.userService).find(login);
        doReturn(Optional.of(champion)).when(this.championService).find(id, user);

        mvc.perform(MockMvcRequestBuilders.delete("/api/users/{login}/champions/{id}", login, id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotDeleteUserChampionIfChampionNotFound() throws Exception {
        String login = "user";
        Long id = 1L;

        User user = User.builder()
                .login(login)
                .build();

        doReturn(Optional.of(user)).when(this.userService).find(login);
        doReturn(Optional.empty()).when(this.championService).find(id, user);

        mvc.perform(MockMvcRequestBuilders.delete("/api/users/{login}/champions/{id}", login, id))
                .andExpect(status().isNotFound());
    }
}
