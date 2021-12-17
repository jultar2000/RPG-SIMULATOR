package com.example.springrestchampion.raceTests.controller;

import com.example.springrestchampion.champion.controller.RaceController;
import com.example.springrestchampion.champion.dto.CreateRaceRequest;
import com.example.springrestchampion.champion.dto.UpdateRaceRequest;
import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.champion.service.RaceService;
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
@WebMvcTest(RaceController.class)
public class RaceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RaceService raceService;

    @Test
    public void shouldGetListOfRaces() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/races"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetRaceIfFound() throws Exception {
        String name = "race";
        Race race = Race.builder()
                .name(name)
                .description("none")
                .build();

        doReturn(Optional.of(race)).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.get("/api/races/{name}", name))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotGetRaceIfNotFound() throws Exception {
        String name = "race";
        doReturn(Optional.empty()).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.get("/api/races/{id}", name))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAddRace() throws Exception {
        String name = "race";

        CreateRaceRequest raceRequest = CreateRaceRequest.builder()
                .name(name)
                .description("none")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/api/races")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(raceRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateRaceIfFound() throws Exception {
        String name = "race";

        Race race = Race.builder()
                .name(name)
                .description("none")
                .build();

        UpdateRaceRequest raceRequest = UpdateRaceRequest.builder()
                .description("nothing")
                .build();

        doReturn(Optional.of(race)).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.put("/api/races/{name}", name)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(raceRequest)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotUpdateRaceIfNotFound() throws Exception {
        String name = "race";

        UpdateRaceRequest raceRequest = UpdateRaceRequest.builder()
                .description("nothing")
                .build();
        doReturn(Optional.empty()).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.put("/api/races/{name}", name)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(raceRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteRaceIfFound() throws Exception {
        String name = "race";
        Race race = Race.builder()
                .name(name)
                .description("none")
                .build();

        doReturn(Optional.of(race)).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.delete("/api/races/{name}", name))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotDeleteRaceIfNotFound() throws Exception {
        String name = "race";
        doReturn(Optional.empty()).when(this.raceService).find(name);

        mvc.perform(MockMvcRequestBuilders.delete("/api/races/{name}", name))
                .andExpect(status().isNotFound());
    }
}
