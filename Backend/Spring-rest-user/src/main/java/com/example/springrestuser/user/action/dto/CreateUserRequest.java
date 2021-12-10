package com.example.springrestuser.user.action.dto;

import com.example.springrestuser.user.entity.User;
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

    public static CreateUserRequest entityToDtoMapper(User user){
        return CreateUserRequest.builder()
                .login(user.getLogin())
                .build();
    }
}
