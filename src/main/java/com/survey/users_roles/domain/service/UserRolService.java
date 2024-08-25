package com.survey.users_roles.domain.service;

import java.util.List;

import com.survey.users_roles.domain.entity.UserRol;

public interface UserRolService {
    List<UserRol> getUserRoles(int UserId);
    void addUserRole(int UserId);
}
