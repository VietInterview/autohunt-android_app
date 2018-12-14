
package com.vietinterview.getbee.api.response.detailprocessresume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstOfferHi {

    @SerializedName("curency")
    @Expose
    private Integer curency;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("emailTemplate")
    @Expose
    private String emailTemplate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("round")
    @Expose
    private String round;
    @SerializedName("salary")
    @Expose
    private Integer salary;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("workAddress")
    @Expose
    private String workAddress;
    @SerializedName("workTime")
    @Expose
    private String workTime;

    public Integer getCurency() {
        return curency;
    }

    public void setCurency(Integer curency) {
        this.curency = curency;
    }

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

}
