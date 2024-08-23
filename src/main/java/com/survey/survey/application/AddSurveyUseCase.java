package com.survey.survey.application;

import com.survey.survey.domain.entity.Survey;
import com.survey.survey.domain.service.SurveyService;

public class AddSurveyUseCase {
  private SurveyService surveyService;

  public AddSurveyUseCase(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  public void execute(Survey survey) {
    this.surveyService.add(survey);
  }
}
