package com.example.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public Page<User> findByNameWildcard(String name, Pageable pageable) {
        return userRepository.findByNameWildcard(name, pageable);
    }

    public Page<User> findByAge(int age, Pageable pageable) {
        return userRepository.findByAge(age, pageable);
    }
}
