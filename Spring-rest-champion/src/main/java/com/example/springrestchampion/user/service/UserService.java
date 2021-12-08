package com.example.springrestchampion.user.service;

import com.example.springrestchampion.user.entity.User;
import com.example.springrestchampion.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void add(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }

    public Optional<User> find(String login) {
        return userRepository.findById(login);
    }
}
