package com.example.springrestchampion.user.dto;

import com.example.springrestchampion.user.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateUserRequest {

    private String login;

    public static User dtoToEntityMapper(CreateUserRequest request){
            return User.builder()
                    .login(request.getLogin())
                    .build();
    }
}
