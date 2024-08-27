package com.survey.responsequestion.domain.service;

import java.sql.Timestamp;
import java.util.List;

import com.survey.responsequestion.domain.entity.ResponseQuestion;
import com.survey.responsequestion.domain.entity.ResponseQuestionDto;
import com.survey.survey.domain.entity.Survey;

public interface ResponseQuestionService {
    void addResponseQuestion(ResponseQuestion responseQuestion);
    List<Timestamp> getAllResponseQuestionDates(Survey survey); 
    List<ResponseQuestionDto> getAllResponseQuestion(Timestamp createAt, Survey survey);
}
