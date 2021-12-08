package com.example.springrestchampion.user.controller;

import com.example.springrestchampion.user.dto.CreateUserRequest;
import com.example.springrestchampion.user.entity.User;
import com.example.springrestchampion.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request) {
        User user = CreateUserRequest.dtoToEntityMapper(request);
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
