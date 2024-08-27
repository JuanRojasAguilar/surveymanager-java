package com.survey.responsequestion.application;

import java.sql.Timestamp;
import java.util.List;

import com.survey.responsequestion.domain.entity.ResponseQuestionDto;
import com.survey.responsequestion.domain.service.ResponseQuestionService;
import com.survey.survey.domain.entity.Survey;

public class GetAllResponseQuestionUseCase {
    private ResponseQuestionService responseQuestionService;
    
    public GetAllResponseQuestionUseCase (ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public List<ResponseQuestionDto> execute(Timestamp date, Survey survey) {
        return responseQuestionService.getAllResponseQuestion(date, survey);
    }
}
