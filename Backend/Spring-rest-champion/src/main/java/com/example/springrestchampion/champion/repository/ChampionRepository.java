package com.example.springrestchampion.champion.repository;

import com.example.springrestchampion.champion.entity.Champion;
import com.example.springrestchampion.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ChampionRepository extends JpaRepository<Champion, Long> {

    List<Champion> findAllByChampionUser(User user);

    Optional<Champion> findByIdAndChampionUser(Long id, User user);

}