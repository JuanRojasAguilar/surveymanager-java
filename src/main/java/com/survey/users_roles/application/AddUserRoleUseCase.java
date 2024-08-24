package com.survey.users_roles.application;

import com.survey.users_roles.domain.service.UserRolService;

public class AddUserRoleUseCase {
    private UserRolService userRolService;

    public AddUserRoleUseCase(UserRolService userRolService) {
        this.userRolService = userRolService;
    }

    public void execute(int userId) {
        userRolService.addUserRole(userId);
    }
}
