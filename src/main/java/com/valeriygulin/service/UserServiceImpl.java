package com.valeriygulin.service;

import com.valeriygulin.model.User;
import com.valeriygulin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("User is already exist!");
        }
    }

    @Override
    public User get(String login, String password) {
        return this.userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public User get(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User doesn't exist!"));
    }

    @Override
    public User delete(long id) {
        User user = this.get(id);
        this.userRepository.deleteById(id);
        return user;
    }
}
