package com.example.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserJPAService {
    @Autowired
    private final UserJPARepository userJPARepository;

    public UserJPAService(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    public void saveUser(UserJPA user) {
        userJPARepository.save(user);
    }

    public void deleteUser(UserJPA user) {
        userJPARepository.delete(user);
    }

    public List<UserJPA> getAllUsers() {
        return userJPARepository.findAll();
    }

    public UserJPA getUserById(String id) {
        return userJPARepository.findById(id).orElse(null);
    }

    public List<UserJPA> getUserByName(String name) {
        return userJPARepository.findByName(name);
    }

    public List<UserJPA> findByNameContaining(String name) {
        return userJPARepository.findByNameContaining(name);
    }
}
