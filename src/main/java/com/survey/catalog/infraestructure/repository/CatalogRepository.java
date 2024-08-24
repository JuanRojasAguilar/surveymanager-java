package com.survey.catalog.infraestructure.repository;

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

import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;

public class CatalogRepository implements CatalogService {
  private Connection connection;

  public CatalogRepository() {
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
  public void add(Catalog catalog) {
    String sql = "INSERT INTO catalogs (name) VALUES (?)";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, catalog.getName());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Catalog> searchById(int id) {
    String sql = "SELECT id, name, create_at, update_at FROM catalogs WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.executeUpdate();
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          String name = response.getString("name");
          Date createdAt = response.getDate("create_at");
          Date updatedAt = response.getDate("update_at");
          return Optional.of(new Catalog(id, name, createdAt, updatedAt));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<Catalog>> showAll(int offset, int limit) {
    List<Catalog> catalogs = new ArrayList<>();
    String sql = "SELECT id, name, create_at, update_at FROM catalogs LIMIT ?, ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, limit);
      statement.setInt(2, offset);
      try (ResultSet response = statement.executeQuery()) {
        while (response.next()) {
          int idCatalog = response.getInt("id");
          String name = response.getString("name");
          Date createdAt = response.getDate("create_at");
          Date updatedAt = response.getDate("update_at");
          catalogs.add(new Catalog(idCatalog, name, createdAt, updatedAt));
        }
        return Optional.of(catalogs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }

  @Override
  public void update(Catalog catalog) {
    String sql = "UPDATE catalogs SET name = ? WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, catalog.getName());
      statement.setInt(2, catalog.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean delete(int id) {
    String sql = "DELETE FROM catalogs WHERE id = ?";
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
