package com.survey.question.domain.entity;

public class Question {
  private int id;
  private String questionNumber;
  private String responseType;
  private String commentQuestion;
  private String questionText;

  public Question() {
  }

  public Question(int id, String questionNumber, String responseType, String commentQuestion, String questionText) {
    this.setId(id);
    this.setQuestionNumber(questionNumber);
    this.setResponseType(responseType);
    this.setCommentQuestion(commentQuestion);
    this.setQuestionText(questionText);
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void setQuestionNumber(String questionNumber) {
    this.questionNumber = questionNumber;
  }

  public String getQuestionNumber() {
    return this.questionNumber;
  }

  public void setResponseType(String responseType) {
    this.responseType = responseType;
  }

  public String getResponseType() {
    return this.responseType;
  }

  public void setCommentQuestion(String commentQuestion) {
    this.commentQuestion = commentQuestion;
  }

  public String getCommentQuestion() {
    return this.commentQuestion;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  public String getQuestionText() {
    return this.questionText;
  }
}
