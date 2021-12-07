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
public class UpdateRaceRequest {

    String name;

    String description;

    public static void dtoToEntityMapper(UpdateRaceRequest raceRequest, Race race){
        race.setName(raceRequest.getName());
        race.setDescription(raceRequest.getDescription());
    }
}
