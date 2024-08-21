package com.survey.question.domain.entity;

import java.util.Date;

public class Question {
    private int id;
    private int idChapter;
    private String questionNumber;
    private String responseType;
    private String commentQuestion;
    private String questionText;
    private Date createdAt;
    private Date updatedAt;

    public Question() {
    }
    public Question(int id, int idChapter, String questionNumber, String responseType, String commentQuestion, String questionText) {
        this.setId(id);
        this.setIdChapter(idChapter);
        this.setQuestionNumber(questionNumber);
        this.setResponseType(responseType);
        this.setCommentQuestion(commentQuestion);
        this.setQuestionText(questionText);
    }

    public Question(int id,int idChapter, String questionNumber, String responseType, String commentQuestion, String questionText, Date createdAt, Date updatedAt) {
        this.setId(id);
        this.setIdChapter(idChapter);
        this.setQuestionNumber(questionNumber);
        this.setResponseType(responseType);
        this.setCommentQuestion(commentQuestion);
        this.setQuestionText(questionText);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
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

	public int getIdChapter() {
		return idChapter;
	}

	public void setIdChapter(int idChapter) {
		this.idChapter = idChapter;
	}

}
