package com.survey.subresponseOption.infraestructure.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.survey.subresponseOption.domain.entity.SubresponseOption;
import com.survey.subresponseOption.domain.service.SubresponseOptionService;

public class SubresponseOptionRepository implements SubresponseOptionService {
    private Connection connection;

    public SubresponseOptionRepository() {
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
    public void add(SubresponseOption subresponseOption) {
        String sql = "INSERT INTO subresponse_options (responseoptions_id, subresponse_text) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, subresponseOption.getIdResponseOption());
            statement.setString(2, subresponseOption.getSubresponseText());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<SubresponseOption> searchById(int id) {
        String sql = "SELECT responseoptions_id, subresponse_text, create_at, update_at FROM subresponse_options WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            try (ResultSet response = statement.executeQuery()) {
                if (response.next()) {
                    int idResponseOption = response.getInt("responseoptions_id");
                    String subresponseText = response.getString("subresponse_text");
                    Date createdAt = response.getDate("create_at");
                    Date updatedAt = response.getDate("update_at");
                    return Optional.of(new SubresponseOption(id, idResponseOption, subresponseText, createdAt, updatedAt));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<SubresponseOption>> showAll() {
        String sql = "SELECT id, responseoptions_id, subresponse_text, create_at, update_at FROM subresponse_options";
        List<SubresponseOption> subresponseOptions = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            try (ResultSet response = statement.executeQuery()) {
                while (response.next()) {
                    int idSubresponseOption = response.getInt("id");
                    int idResponseOption = response.getInt("responseoptions_id");
                    String subresponseText = response.getString("subresponse_text");
                    Date createdAt = response.getDate("create_at");
                    Date updatedAt = response.getDate("update_at");
                    subresponseOptions.add(new SubresponseOption(idSubresponseOption, idResponseOption, subresponseText, createdAt, updatedAt));
                }
                return Optional.of(subresponseOptions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(SubresponseOption subresponseOption) {
        String sql = "UPDATE response_options SET responseoptions_id = ?, subresponse_text = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, subresponseOption.getIdResponseOption());
            statement.setString(2, subresponseOption.getSubresponseText());
            statement.setInt(3, subresponseOption.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM subresponse_options WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            return (statement.executeUpdate() == 1) ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
