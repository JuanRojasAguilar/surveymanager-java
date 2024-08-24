package com.survey.user.domain.entity;

public class User {
    private int id;
    private boolean enable;
    private String username;
    private String password;

    public User() {
    }

    public User(int id, boolean enable, String username, String password) {
        this.id = id;
        this.enable = enable;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
