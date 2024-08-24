package com.survey.question.infraestructure.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.survey.question.domain.entity.Question;
import com.survey.question.domain.service.QuestionService;

public class QuestionRepository implements QuestionService {
  private Connection connection;

  public QuestionRepository() {
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
  public void add(Question question) {
    String sql = "INSERT INTO questions (chapter_id, question_number, response_type, comment_question, question_text) VALUES (?, ?, ?, ?, ?)";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, question.getIdChapter());
      statement.setString(2, question.getQuestionNumber());
      statement.setString(3, question.getResponseType());
      statement.setString(4, question.getCommentQuestion());
      statement.setString(5, question.getQuestionText());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Question> searchById(int id) {
    String sql = "SELECT chapter_id, question_number, response_type, comment_question, question_text FROM questions WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.executeUpdate();
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          int idChapter = response.getInt("chapter_id");
          String questionNumber = response.getString("question_number");
          String responseType = response.getString("response_type");
          String commentQuestion = response.getString("comment_question");
          String questionText = response.getString("question_text");
          return Optional.of(new Question(id, idChapter, questionNumber, responseType, commentQuestion, questionText));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<Question>> showAll(int limit, int offset) {
    String sql = "SELECT id, chapter_id, question_number, response_type, comment_question, question_text, created_at, update_at FROM questions";
    List<Question> questions = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.executeQuery();
      try (ResultSet response = statement.executeQuery()) {
        while (response.next()) {
          int idQuestion = response.getInt("id");
          int idChapter = response.getInt("chapter_id");
          String questionNumber = response.getString("question_number");
          String responseType = response.getString("response_type");
          String commentQuestion = response.getString("comment_question");
          String questionText = response.getString("question_text");
          Date create_at = response.getDate("created_at");
          Date updated_at = response.getDate("update_at");
          questions.add(new Question(idQuestion, idChapter, questionNumber, responseType, commentQuestion, questionText, create_at, updated_at));
        }
        return Optional.of(questions);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public void update(Question question) {
    String sql = "UPDATE questions SET chapter_id = ?, question_number = ?, response_type = ?, comment_question = ?, question_text = ? WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, question.getIdChapter());
      statement.setString(2, question.getQuestionNumber());
      statement.setString(3, question.getResponseType());
      statement.setString(4, question.getCommentQuestion());
      statement.setString(5, question.getQuestionText());
      statement.setInt(6, question.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean delete(int id) {
    String sql = "DELETE FROM questions WHERE id = ?";
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
