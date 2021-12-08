package com.example.springrestuser.user.controller;


import com.example.springrestuser.user.dto.CreateUserRequest;
import com.example.springrestuser.user.dto.GetUserResponse;
import com.example.springrestuser.user.dto.GetUsersResponse;
import com.example.springrestuser.user.dto.UpdateUserRequest;

import com.example.springrestuser.user.entity.User;
import com.example.springrestuser.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<GetUsersResponse> getUsers() {
        List<User> all = userService.findAll();
        GetUsersResponse getUsersResponse = GetUsersResponse.entityToDtoMapper(all);
        return ResponseEntity.ok(getUsersResponse);
    }

    @GetMapping("{login}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("login") String login) {
        return userService.find(login)
                .map(user -> ResponseEntity.ok(GetUserResponse.entityToDtoMapper(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request) {
        User user = CreateUserRequest.dtoToEntityMapper(request);
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{login}")
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserRequest request,
                                           @PathVariable("login") String login) {
        Optional<User> user = userService.find(login);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UpdateUserRequest.dtoToEntityMapper(request, user.get());
        userService.update(user.get());
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable("login") String login) {
        Optional<User> user = userService.find(login);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(user.get());
        return ResponseEntity.accepted().build();
    }
}
