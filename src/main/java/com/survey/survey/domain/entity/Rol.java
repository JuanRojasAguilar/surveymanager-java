package com.survey.rol.domain.entity;

public class Rol {
  private int id;
  private String name;

  public Rol() {}

  public Rol(String name) {
    this.setName(name);
  }

  public Rol(int id, String name) {
    this.setId(id);
    this.setName(name);
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
}
