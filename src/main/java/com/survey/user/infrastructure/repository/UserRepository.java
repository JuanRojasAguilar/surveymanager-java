package com.survey.user.infrastructure.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.survey.user.domain.entity.User;
import com.survey.user.domain.service.UserService;

public class UserRepository implements UserService {
    private Connection connection;

    public UserRepository() {
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
    public boolean addUser(User user) {
        String query = "INSERT INTO users(enable, username, password) VALUES (true, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "usuario ya existente");
            return false;
        }
    }

    @Override
    public Optional<User> searhUser(String username, String password) {
        String query = "SELECT id, enable, username, password FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    User user = new User(rs.getInt("id"), rs.getBoolean("enable"), rs.getString("username"), rs.getString("password"));
                    return Optional.of(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
}
