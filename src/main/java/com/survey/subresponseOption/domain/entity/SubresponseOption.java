package com.survey.subresponseOption.domain.entity;

import java.util.Date;

public class SubresponseOption {
    private int id;
    private int idResponseOptions;
    private String subresponseText;
    private Date createdAt;
    private Date updatedAt;

    public SubresponseOption() {}

    public SubresponseOption(int id, int idResponseOptions, String subresponseText, Date createdAt, Date updatedAt) {
        this.setId(id);
        this.setIdResponseOptions(idResponseOptions);
        this.setSubresponseText(subresponseText);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
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

    public int getIdResponseOptions() {
        return idResponseOptions;
    }

    public void setIdResponseOptions(int idResponseOptions) {
        this.idResponseOptions = idResponseOptions;
    }

    public String getSubresponseText() {
        return subresponseText;
    }

    public void setSubresponseText(String subresponseText) {
        this.subresponseText = subresponseText;
    }
}
