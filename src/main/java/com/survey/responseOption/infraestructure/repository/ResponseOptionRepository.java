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
      if (responseOption.getIdParentResponse() == 0 && responseOption.getIdCategoryCatalog() == 0) {
        String sql = "INSERT INTO response_options (question_id, comment_response, option_text) VALUES (?,?,?)";
        try {
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, responseOption.getIdQuestion());
          statement.setString(2, responseOption.getCommentResponse());
          statement.setString(3, responseOption.getOptionText());
          statement.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } else if (responseOption.getIdParentResponse() == 0) {
        String sql = "INSERT INTO response_options (question_id, comment_response, option_text, categorycatalog_id) VALUES (?,?,?,?)";
        try {
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, responseOption.getIdQuestion());
          statement.setString(2, responseOption.getCommentResponse());
          statement.setString(3, responseOption.getOptionText());
          statement.setInt(4, responseOption.getIdCategoryCatalog());
          statement.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } else if (responseOption.getIdCategoryCatalog() == 0) {
        String sql = "INSERT INTO response_options (question_id, comment_response, option_text, parentresponse_id) VALUES (?,?,?,?)";
        try {
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, responseOption.getIdQuestion());
          statement.setString(2, responseOption.getCommentResponse());
          statement.setString(3, responseOption.getOptionText());
          statement.setInt(4, responseOption.getIdParentResponse());
          statement.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } else {
        String sql = "INSERT INTO response_options (question_id, comment_response, option_text, parentresponse_id, categorycatalog_id) VALUES (?,?,?,?,?)";
        try {
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, responseOption.getIdQuestion());
          statement.setString(2, responseOption.getCommentResponse());
          statement.setString(3, responseOption.getOptionText());
          statement.setInt(4, responseOption.getIdParentResponse());
          statement.setInt(5, responseOption.getIdCategoryCatalog());
          statement.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

  @Override
  public Optional<ResponseOption> searchById(int id) {
    String sql = "SELECT categorycatalog_id, parentresponse_id, question_id, comment_response, option_text, subresponse_type, create_at, update_at FROM response_options WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          int idCategoryCatalog = response.getInt("categorycatalog_id");
          int idParentResponse = response.getInt("parentresponse_id");
          int idQuestion = response.getInt("question_id");
          String commentResponse = response.getString("comment_response");
          String optionText = response.getString("option_text");
          String subResponseType = response.getString("subresponse_type");
          Date createdAt = response.getDate("create_at");
          Date updatedAt = response.getDate("update_At");
          return Optional.of(new ResponseOption(id, idCategoryCatalog, idParentResponse, idQuestion, commentResponse, optionText, subResponseType, createdAt, updatedAt));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<ResponseOption>> showAll() {
    String sql = "SELECT id, categorycatalog_id, parentresponse_id, question_id, comment_response, subresponse_type, option_text, create_at, update_at FROM response_options";
    List<ResponseOption> responseOptions = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      try (ResultSet response = statement.executeQuery()) {
        while (response.next()) {
          int idResponseOption = response.getInt("id");
          int idCategoryCatalog = response.getInt("categorycatalog_id");
          int idParentResponse = response.getInt("parentresponse_id");
          int idQuestion = response.getInt("question_id");
          String commentResponse = response.getString("comment_response");
          String optionText = response.getString("option_text");
          String subResponseType = response.getString("subresponse_type");
          Date createdAt = response.getDate("create_at");
          Date updatedAt = response.getDate("update_At");
          responseOptions.add(new ResponseOption(idResponseOption, idCategoryCatalog, idParentResponse, idQuestion, commentResponse, optionText, subResponseType, createdAt, updatedAt));
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
    String sql = "UPDATE response_options SET categorycatalog_id = ?, parentresponse_id = ?, question_id = ?, comment_response = ?, option_text = ? WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, responseOption.getIdCategoryCatalog());
      statement.setInt(2, responseOption.getIdParentResponse());
      statement.setInt(3, responseOption.getIdQuestion());
      statement.setString(4, responseOption.getCommentResponse());
      statement.setString(5, responseOption.getOptionText());
      statement.setInt(6, responseOption.getId());
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
