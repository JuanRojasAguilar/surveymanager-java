package com.survey.users_roles.domain.entity;

public class UserRol {
    private int rolId;
    private int userId;

    
    public UserRol() {
    }

    public UserRol(int rolId, int userId) {
        this.rolId = rolId;
        this.userId = userId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
