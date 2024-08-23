package com.survey.survey.application;

import java.util.Optional;

import com.survey.survey.domain.entity.Survey;
import com.survey.survey.domain.service.SurveyService;

public class SearchSurveyByIdUseCase {
  private SurveyService surveyService;

  public SearchSurveyByIdUseCase(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  public Optional<Survey> execute(int id) {
    return this.surveyService.searchById(id);
  }
}
