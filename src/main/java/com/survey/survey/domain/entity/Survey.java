package com.survey.survey.domain.entity;

import java.util.Date;

public class Survey {
    private int id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    public Survey() {}

    public Survey(String name) {
        this.setName(name);
    }

    public Survey(int id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public Survey(int id, String name, String description, Date createdAt, Date updatedAt) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
      return this.name;
    }
}
