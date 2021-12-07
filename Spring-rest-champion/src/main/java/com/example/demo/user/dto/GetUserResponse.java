package com.example.demo.user.dto;

import com.example.demo.user.entity.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUserResponse {

    private String login;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private String email;

    public static GetUserResponse entityToDtoMapper(User user){
        return GetUserResponse.builder()
                .login(user.getLogin())
                .name(user.getName())
                .surname(user.getSurname())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .build();
    }
}
