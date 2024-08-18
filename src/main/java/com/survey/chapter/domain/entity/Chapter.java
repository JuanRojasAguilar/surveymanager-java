package com.survey.chapter.domain.entity;

import java.util.Date;

public class Chapter {
  private int id;
  private int idSurvey;
  private String chapterNumber;
  private String chapterTitle;
  private Date createdAt;
  private Date updatedAt;

  public Chapter() {
  }

  public Chapter(int id, int idSurvey, String chapterNumber, String chapterTitle, Date createdAt) {
    this.setId(id);
    this.setIdSurvey(idSurvey);
    this.setChapterNumber(chapterNumber);
    this.setChapterTitle(chapterTitle);
    this.setCreatedAt(createdAt != null ? createdAt : new Date());
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public int getIdSurvey() {
    return idSurvey;
  }

  public void setIdSurvey(int idSurvey) {
    this.idSurvey = idSurvey;
  }

  public String getChapterNumber() {
    return chapterNumber;
  }

  public void setChapterNumber(String chapterNumber) {
    this.chapterNumber = chapterNumber;
  }

  public String getChapterTitle() {
    return chapterTitle;
  }

  public void setChapterTitle(String chapterTitle) {
    this.chapterTitle = chapterTitle;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

}
