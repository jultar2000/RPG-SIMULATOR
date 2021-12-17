package com.example.springrestchampion.championTests.service;


import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.champion.repository.ChampionRepository;
import com.example.springrestchampion.champion.service.ChampionService;
import com.example.springrestchampion.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ChampionServiceTest {

    @Mock
    ChampionRepository championRepository;

    @InjectMocks
    ChampionService underTest;

    @Test
    void shouldAddChampion() {
        User user = new User();
        Race race = new Race();

        Champion champion = Champion.builder()
                .id(1L)
                .name("champion")
                .backstory("noone")
                .experience(0L)
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .level(1)
                .race(race)
                .user(user)
                .build();

        underTest.add(champion);

        ArgumentCaptor<Champion> userArgumentCaptor =
                ArgumentCaptor.forClass(Champion.class);

        verify(championRepository).save(userArgumentCaptor.capture());
        Champion capturedChampion = userArgumentCaptor.getValue();
        assertThat(capturedChampion).isEqualTo(champion);
    }

    @Test
    void shouldUpdateChampion() {
        User user = new User();
        Race race = new Race();

        Champion champion = Champion.builder()
                .id(1L)
                .name("champion")
                .backstory("noone")
                .experience(0L)
                .manaPoints(100L)
                .healthPoints(100L)
                .inteligence(10)
                .strength(10)
                .level(1)
                .race(race)
                .user(user)
                .build();

        underTest.update(champion);

        ArgumentCaptor<Champion> championArgumentCaptor =
                ArgumentCaptor.forClass(Champion.class);

        verify(championRepository).save(championArgumentCaptor.capture());
        Champion capturedChampion = championArgumentCaptor.getValue();
        assertThat(capturedChampion).isEqualTo(champion);
    }

    @Test
    void shouldDeleteChampion() {
        long id = 1;
        underTest.delete(id);
        verify(championRepository).deleteById(id);
    }

    @Test
    void shouldFindChampionById() {
        long id = 1;
        underTest.find(id);
        verify(championRepository).findById(id);
    }

    @Test
    void shouldFindChampionByIdAndUser() {
        User user = new User();
        long id = 1;
        underTest.find(id, user);
        verify(championRepository).findByIdAndUser(id, user);
    }

    @Test
    void shouldFindAllChampions() {
        underTest.findAll();
        verify(championRepository).findAll();
    }

    @Test
    void shouldFindAllChampionsOwnedByUser() {
        User user = new User();
        underTest.findAll(user);
        verify(championRepository).findAllByUser(user);
    }
}
