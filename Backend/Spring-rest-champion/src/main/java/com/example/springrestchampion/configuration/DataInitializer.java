package com.example.springrestchampion.configuration;

import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.champion.service.ChampionService;
import com.example.springrestchampion.champion.service.RaceService;
import com.example.springrestchampion.user.entity.User;
import com.example.springrestchampion.user.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
                .build();

        User mark = User.builder()
                .login("mark")
                .build();

        User elizabeth = User.builder()
                .login("elizabeth")
                .build();

        User alex = User.builder()
                .login("alex")
                .build();

        userService.add(adam);
        userService.add(mark);
        userService.add(elizabeth);
        userService.add(alex);

        Race bard = Race.builder()
                .name("bard")
                .description("A mysterious travelers known for their healing abilities.")
                .build();

        Race sorcerer = Race.builder()
                .name("sorcerer")
                .description("Smart and intelligent people who were able to master magic skills.")
                .build();

        Race elf = Race.builder()
                .name("elf")
                .description("An ancient human-like race known for their longevity and archery skills.")
                .build();

        Race troll = Race.builder()
                .name("troll")
                .description("Ugly creatures known for their strength and stamina.")
                .build();

        Race dwarf = Race.builder()
                .name("dwarf")
                .description("Human-like race known for their small height and spitefulness.")
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
                .backstory("A man from planet Armos who came to the earth to spread music folklore and help citizens.")
                .race(bard)
                .championUser(elizabeth)
                .build();

        Champion aurelion = Champion.builder()
                .name("aurelion")
                .level(1)
                .experience(0L)
                .strength(3)
                .inteligence(12)
                .healthPoints(700L)
                .manaPoints(600L)
                .backstory("Young talented man who was banished from the academy for dealing with black magic.")
                .race(sorcerer)
                .championUser(adam)
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
                            "were slaughtered by humans.")
                .race(elf)
                .championUser(alex)
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
                .championUser(mark)
                .build();

        Champion kager = Champion.builder()
                .name("kager")
                .level(1)
                .experience(0L)
                .strength(13)
                .inteligence(4)
                .healthPoints(1100L)
                .manaPoints(300L)
                .backstory("Leader of the biggest gang in the capitol of the northern country.")
                .race(dwarf)
                .championUser(mark)
                .build();

        championService.add(arcus);
        championService.add(aurelion);
        championService.add(lores);
        championService.add(hamrok);
        championService.add(kager);

    }
}
