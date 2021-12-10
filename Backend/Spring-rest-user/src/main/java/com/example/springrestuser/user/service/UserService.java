package com.example.springrestuser.user.service;

import com.example.springrestuser.user.action.repository.UserActionRepository;
import com.example.springrestuser.user.entity.User;
import com.example.springrestuser.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserActionRepository userActionRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserActionRepository userActionRepository) {
        this.userRepository = userRepository;
        this.userActionRepository = userActionRepository;
    }

    @Transactional
    public void add(User user) {
        userRepository.save(user);
        userActionRepository.add(user);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
        userActionRepository.delete(user);
    }

    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }

    public Optional<User> find(String login) {
        return userRepository.findById(login);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
