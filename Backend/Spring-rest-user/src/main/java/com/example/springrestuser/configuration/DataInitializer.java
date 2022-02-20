package com.example.springrestuser.configuration;

import com.example.springrestuser.user.entity.User;
import com.example.springrestuser.user.security.Sha256;
import com.example.springrestuser.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DataInitializer {

    UserService userService;

    @Autowired
    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initData() {

        User adam = User.builder()
                .login("adam")
                .name("Adam")
                .surname("Smith")
                .dateOfBirth(LocalDate.of(2001, 2, 9))
                .email("adam.smith@gmail.com")
                .password(Sha256.hash("adamadam"))
                .build();

        User mark = User.builder()
                .login("mark")
                .name("Mark")
                .surname("Hauer")
                .dateOfBirth(LocalDate.of(1998, 6, 15))
                .email("mark.hauer@gmail.com")
                .password(Sha256.hash("markmark"))
                .build();

        User elizabeth = User.builder()
                .login("elizabeth")
                .name("Elizabeth")
                .surname("Manner")
                .dateOfBirth(LocalDate.of(1994, 4, 21))
                .email("elizabeth.manner@gmail.com")
                .password(Sha256.hash("elizaeliza"))
                .build();

        User alex = User.builder()
                .login("alex")
                .name("Alex")
                .surname("Madess")
                .dateOfBirth(LocalDate.of(2000, 12, 12))
                .email("alex.madess@gmail.com")
                .password(Sha256.hash("alexalex"))
                .build();

        userService.add(adam);
        userService.add(mark);
        userService.add(elizabeth);
        userService.add(alex);
    }
}