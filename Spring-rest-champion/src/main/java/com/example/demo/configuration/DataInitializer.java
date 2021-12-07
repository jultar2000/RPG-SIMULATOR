package com.example.demo.configuration;

import com.example.demo.champion.entity.Race;
import com.example.demo.champion.entity.Champion;
import com.example.demo.champion.service.ChampionService;
import com.example.demo.champion.service.RaceService;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DataInitializer {

    ChampionService championService;

    UserService userService;

    RaceService raceService;

    public DataInitializer(ChampionService championService, UserService userService, RaceService raceService) {
        this.championService = championService;
        this.userService = userService;
        this.raceService = raceService;
    }

    @PostConstruct
    public void initData() {

        User adam = User.builder()
                .login("adam")
                .name("Adam")
                .surname("Smith")
                .dateOfBirth(LocalDate.of(2001,2,9))
                .email("adam.smith@gmail.com")
                .build();

        User mark = User.builder()
                .login("mark")
                .name("Mark")
                .surname("Hauer")
                .dateOfBirth(LocalDate.of(1998,6,15))
                .email("mark.hauer@gmail.com")
                .build();

        User elizabeth = User.builder()
                .login("elizabeth")
                .name("Elizabeth")
                .surname("Manner")
                .dateOfBirth(LocalDate.of(1994,4,21))
                .email("elizabeth.manner@gmail.com")
                .build();

        User alex = User.builder()
                .login("alex")
                .name("Alex")
                .surname("Madess")
                .dateOfBirth(LocalDate.of(2000,12,12))
                .email("alex.madess@gmail.com")
                .build();

        userService.add(adam);
        userService.add(mark);
        userService.add(elizabeth);
        userService.add(alex);

        Race bard = Race.builder()
                .name("bard")
                .description("A mysterious travelers known for their healing abilities")
                .build();

        Race sorcerer = Race.builder()
                .name("sorcerer")
                .description("Smart and intelligent people who were able to master magic skills")
                .build();

        Race elf = Race.builder()
                .name("elf")
                .description("An ancient human-like race known for their longevity and archery skills")
                .build();

        Race troll = Race.builder()
                .name("troll")
                .description("Ugly creatures known for their strength and stamina")
                .build();

        Race dwarf = Race.builder()
                .name("dwarf")
                .description("Human-like race known for their small height and spitefulness")
                .build();

        raceService.add(bard);
        raceService.add(elf);
        raceService.add(troll);
        raceService.add(dwarf);
        raceService.add(sorcerer);

        Champion arcus = Champion.builder()
                .name("arcus")
                .level(1)
                .experience(0L)
                .strength(3)
                .inteligence(8)
                .healthPoints(700L)
                .manaPoints(700L)
                .backstory("A man from planet Armos who came to the earth to spread music folklore and help citizens")
                .race(bard)
                .user(elizabeth)
                .build();

        Champion aurelion = Champion.builder()
                .name("aurelion")
                .level(1)
                .experience(0L)
                .strength(3)
                .inteligence(12)
                .healthPoints(700L)
                .manaPoints(600L)
                .backstory("Young talented man who was banished from the academy for dealing with black magic")
                .race(sorcerer)
                .user(adam)
                .build();

        Champion lores = Champion.builder()
                .name("lores")
                .level(1)
                .experience(0L)
                .strength(8)
                .inteligence(8)
                .healthPoints(750L)
                .manaPoints(400L)
                .backstory("Great archer who left his village to seek revenge after his comrades " +
                        "were slaughtered by humans")
                .race(elf)
                .user(alex)
                .build();

        Champion hamrok = Champion.builder()
                .name("hamrok")
                .level(1)
                .experience(0L)
                .strength(17)
                .inteligence(2)
                .healthPoints(1400L)
                .manaPoints(50L)
                .backstory("Troll who just awaken after hibernating in his cave for a decade and he's hungry!")
                .race(troll)
                .user(mark)
                .build();

        Champion kager = Champion.builder()
                .name("kager")
                .level(1)
                .experience(0L)
                .strength(13)
                .inteligence(4)
                .healthPoints(1100L)
                .manaPoints(300L)
                .backstory("Leader of the biggest gang in the capitol of the northern country")
                .race(dwarf)
                .user(mark)
                .build();

        championService.add(arcus);
        championService.add(aurelion);
        championService.add(lores);
        championService.add(hamrok);
        championService.add(kager);

    }
}
