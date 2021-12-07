package com.example.demo.champion.service;

import com.example.demo.champion.entity.Champion;
import com.example.demo.champion.repository.ChampionRepository;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChampionService {

    private ChampionRepository championRepository;

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

    public List<Champion> findAll(){
        return championRepository.findAll();
    }











}
