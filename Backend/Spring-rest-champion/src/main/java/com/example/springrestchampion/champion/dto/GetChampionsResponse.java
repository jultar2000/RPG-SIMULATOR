package com.example.springrestchampion.champion.dto;

import com.example.springrestchampion.champion.entity.Champion;
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
public class GetChampionsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class ChampionDTO {
        private Long id;

        private String name;
    }

    private List<ChampionDTO> champions;

    public static GetChampionsResponse entityToDtoMapper(Collection<Champion> given_champions) {
        GetChampionsResponseBuilder response = GetChampionsResponse.builder();

        List<ChampionDTO> collect = given_champions.stream()
                .map(champion ->
                        ChampionDTO.builder()
                                .id(champion.getId())
                                .name(champion.getName())
                                .build())
                .collect(Collectors.toList());

       return response.champions(collect).build();
    }
}
