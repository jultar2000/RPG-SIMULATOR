package com.example.demo.user.dto;

import com.example.demo.user.entity.User;
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
public class GetUsersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class UserDTO {

        private String login;
    }

    List<UserDTO> users;

    public static GetUsersResponse entityToDtoMapper(Collection<User> given_users) {
        GetUsersResponse.GetUsersResponseBuilder response = GetUsersResponse.builder();
        List<UserDTO> collect = given_users.stream()
                .map(user ->
                        UserDTO.builder()
                                .login(user.getLogin())
                                .build())
                .collect(Collectors.toList());
        return response.users(collect).build();
    }
}
