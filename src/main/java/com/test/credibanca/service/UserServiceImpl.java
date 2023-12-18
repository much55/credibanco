package com.test.credibanca.service;


import com.test.credibanca.entity.User;
import com.test.credibanca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public User createUser(User user) {
        validateUser(user);
        return userRepository.save(user);
    }



    private void validateUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            user.setLastName("");
        }




    }

 }