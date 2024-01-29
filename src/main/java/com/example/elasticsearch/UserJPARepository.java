package com.example.elasticsearch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJPARepository extends JpaRepository<UserJPA, String> {
    List<UserJPA> findByName(String name);
    List<UserJPA> findByNameContaining(String name);
}
