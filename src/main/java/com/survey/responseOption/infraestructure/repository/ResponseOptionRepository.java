package com.survey.responseOption.infraestructure.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
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
    String sql = "INSERT INTO subresponse_options (id_category_catalog, id_parent_response, id_question, comment_response, option_text) VALUES (?,?,?,?,?)";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, responseOption.getIdCategoryCatalog());
      statement.setInt(2, responseOption.getIdParentResponse());
      statement.setInt(3, responseOption.getIdQuestion());
      statement.setString(4, responseOption.getCommentResponse());
      statement.setString(5, responseOption.getOptionText());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<ResponseOption> searchById(int id) {
    String sql = "SELECT id_category_catalog, id_parent_response, id_question, comment_response, option_text, created_at, updated_at FROM subresponse_options WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          int idCategoryCatalog = response.getInt("id_category_catalog");
          int idParentResponse = response.getInt("id_parent_response");
          int idQuestion = response.getInt("id_question");
          String commentResponse = response.getString("comment_response");
          String optionText = response.getString("option_text");
          Date createdAt = response.getDate("created_at");
          Date updatedAt = response.getDate("updatedAt");
          return Optional.of(new ResponseOption(id, idCategoryCatalog, idParentResponse, idQuestion, commentResponse, optionText, createdAt, updatedAt));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<ResponseOption>> showAll() {
    String sql = "SELECT id_response_option ,id_question, id_category_catalog, id_parent_response, id_question, comment_response, option_text, created_at, updated_at FROM subresponse_options WHERE id = ?";
    List<ResponseOption> responseOptions = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          int idResponseOption = response.getInt("id_response_option");
          int idCategoryCatalog = response.getInt("id_category_catalog");
          int idParentResponse = response.getInt("id_parent_response");
          int idQuestion = response.getInt("id_question");
          String commentResponse = response.getString("comment_response");
          String optionText = response.getString("option_text");
          Date createdAt = response.getDate("created_at");
          Date updatedAt = response.getDate("updatedAt");
          responseOptions.add(new ResponseOption(idResponseOption, idCategoryCatalog, idParentResponse, idQuestion, commentResponse, optionText, createdAt, updatedAt));
        }
        return Optional.of(responseOptions);
      }
    } catch (SQLException e) {
        e.printStackTrace();
        return Optional.empty();
    }
  }

  @Override
  public void update(ResponseOption responseOption) {
    String sql = "UPDATE TABLE response_options SET id_question = ?, id_category_catalog = ?, id_parent_response = ?, id_question = ?, comment_response = ?, option_text = ?, created_at = ?, updated_at = ? WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, responseOption.getIdQuestion());
      statement.setInt(2, responseOption.getIdCategoryCatalog());
      statement.setInt(3, responseOption.getIdParentResponse());
      statement.setInt(4, responseOption.getIdQuestion());
      statement.setString(5, responseOption.getCommentResponse());
      statement.setString(6, responseOption.getOptionText());
      statement.setDate(7, new java.sql.Date(responseOption.getCreatedAt().getTime()));
      statement.setDate(8, new java.sql.Date(responseOption.getUpdatedAt().getTime()));
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean delete(int id) {
    String sql = "DELETE FROM response_options WHERE id = ?";
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
