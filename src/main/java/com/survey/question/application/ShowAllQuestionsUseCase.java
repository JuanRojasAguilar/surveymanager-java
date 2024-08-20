package com.survey.question.application;

import java.util.List;
import java.util.Optional;

import com.survey.question.domain.entity.Question;
import com.survey.question.domain.service.QuestionService;

public class ShowAllQuestionsUseCase {
  private QuestionService questionService;

  public ShowAllQuestionsUseCase(QuestionService questionService) {
    this.questionService = questionService;
  }

  public Optional<List<Question>> execute(int limit, int offset) {
    return this.questionService.showAll(limit, offset);
  }
}
