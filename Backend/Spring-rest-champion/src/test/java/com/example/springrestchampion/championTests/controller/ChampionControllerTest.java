package com.example.springrestchampion.championTests.controller;

import com.example.springrestchampion.champion.controller.ChampionController;
import com.example.springrestchampion.champion.dto.CreateChampionRequest;
import com.example.springrestchampion.champion.dto.UpdateChampionRequest;
import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.champion.service.ChampionService;
import com.example.springrestchampion.champion.service.RaceService;
import com.example.springrestchampion.user.entity.User;
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
@WebMvcTest(ChampionController.class)
public class ChampionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ChampionService championService;

    @MockBean
    private RaceService raceService;

    @Test
    public void shouldGetListOfChampions() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/champions"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetChampionIfFound() throws Exception {
        Long id = 1L;
        User user = new User();
        Race race = new Race();
        Champion champion = Champion.builder()
                .id(id)
                .name("champion")
                .backstory("noone")
                .experience(0L)
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .level(1)
                .championUser(user)
                .race(race)
                .build();
        doReturn(Optional.of(champion)).when(this.championService).find(id);

        mvc.perform(MockMvcRequestBuilders.get("/api/champions/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotGetChampionIfNotFound() throws Exception {
        Long id = 1L;
        doReturn(Optional.empty()).when(this.championService).find(id);

        mvc.perform(MockMvcRequestBuilders.get("/api/champions/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAddChampionIfRaceFound() throws Exception {
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

        doReturn(Optional.of(race)).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.post("/api/champions")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(championRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldNotAddChampionIfRaceNotFound() throws Exception {
        String name = "race";

        CreateChampionRequest championRequest = CreateChampionRequest.builder()
                .name("championRequest")
                .backstory("noone")
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .race(name)
                .build();

        doReturn(Optional.empty()).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.post("/api/champions")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(championRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateChampionIfFound() throws Exception {
        Long id = 1L;
        User user = new User();
        Race race = new Race();
        Champion champion = Champion.builder()
                .id(id)
                .name("champion")
                .backstory("noone")
                .experience(0L)
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .level(1)
                .championUser(user)
                .race(race)
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

        doReturn(Optional.of(champion)).when(this.championService).find(id);

        mvc.perform(MockMvcRequestBuilders.put("/api/champions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(championRequest)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotUpdateChampionIfNotFound() throws Exception {
        Long id = 1L;

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

        doReturn(Optional.empty()).when(this.championService).find(id);

        mvc.perform(MockMvcRequestBuilders.put("/api/champions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(championRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteChampionIfFound() throws Exception {
        Long id = 1L;
        User user = new User();
        Race race = new Race();
        Champion champion = Champion.builder()
                .id(id)
                .name("champion")
                .backstory("noone")
                .experience(0L)
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .level(1)
                .championUser(user)
                .race(race)
                .build();

        doReturn(Optional.of(champion)).when(this.championService).find(id);

        mvc.perform(MockMvcRequestBuilders.delete("/api/champions/{id}", id))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotDeleteChampionIfNotFound() throws Exception {
        Long id = 1L;
        doReturn(Optional.empty()).when(this.championService).find(id);

        mvc.perform(MockMvcRequestBuilders.delete("/api/champions/{id}", id))
                .andExpect(status().isNotFound());
    }
}
