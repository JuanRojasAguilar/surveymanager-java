package com.survey.user.application;

import java.util.Optional;

import com.survey.user.domain.entity.User;
import com.survey.user.domain.service.UserService;

public class SearchUserUseCase {
    private UserService userService;

    public SearchUserUseCase (UserService userService) {
        this.userService = userService;
    }

    public Optional<User> execute(String username, String password) {
        return userService.searhUser(username, password);
    }
}
