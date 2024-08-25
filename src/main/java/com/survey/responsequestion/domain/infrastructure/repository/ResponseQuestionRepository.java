package com.survey.responsequestion.domain.infrastructure.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.survey.responsequestion.domain.entity.ResponseQuestion;
import com.survey.responsequestion.service.ResponseQuestionService;

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
    
}
