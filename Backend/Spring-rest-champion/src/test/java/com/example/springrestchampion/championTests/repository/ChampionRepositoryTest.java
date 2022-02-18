//package com.example.springrestchampion.championTests.repository;
//
//import com.example.springrestchampion.champion.entity.Champion;
//import com.example.springrestchampion.champion.entity.Race;
//import com.example.springrestchampion.champion.repository.ChampionRepository;
//import com.example.springrestchampion.user.entity.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@Transactional
//@DataJpaTest
//public class ChampionRepositoryTest {
//
//    @Autowired
//    private ChampionRepository underTest;
//
//    @AfterEach
//    void tearDown() {
//        underTest.deleteAll();
//    }
//
//    @Test
//    void findChampionByIdAndUser_whenExists_ReturnFoundChampion() {
//        User user = new User();
//        Race race = new Race();
//
//        Champion champion = Champion.builder()
//                .id(1L)
//                .name("champion")
//                .backstory("noone")
//                .experience(0L)
//                .manaPoints(100L)
//                .healthPoints(100L)
//                .inteligence(10)
//                .strength(10)
//                .level(1)
//                .race(race)
//                .championUser(user)
//                .build();
//
//        underTest.save(champion);
//
//        Optional<Champion> foundChampion = underTest.findByIdAndChampionUser(1L, user);
//        assertThat(foundChampion).isEqualTo(champion);
//    }
//}
