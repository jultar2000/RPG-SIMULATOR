package com.example.springrestuser.user.action.repository;


import com.example.springrestuser.user.action.dto.CreateUserRequest;
import com.example.springrestuser.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserActionRepository {

    RestTemplate restTemplate;

    @Autowired
    public UserActionRepository(@Value("${champions.url}") String url) {
        restTemplate = new RestTemplateBuilder().rootUri(url).build();
    }

    public void delete(User user){
        restTemplate.delete("/users/{login}",user.getLogin());
    }

    public void add(User user){
        restTemplate.postForLocation("/users", CreateUserRequest.entityToDtoMapper(user));
    }
}
