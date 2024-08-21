package com.survey.chapter.infraestructure.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Date;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;

public class ChapterRepository implements ChapterService {
  private Connection connection;

  public ChapterRepository() {
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
  public void add(Chapter chapter) {
    String sql = "INSERT INTO chapters (name) VALUES (?)";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<Chapter> searchById(int id) {
    String sql = "SELECT id_survey, chapter_number, chapter_title, created_at, updated_at FROM chapters WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.executeUpdate();
      try (ResultSet response = statement.executeQuery()) {
        if (response.next()) {
          int idSurvey = response.getInt("id_survey");
          String chapterNumber = response.getString("chapter_number");
          String chapterTitle = response.getString("chapter_title");
          Date createdAt = response.getDate("created_at");
          Date updatedAt = response.getDate("updatedAt");
          return Optional.of(new Chapter(id, idSurvey, chapterNumber, chapterTitle, createdAt, updatedAt));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<List<Chapter>> showAll(int limit, int offset) {
    String sql = "SELECT id_chapter, id_survey, chapter_number, chapter_title, created_at, updated_at FROM chapters";
    List<Chapter> chapters = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      try (ResultSet response = statement.executeQuery()) {
        while (response.next()) {
          int idChapter = response.getInt("id_chapter");
          int idSurvey = response.getInt("id_survey");
          String chapterNumber = response.getString("chapter_number");
          String chapterTitle = response.getString("chapter_title");
          Date createdAt = response.getDate("created_at");
          Date updatedAt = response.getDate("updatedAt");
          chapters.add(new Chapter(idChapter, idSurvey, chapterNumber, chapterTitle, createdAt, updatedAt));
        }
        return Optional.of(chapters);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }

  @Override
  public void update(Chapter chapter) {
    String sql = "UPDATE TABLE chapters SET id_survey = ?, chapter_number = ?, chapter_title = ? WHERE id = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setInt(1, chapter.getIdSurvey());
      statement.setString(2, chapter.getChapterNumber());
      statement.setString(3, chapter.getChapterTitle());
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean delete(int id) {
    String sql = "DELETE FROM chapteres WHERE id = ?";
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
