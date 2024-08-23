package com.survey.survey.application;

import com.survey.survey.domain.entity.Survey;
import com.survey.survey.domain.service.SurveyService;

public class UpdateSurveyUseCase {
  private SurveyService surveyService;

  public UpdateSurveyUseCase(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  public void execute(Survey survey) {
    this.surveyService.update(survey);
  }
}
