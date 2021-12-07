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
public class UpdateUserRequest {

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private String email;

    public static void dtoToEntityMapper(UpdateUserRequest request, User user){
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setEmail(request.getEmail());
    }
}
