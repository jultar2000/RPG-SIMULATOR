package com.example.springrestchampion.champion.dto;

import com.example.springrestchampion.champion.entity.Race;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateRaceRequest {

    String description;

    public static void dtoToEntityMapper(UpdateRaceRequest raceRequest, Race race){
        race.setDescription(raceRequest.getDescription());
    }
}
