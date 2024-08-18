package com.survey.question.application;

import com.survey.question.domain.service.QuestionService;

public class DeleteQuestionUseCase {
  private QuestionService questionService;

  public DeleteQuestionUseCase(QuestionService questionService) {
    this.questionService = questionService;
  }

  public boolean execute(int id) {
    return this.questionService.delete(id);
  }
}
