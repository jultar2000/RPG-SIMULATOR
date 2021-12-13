package com.example.springrestchampion.UnitTests.championTests.repository;

import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.champion.repository.ChampionRepository;
import com.example.springrestchampion.user.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@DataJpaTest
public class ChampionRepositoryTest {

    @Autowired
    private ChampionRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    @Disabled
    void findChampionByIdAndUser_whenExists_ReturnFoundChampion() {
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

        underTest.save(champion);

        Optional<Champion> foundChampion = underTest.findByIdAndUser(1L, user);
        assertThat(foundChampion).isEqualTo(champion);
    }

    @Test
    @Disabled
    void findAllChampionsByUser_ReturnListOfFoundChampions() {



    }



}
