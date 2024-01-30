package com.example.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
    List<User> findByName(String name);
    @Query("{\"wildcard\":{\"name.keyword\":\"*?0*\"}}")
    List<User> findByNameWildcard(String name);

    @Query("{\"wildcard\":{\"age.keyword\":\"*?0*\"}}")
    List<User> findByAgeWildcard(String age);

//    @Query("{\"wildcard\":{\"age.keyword\":\"*?0*\"}}")
//    Page<User> findByAgeWildcard(int age, Pageable pageable);
}
