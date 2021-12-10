package com.example.springrestchampion.champion.service;

import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.champion.repository.ChampionRepository;
import com.example.springrestchampion.user.entity.User;
import com.example.springrestchampion.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChampionService {

    private ChampionRepository championRepository;

    @Autowired
    public ChampionService(ChampionRepository championRepository, UserRepository userRepository) {
        this.championRepository = championRepository;
    }

    @Transactional
    public void add(Champion champion) {
        championRepository.save(champion);
    }

    @Transactional
    public void delete(Long championId) {
        championRepository.deleteById(championId);
    }

    @Transactional
    public void update(Champion champion) {
        championRepository.save(champion);
    }

    public Optional<Champion> find(Long id){
        return championRepository.findById(id);
    }

    public Optional<Champion> find(Long id, User user){
        return championRepository.findByIdAndUser(id, user);
    }

    public List<Champion> findAll(){
        return championRepository.findAll();
    }

    public List<Champion> findAll(User user){
        return championRepository.findAllByUser(user);
    }
}
