package com.example.demo.champion.repository;

import com.example.demo.champion.entity.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChampionRepository extends JpaRepository<Champion, Long> {
}
