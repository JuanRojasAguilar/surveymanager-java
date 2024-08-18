package com.survey.rol.infraestructure.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.survey.rol.domain.entity.Rol;
import com.survey.rol.domain.service.RolService;

public class RolRepository implements RolService {
  private Connection connection;

  public RolRepository() {
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
  public void add(Rol rol) {
    String sql = "INSERT INTO roles (name) VALUES (?)";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, rol.getName());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Rol> searchById(int id) {
    String sql = "SELECT name FROM roles WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.executeUpdate();
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          String name = response.getString("name");
          return Optional.of(new Rol(name));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<Rol>> showAll() {
    throw new UnsupportedOperationException("Unimplemented method 'showAll'");
  }

  @Override
  public void update(Rol rol) {
    String sql = "UPDATE TABLE roles SET name = ? WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, rol.getName());
      statement.setInt(2, rol.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean delete(int id) {
    String sql = "DELETE FROM roles WHERE id = ?";
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
