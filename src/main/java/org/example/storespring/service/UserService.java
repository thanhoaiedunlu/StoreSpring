package org.example.storespring.service;


import org.example.storespring.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User saveUser(User user);

    User updateUser(User user);

    void deleteUserById(String id);

    User GetUserByEmail(String email);

    org.example.storespring.entity.User findByIdAndRole(String id, String role);

    List<User> findAll();
}
