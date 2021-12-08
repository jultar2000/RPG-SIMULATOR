package com.example.springrestuser.user.dto;

import com.example.springrestuser.user.entity.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateUserRequest {

    private String login;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private String password;

    private String email;

    public static User dtoToEntityMapper(CreateUserRequest request) {
        return User.builder()
                .login(request.getLogin())
                .name(request.getName())
                .surname(request.getSurname())
                .dateOfBirth(request.getDateOfBirth())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
    }
}
