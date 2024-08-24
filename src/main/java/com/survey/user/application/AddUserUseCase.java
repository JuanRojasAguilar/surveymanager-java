package com.survey.user.application;

import com.survey.user.domain.entity.User;
import com.survey.user.domain.service.UserService;

public class AddUserUseCase {
    private UserService userService;

    public AddUserUseCase (UserService userService) {
        this.userService = userService;
    }

    public boolean execute(User user) {
        return userService.addUser(user);
    } 
}
