package com.survey.question.application;

import com.survey.question.domain.entity.Question;
import com.survey.question.domain.service.QuestionService;

public class UpdateQuestionUseCase {
  private QuestionService questionService;

  public UpdateQuestionUseCase(QuestionService questionService) {
    this.questionService = questionService;
  }

  public void execute(Question question) {
    this.questionService.update(question);
  }
}
