package com.example.demo.user.controller;

import com.example.demo.user.dto.CreateUserRequest;
import com.example.demo.user.dto.GetUserResponse;
import com.example.demo.user.dto.GetUsersResponse;
import com.example.demo.user.dto.UpdateUserRequest;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
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

    @PostMapping
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
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        userService.delete(user.get());
        return ResponseEntity.accepted().build();
    }
}
