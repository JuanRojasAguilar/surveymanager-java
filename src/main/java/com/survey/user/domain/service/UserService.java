package com.survey.user.domain.service;

import java.util.Optional;

import com.survey.user.domain.entity.User;

public interface UserService {
    boolean addUser(User user);
    Optional<User> searhUser(String username, String password);
}
