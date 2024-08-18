package com.survey.question.application;

import com.survey.question.domain.entity.Question;
import com.survey.question.domain.service.QuestionService;

public class AddQuestionUseCase {
  private QuestionService questionService;

  public AddQuestionUseCase(QuestionService questionService) {
    this.questionService = questionService;
  }

  public void execute(Question question) {
    this.questionService.add(question);
  }
}
