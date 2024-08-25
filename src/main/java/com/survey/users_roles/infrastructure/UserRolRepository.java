package com.survey.users_roles.infrastructure;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.survey.users_roles.domain.entity.UserRol;
import com.survey.users_roles.domain.service.UserRolService;

public class UserRolRepository implements UserRolService {
    private Connection connection;

    public UserRolRepository() {
        try {
        Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | IOException e) {
        e.printStackTrace();
        }
    }

    @Override
    public List<UserRol> getUserRoles(int UserId) {
        String query = "SELECT role_id, user_id FROM users_roles WHERE user_id = ?";
        List<UserRol> userRoles = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, UserId);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    UserRol userRol = new UserRol(rs.getInt("role_id"), rs.getInt("user_id"));
                    userRoles.add(userRol); 
                }
                return userRoles;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRoles;
    }

    @Override
    public void addUserRole(int UserId) {
        String query = "INSERT INTO users_roles(role_id, user_id) VALUES(2, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, UserId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
