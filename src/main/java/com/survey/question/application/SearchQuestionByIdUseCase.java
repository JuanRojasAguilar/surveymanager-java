package com.survey.question.application;

import java.util.Optional;

import com.survey.question.domain.entity.Question;
import com.survey.question.domain.service.QuestionService;

public class SearchQuestionByIdUseCase {
  private QuestionService questionService;

  public SearchQuestionByIdUseCase(QuestionService questionService) {
    this.questionService = questionService;
  }

  public Optional<Question> execute(int id) {
    return this.questionService.searchById(id);
  }
}
