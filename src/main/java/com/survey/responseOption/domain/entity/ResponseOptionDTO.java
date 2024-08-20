package com.survey.responseOption.domain.entity;

import java.util.Date;

public class ResponseOptionDTO {
  private int id;
  private String categoryCatalog;
  private String parentResponse;
  private String question;
  private String commentResponse;
  private String optionText;
  private Date createdAt;
  private Date updatedAt;

  public ResponseOptionDTO() {}

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void setIdCategoryCatalog(String categoryCatalog) {
    this.categoryCatalog = categoryCatalog;
  }

  public String getCategoryCatalog() {
    return this.categoryCatalog;
  }

  public void setParentResponse(String parentResponse) {
    this.parentResponse = parentResponse;
  }

  public String getParentResponse() {
    return this.parentResponse;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getQuestion() {
    return this.question;
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
