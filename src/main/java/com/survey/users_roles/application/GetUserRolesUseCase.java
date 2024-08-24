package com.survey.users_roles.application;

import java.util.List;

import com.survey.users_roles.domain.entity.UserRol;
import com.survey.users_roles.domain.service.UserRolService;

public class GetUserRolesUseCase {
    private UserRolService userRolService;

    public GetUserRolesUseCase (UserRolService userRolService) {
        this.userRolService = userRolService;
    }

    public List<UserRol> execute(int UserId) {
        return userRolService.getUserRoles(UserId);
    }
}
