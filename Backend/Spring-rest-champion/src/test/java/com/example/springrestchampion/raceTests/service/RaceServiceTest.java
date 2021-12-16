package com.example.springrestchampion.raceTests.service;

import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.champion.repository.RaceRepository;
import com.example.springrestchampion.champion.service.RaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RaceServiceTest {

    @Mock
    RaceRepository raceRepository;

    @InjectMocks
    RaceService underTest;

    @Test
    void shouldAddRace() {
        Race race = Race.builder()
                .name("race")
                .description("nothing")
                .build();

        underTest.add(race);

        ArgumentCaptor<Race> userArgumentCaptor =
                ArgumentCaptor.forClass(Race.class);

        verify(raceRepository).save(userArgumentCaptor.capture());
        Race capturedRace = userArgumentCaptor.getValue();
        assertThat(capturedRace).isEqualTo(race);
    }

    @Test
    void shouldUpdateRace() {
        Race race = Race.builder()
                .name("race")
                .description("nothing")
                .build();

        underTest.update(race);

        ArgumentCaptor<Race> userArgumentCaptor =
                ArgumentCaptor.forClass(Race.class);

        verify(raceRepository).save(userArgumentCaptor.capture());
        Race capturedRace = userArgumentCaptor.getValue();
        assertThat(capturedRace).isEqualTo(race);
    }

    @Test
    void shouldDeleteRace() {
        String id = "race";
        underTest.delete(id);
        verify(raceRepository).deleteById(id);
    }

    @Test
    void shouldFindRaceById() {
        String id = "race";
        underTest.find(id);
        verify(raceRepository).findById(id);
    }

    @Test
    void shouldFindAllRaces() {
        underTest.findAll();
        verify(raceRepository).findAll();
    }
}
