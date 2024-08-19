package com.survey.responseOption.domain.entity;

import java.util.Date;

public class ResponseOption {
  private int id;
  private int idCategoryCatalog;
  private int idParentResponse;
  private int idQuestion;
  private String commentResponse;
  private String optionText;
  private Date createdAt;
  private Date updatedAt;

  public ResponseOption() {}

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void setIdCategoryCatalog(int idCategoryCatalog) {
    this.idCategoryCatalog = idCategoryCatalog;
  }

  public int getIdCategoryCatalog() {
    return this.idCategoryCatalog;
  }

  public void setIdParentResponse(int idParentResponse) {
    this.idParentResponse = idParentResponse;
  }

  public int getIdParentResponse() {
    return this.idParentResponse;
  }

  public void setIdQuestion(int idQuestion) {
    this.idQuestion = idQuestion;
  }

  public int getIdQuestion() {
    return this.idQuestion;
  }

  public void setCommentResponse(String commentResponse) {
    this.commentResponse = commentResponse;
  }

  public String getCommentResponse() {
    return this.commentResponse;
  }

  public void setOptionText(String optionText) {
    this.optionText = optionText;
  }

  public String getOptionText() {
    return this.optionText;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getUpdatedAt() {
    return this.updatedAt;
  }
}
