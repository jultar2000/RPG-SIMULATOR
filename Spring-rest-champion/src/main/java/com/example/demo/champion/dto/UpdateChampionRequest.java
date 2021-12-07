package com.example.demo.champion.dto;

import com.example.demo.champion.entity.Champion;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateChampionRequest {

    private String name;

    private String backstory;

    private Long experience;

    private Long manaPoints;

    private Long healthPoints;

    private int inteligence;

    private int strength;

    private int level;

    public static void dtoToEntityUpdater(UpdateChampionRequest updateChampionRequest, Champion champion){
        champion.setName(updateChampionRequest.getName());
        champion.setBackstory(updateChampionRequest.getBackstory());
        champion.setExperience(updateChampionRequest.getExperience());
        champion.setManaPoints(updateChampionRequest.getManaPoints());
        champion.setHealthPoints(updateChampionRequest.getHealthPoints());
        champion.setInteligence(updateChampionRequest.getInteligence());
        champion.setStrength(updateChampionRequest.getStrength());
        champion.setLevel(champion.getLevel());
    }
}
