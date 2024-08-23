package com.survey.survey.domain.service;

import java.util.List;
import java.util.Optional;

import com.survey.survey.domain.entity.Survey;

public interface SurveyService {
  void add(Survey survey);

  Optional<Survey> searchById(int id);

  Optional<List<Survey>> showAll();

  void update(Survey survey);

  boolean delete(int id);
}
