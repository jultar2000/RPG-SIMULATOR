package com.example.springrestchampion.champion.dto;

import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.user.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateChampionRequest {

    private String name;

    private String backstory;

    private Long manaPoints;

    private Long healthPoints;

    private int strength;

    private int inteligence;

    private String race;

    public static Champion dtoToEntityMapper(CreateChampionRequest request, Race race, User user) {
        return Champion.builder()
                .name(request.getName())
                .backstory(request.getBackstory())
                .manaPoints(request.getManaPoints())
                .healthPoints(request.getHealthPoints())
                .strength(request.getStrength())
                .inteligence(request.getInteligence())
                .race(race)
                .championUser(user)
                .build();
    }
}
