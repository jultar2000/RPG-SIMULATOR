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
    void canAddChampionTest() {
        User user = Mockito.mock(User.class);
        Race race = Mockito.mock(Race.class);

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
    void canUpdateChampionTest() {
        User user = Mockito.mock(User.class);
        Race race = Mockito.mock(Race.class);

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

        ArgumentCaptor<Champion> userArgumentCaptor =
                ArgumentCaptor.forClass(Champion.class);

        verify(championRepository).save(userArgumentCaptor.capture());
        Champion capturedChampion = userArgumentCaptor.getValue();
        assertThat(capturedChampion).isEqualTo(champion);
    }

    @Test
    void canDeleteChampionTest() {
        long id = 1;
        underTest.delete(id);
        verify(championRepository).deleteById(id);
    }

    @Test
    void canFindChampionById() {
        long id = 1;
        underTest.find(id);
        verify(championRepository).findById(id);
    }

    @Test
    void canFindChampionByIdAndUser() {
        User user = Mockito.mock(User.class);
        long id = 1;
        underTest.find(id,user);
        verify(championRepository).findByIdAndUser(id, user);
    }

    @Test
    void canFindAllChampions() {
        underTest.findAll();
        verify(championRepository).findAll();
    }

    @Test
    void canFindAllChampionsOwnedByUser() {
        User user = Mockito.mock(User.class);
        underTest.findAll(user);
        verify(championRepository).findAllByUser(user);
    }


}
