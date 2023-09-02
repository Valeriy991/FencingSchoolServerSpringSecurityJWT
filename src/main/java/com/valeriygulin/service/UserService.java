package com.valeriygulin.service;

import com.valeriygulin.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    User get(String login, String password);


    User get(Long id);

    User delete(long id);
}
