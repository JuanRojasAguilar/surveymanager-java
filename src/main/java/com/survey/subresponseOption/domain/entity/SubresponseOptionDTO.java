package com.survey.subresponseOption.domain.entity;

import java.util.Date;

public class SubresponseOptionDTO {
  private int id;
  private String categoryCatalog;
  private String parentSubresponse;
  private String question;
  private String commentSubresponse;
  private String optionText;
  private Date createdAt;
  private Date updatedAt;

  public SubresponseOptionDTO() {}

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

  public void setParentSubresponse(String parentSubresponse) {
    this.parentSubresponse = parentSubresponse;
  }

  public String getParentSubresponse() {
    return this.parentSubresponse;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getQuestion() {
    return this.question;
  }

  public void setCommentSubresponse(String commentSubresponse) {
    this.commentSubresponse = commentSubresponse;
  }

  public String getCommentSubresponse() {
    return this.commentSubresponse;
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
