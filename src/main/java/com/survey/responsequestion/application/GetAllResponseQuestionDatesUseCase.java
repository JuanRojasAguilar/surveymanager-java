package com.survey.responsequestion.application;

import java.sql.Timestamp;
import java.util.List;

import com.survey.responsequestion.domain.service.ResponseQuestionService;
import com.survey.survey.domain.entity.Survey;

public class GetAllResponseQuestionDatesUseCase {
    private ResponseQuestionService responseQuestionService;

    public GetAllResponseQuestionDatesUseCase(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public List<Timestamp> execute(Survey survey) {
        return responseQuestionService.getAllResponseQuestionDates(survey);
    }
}
