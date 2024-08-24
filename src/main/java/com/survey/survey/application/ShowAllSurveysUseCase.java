package com.survey.survey.application;

import java.util.List;
import java.util.Optional;

import com.survey.survey.domain.entity.Survey;
import com.survey.survey.domain.service.SurveyService;

public class ShowAllSurveysUseCase {
  private SurveyService surveyService;

  public ShowAllSurveysUseCase(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  public Optional<List<Survey>> execute() {
    return this.surveyService.showAll();
  }
}
