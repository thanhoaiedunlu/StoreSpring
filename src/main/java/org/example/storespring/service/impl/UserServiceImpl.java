package org.example.storespring.service.impl;


import org.example.storespring.entity.User;
import org.example.storespring.repository.UserRepository;
import org.example.storespring.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User GetUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public org.example.storespring.entity.User findByIdAndRole(String id, String role) {
        return userRepository.findByIdAndRole(id, role);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
