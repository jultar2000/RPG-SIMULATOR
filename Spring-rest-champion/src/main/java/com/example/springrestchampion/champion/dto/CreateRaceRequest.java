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
public class CreateRaceRequest {

    String name;

    String description;

    public static Race dtoToEntityMapper(CreateRaceRequest request) {
        return Race.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
}







