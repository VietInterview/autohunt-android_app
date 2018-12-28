
package com.vietinterview.getbee.api.response.detailprocessresume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CvProcessInfo {

    @SerializedName("birthday")
    @Expose
    private Integer birthday;
    @SerializedName("collEmail")
    @Expose
    private String collEmail;
    @SerializedName("collName")
    @Expose
    private String collName;
    @SerializedName("collPhone")
    @Expose
    private String collPhone;
    @SerializedName("currencyId")
    @Expose
    private Integer currencyId;
    @SerializedName("currencyName")
    @Expose
    private String currencyName;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("educationLevelName")
    @Expose
    private String educationLevelName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("pictureUrl")
    @Expose
    private String pictureUrl;
    @SerializedName("positionName")
    @Expose
    private String positionName;
    @SerializedName("reasonRejectId")
    @Expose
    private Integer reasonRejectId;
    @SerializedName("reasonRejectName")
    @Expose
    private String reasonRejectName;
    @SerializedName("rejectNote")
    @Expose
    private String rejectNote;
    @SerializedName("rejectStep")
    @Expose
    private Integer rejectStep;
    @SerializedName("salary")
    @Expose
    private Long salary;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("workAddress")
    @Expose
    private String workAddress;
    @SerializedName("yearsExperienceName")
    @Expose
    private String yearsExperienceName;

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getCollEmail() {
        return collEmail;
    }

    public void setCollEmail(String collEmail) {
        this.collEmail = collEmail;
    }

    public String getCollName() {
        return collName;
    }

    public void setCollName(String collName) {
        this.collName = collName;
    }

    public String getCollPhone() {
        return collPhone;
    }

    public void setCollPhone(String collPhone) {
        this.collPhone = collPhone;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public String getEducationLevelName() {
        return educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName) {
        this.educationLevelName = educationLevelName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getReasonRejectId() {
        return reasonRejectId;
    }

    public void setReasonRejectId(Integer reasonRejectId) {
        this.reasonRejectId = reasonRejectId;
    }

    public String getReasonRejectName() {
        return reasonRejectName;
    }

    public void setReasonRejectName(String reasonRejectName) {
        this.reasonRejectName = reasonRejectName;
    }

    public String getRejectNote() {
        return rejectNote;
    }

    public void setRejectNote(String rejectNote) {
        this.rejectNote = rejectNote;
    }

    public Integer getRejectStep() {
        return rejectStep;
    }

    public void setRejectStep(Integer rejectStep) {
        this.rejectStep = rejectStep;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
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

    public String getYearsExperienceName() {
        return yearsExperienceName;
    }

    public void setYearsExperienceName(String yearsExperienceName) {
        this.yearsExperienceName = yearsExperienceName;
    }

}
