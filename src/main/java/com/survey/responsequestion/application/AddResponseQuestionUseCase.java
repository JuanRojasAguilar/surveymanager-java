package com.survey.responsequestion.application;

import com.survey.responsequestion.domain.entity.ResponseQuestion;
import com.survey.responsequestion.domain.service.ResponseQuestionService;

public class AddResponseQuestionUseCase {
    private ResponseQuestionService responseQuestionService;

    public AddResponseQuestionUseCase(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public void execute(ResponseQuestion responseQuestion) {
        responseQuestionService.addResponseQuestion(responseQuestion);
    }
}
