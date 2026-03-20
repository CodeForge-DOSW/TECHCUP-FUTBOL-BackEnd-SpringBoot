package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.User;

import java.util.List;

public interface IUserService {

    // CREATE
    User createUser(User user);

    // READ
    User getUser(Long id);
    List<User> getAllUsers();

    // UPDATE
    User updateUser(Long id, User updatedUser);

    // DEACTIVATE 
    void deactivateUser(Long id);

    // AUTH
    void authenticate(String email, String password);
}