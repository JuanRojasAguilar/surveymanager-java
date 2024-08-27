package com.survey.responsequestion.domain.entity;

public class ResponseQuestionDto {
    private String chapter;
    private String question;
    private String response;
    private String subResponse;
    
    public ResponseQuestionDto() {
    }

    public ResponseQuestionDto(String chapter, String question, String response, String subResponse) {
        this.chapter = chapter;
        this.question = question;
        this.response = response;
        this.subResponse = subResponse;
    }

    public ResponseQuestionDto(String chapter, String question, String response) {
        this.chapter = chapter;
        this.question = question;
        this.response = response;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSubResponse() {
        return subResponse;
    }

    public void setSubResponse(String subResponse) {
        this.subResponse = subResponse;
    }

    
}
