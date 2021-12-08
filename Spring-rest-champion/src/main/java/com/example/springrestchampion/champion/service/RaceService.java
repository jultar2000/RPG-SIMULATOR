package com.example.springrestchampion.champion.service;


import com.example.springrestchampion.champion.entity.Race;
import com.example.springrestchampion.champion.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    private RaceRepository raceRepository;

    @Autowired
    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Transactional
    public void add(Race race) {
        raceRepository.save(race);
    }

    @Transactional
    public void delete(String name) {
        raceRepository.deleteById(name);
    }

    @Transactional
    public void update(Race race) {
        raceRepository.save(race);
    }

    public Optional<Race> find(String name) {
        return raceRepository.findById(name);
    }

    public List<Race> findAll(){
        return raceRepository.findAll();
    }
}
