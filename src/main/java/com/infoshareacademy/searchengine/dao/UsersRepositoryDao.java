package com.infoshareacademy.searchengine.dao;

import com.infoshareacademy.searchengine.domain.User;

import java.util.List;

public interface UsersRepositoryDao {
    void addUser(User user);
    User getUserById(int id);
    User getUserByLogin(String login);
    List<User> getUsersList();
}
