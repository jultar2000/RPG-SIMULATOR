package com.example.demo.champion.dto;

import com.example.demo.champion.entity.Race;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetRaceResponse {

    String name;

    String description;

    public static GetRaceResponse entityToDtoMapper(Race race) {
        return GetRaceResponse.builder()
                .name(race.getName())
                .description(race.getDescription())
                .build();
    }
}
