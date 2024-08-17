package com.survey.catalog.domain.entity;

import java.util.Date;

public class Catalog {
  private int id;

  private Date createdAt;
  private Date updatedAt;

  private String name;

  public Catalog() {
  }

  public Catalog(String name) {
    this.setName(name);
  }

  public Catalog(int id, String name, Date createdAt, Date updatedAt) {
    this.setId(id);
    this.setName(name);
    this.setCreatedAt(createdAt);
    this.setUpdatedAt(updatedAt);
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
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

  public void refreshUpdateDate() {
    this.updatedAt = (Date) new java.util.Date();
  }
}
