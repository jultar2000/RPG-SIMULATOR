package com.example.springrestchampion.champion.dto;

import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.champion.entity.Race;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetChampionResponse {

    private Long id;

    private String name;

    private String backstory;

    private Long experience;

    private Long manaPoints;

    private Long healthPoints;

    private int inteligence;

    private int strength;

    private int level;

    private Race race;

    public static GetChampionResponse entityToDtoMapper(Champion champion) {
        return GetChampionResponse.builder()
                .id(champion.getId())
                .name(champion.getName())
                .backstory(champion.getBackstory())
                .experience(champion.getExperience())
                .manaPoints(champion.getManaPoints())
                .healthPoints(champion.getHealthPoints())
                .inteligence(champion.getInteligence())
                .strength(champion.getStrength())
                .level(champion.getLevel())
                .race(champion.getRace())
                .build();
    }
}
