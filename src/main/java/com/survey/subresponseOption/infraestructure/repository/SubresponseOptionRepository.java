package com.survey.subresponseOption.infraestructure.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
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
    String sql = "INSERT INTO response_options (name) VALUES (?)";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, subresponseOption);
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<SubresponseOption> searchById(int id) {
    String sql = "SELECT subresponse_number FROM response_options WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.executeUpdate();
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          String name = response.getString("name");
          return Optional.of(new SubresponseOption());
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<SubresponseOption>> showAll() {
    throw new UnsupportedOperationException("Unimplemented method 'showAll'");
  }

  @Override
  public void update(SubresponseOption subresponseOption) {
    String sql = "UPDATE TABLE response_Options SET name = ? WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, subresponseOption.getName());
      statement.setInt(2, subresponseOption.getId());
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
