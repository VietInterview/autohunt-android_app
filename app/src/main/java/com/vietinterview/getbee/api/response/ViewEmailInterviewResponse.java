
package com.vietinterview.getbee.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewEmailInterviewResponse {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("emailTemplate")
    @Expose
    private String emailTemplate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("interviewAddress")
    @Expose
    private String interviewAddress;
    @SerializedName("interviewDate")
    @Expose
    private String interviewDate;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("round")
    @Expose
    private String round;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public String getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(String emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterviewAddress() {
        return interviewAddress;
    }

    public void setInterviewAddress(String interviewAddress) {
        this.interviewAddress = interviewAddress;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
