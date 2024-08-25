package com.survey.responsequestion.domain.entity;

public class ResponseQuestion {
    private int id;
    private Integer response_id;
    private Integer subresponse_id;
    private String response_text;
    
    public ResponseQuestion() {
    }

    public ResponseQuestion(int id, int response_id, int subresponse_id, String response_text) {
        this.id = id;
        this.response_id = response_id;
        this.subresponse_id = subresponse_id;
        this.response_text = response_text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getResponse_id() {
        return response_id;
    }

    public void setResponse_id(Integer response_id) {
        this.response_id = response_id;
    }

    public Integer getSubresponse_id() {
        return subresponse_id;
    }

    public void setSubresponse_id(Integer subresponse_id) {
        this.subresponse_id = subresponse_id;
    }

    public String getResponse_text() {
        return response_text;
    }

    public void setResponse_text(String response_text) {
        this.response_text = response_text;
    }

    
}
