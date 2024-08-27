package com.survey.responsequestion.infrastructure.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.survey.responsequestion.domain.entity.ResponseQuestion;
import com.survey.responsequestion.domain.entity.ResponseQuestionDto;
import com.survey.responsequestion.domain.service.ResponseQuestionService;
import com.survey.survey.domain.entity.Survey;

public class ResponseQuestionRepository implements ResponseQuestionService{
    private Connection connection;
    
    public ResponseQuestionRepository() {
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
    public void addResponseQuestion(ResponseQuestion responseQuestion) {
        if (responseQuestion.getSubresponse_id() == null) {
            String query = "INSERT INTO response_questions(response_id, responsetext) VALUES (?, ?)";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, responseQuestion.getResponse_id());
                ps.setString(2, responseQuestion.getResponse_text());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "INSERT INTO response_questions(response_id, subresponse_id, responsetext) VALUES (?, ?, ?)";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, responseQuestion.getResponse_id());
                ps.setInt(2, responseQuestion.getSubresponse_id());
                ps.setString(3, responseQuestion.getResponse_text());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Timestamp> getAllResponseQuestionDates(Survey survey) {
        String query = "SELECT DISTINCT rq.create_at " +
                       "FROM surveys AS s " +
                       "JOIN chapter AS c ON s.id = c.survey_id " +
                       "JOIN questions AS q ON c.id = q.chapter_id " +
                       "JOIN response_options AS r ON q.id = r.question_id " +
                       "LEFT JOIN subresponse_options AS sr ON r.id = sr.responseoptions_id " +
                       "JOIN response_questions AS rq ON r.id = rq.response_id " +
                       "WHERE s.id = ?";
        List<Timestamp> dates = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, survey.getId());
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    dates.add(rs.getTimestamp("rq.create_at"));
                }
                return dates;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dates;
    }

    @Override
    public List<ResponseQuestionDto> getAllResponseQuestion(Timestamp createAt, Survey survey) {
        String query = "SELECT c.chapter_title, q.question_text, r.option_text, sr.subresponse_text " +
                       "FROM surveys AS s " +
                       "JOIN chapter AS c ON s.id = c.survey_id " +
                       "JOIN questions AS q ON c.id = q.chapter_id " +
                       "JOIN response_options AS r ON q.id = r.question_id " +
                       "LEFT JOIN subresponse_options AS sr ON r.id = responseoptions_id " +
                       "JOIN response_questions AS rq ON r.id = rq.response_id " +
                       "WHERE s.id = ? AND rq.create_at = ?";
        List<ResponseQuestionDto> responses = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, survey.getId());
            ps.setTimestamp(2, createAt);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    responses.add(new ResponseQuestionDto(rs.getString("c.chapter_title"),
                    rs.getString("q.question_text"),
                    rs.getString("r.option_text"),
                    rs.getString("sr.subresponse_text")));
                }
                return responses;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responses;
    }
    
}
