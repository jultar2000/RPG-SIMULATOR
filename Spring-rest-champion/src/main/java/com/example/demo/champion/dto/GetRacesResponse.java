package com.example.demo.champion.dto;

import com.example.demo.champion.entity.Race;
import lombok.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetRacesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class RaceDTO {
        private String name;
    }

    List<RaceDTO> races;

    public static GetRacesResponse entityToDtoMapper(Collection<Race> given_races) {
        GetRacesResponse.GetRacesResponseBuilder response = GetRacesResponse.builder();

        List<GetRacesResponse.RaceDTO> collect = given_races.stream()
                .map(race ->
                        GetRacesResponse.RaceDTO.builder()
                                .name(race.getName())
                                .build())
                .collect(Collectors.toList());

        return response.races(collect).build();

    }
}
