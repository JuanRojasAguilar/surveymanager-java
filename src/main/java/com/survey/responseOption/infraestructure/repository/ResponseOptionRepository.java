package com.survey.responseOption.infraestructure.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.domain.service.ResponseOptionService;

public class ResponseOptionRepository implements ResponseOptionService {
  private Connection connection;

  public ResponseOptionRepository() {
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
  public void add(ResponseOption responseOption) {
    String sql = "INSERT INTO responseOptiones (name) VALUES (?)";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, responseOption.getName());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<ResponseOption> searchById(int id) {
    String sql = "SELECT name FROM responseOptiones WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.executeUpdate();
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          String name = response.getString("name");
          return Optional.of(new ResponseOption(name));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<ResponseOption>> showAll() {
    throw new UnsupportedOperationException("Unimplemented method 'showAll'");
  }

  @Override
  public void update(ResponseOption responseOption) {
    String sql = "UPDATE TABLE responseOptiones SET name = ? WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, responseOption.getName());
      statement.setInt(2, responseOption.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean delete(int id) {
    String sql = "DELETE FROM responseOptiones WHERE id = ?";
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
