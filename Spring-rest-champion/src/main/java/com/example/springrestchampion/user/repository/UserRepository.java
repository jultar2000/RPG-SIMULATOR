package com.example.springrestchampion.user.repository;

import com.example.springrestchampion.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {



}
