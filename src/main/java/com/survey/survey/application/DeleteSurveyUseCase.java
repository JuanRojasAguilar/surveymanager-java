package com.survey.survey.application;

import com.survey.survey.domain.service.SurveyService;

public class DeleteSurveyUseCase {
  private SurveyService surveyService;

  public DeleteSurveyUseCase(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  public boolean execute(int id) {
    return this.surveyService.delete(id);
  }
}
