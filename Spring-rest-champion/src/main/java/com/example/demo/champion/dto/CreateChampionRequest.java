package com.example.demo.champion.dto;

import com.example.demo.champion.entity.Champion;
import com.example.demo.champion.entity.Race;
import com.example.demo.user.entity.User;
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

    public static Champion dtoToEntityMapper(CreateChampionRequest request, User user, Race race) {
        return Champion.builder()
                .name(request.getName())
                .backstory(request.getBackstory())
                .manaPoints(request.getManaPoints())
                .healthPoints(request.getHealthPoints())
                .strength(request.getStrength())
                .inteligence(request.getInteligence())
                .race(race)
                .user(user)
                .build();
    }
}
