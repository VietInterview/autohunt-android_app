
package com.vietinterview.getbee.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendOfferResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("round")
    @Expose
    private String round;
    @SerializedName("salary")
    @Expose
    private Long salary;
    @SerializedName("curency")
    @Expose
    private Integer curency;
    @SerializedName("workTime")
    @Expose
    private String workTime;
    @SerializedName("workAddress")
    @Expose
    private String workAddress;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("emailTemplate")
    @Expose
    private String emailTemplate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Integer getCurency() {
        return curency;
    }

    public void setCurency(Integer curency) {
        this.curency = curency;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(String emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

}
