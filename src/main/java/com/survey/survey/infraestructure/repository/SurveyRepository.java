package com.survey.survey.infraestructure.repository;

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

import com.survey.survey.domain.entity.Survey;
import com.survey.survey.domain.service.SurveyService;

public class SurveyRepository implements SurveyService {
  private Connection connection;

  public SurveyRepository() {
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
  public void add(Survey survey) {
    String sql = "INSERT INTO surveys (name, description) VALUES (?, ?)";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, survey.getName());
      statement.setString(2, survey.getDescription());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Survey> searchById(int id) {
    String sql = "SELECT name, description, created_at, update_at FROM surveys WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.executeQuery();
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          String name = response.getString("name");
          String description = response.getString("description");
          Date createdAt = response.getDate("created_at");
          Date updatedAt = response.getDate("update_at");
          return Optional.of(new Survey(id, name, description, createdAt, updatedAt));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<Survey>> showAll() {
    String sql = "SELECT id, name, description, created_at, update_at FROM surveys";
    List<Survey> surveys = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.executeQuery();
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          int id = response.getInt("id");
          String name = response.getString("name");
          String description = response.getString("description");
          Date createdAt = response.getDate("created_at");
          Date updatedAt = response.getDate("update_at");
          surveys.add(new Survey(id, name, description, createdAt, updatedAt));
        }
        return Optional.of(surveys);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public void update(Survey survey) {
    String sql = "UPDATE surveys SET name = ?, description = ? WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, survey.getName());
      statement.setString(2, survey.getDescription());
      statement.setInt(3, survey.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean delete(int id) {
    String sql = "DELETE FROM surveys WHERE id = ?";
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
