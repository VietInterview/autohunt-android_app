
package com.vietinterview.getbee.api.response.detailcvcustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstEmploymentHi {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("fromMonth")
    @Expose
    private Integer fromMonth;
    @SerializedName("toMonth")
    @Expose
    private Integer toMonth;
    @SerializedName("isCurrent")
    @Expose
    private Integer isCurrent;
    @SerializedName("salaryCurency")
    @Expose
    private Integer salaryCurency;
    @SerializedName("salary")
    @Expose
    private Integer salary;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;
    @SerializedName("achievement")
    @Expose
    private String achievement;
    @SerializedName("humanResources")
    @Expose
    private Integer humanResources;
    @SerializedName("currentJob")
    @Expose
    private Integer currentJob;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(Integer fromMonth) {
        this.fromMonth = fromMonth;
    }

    public Integer getToMonth() {
        return toMonth;
    }

    public void setToMonth(Integer toMonth) {
        this.toMonth = toMonth;
    }

    public Integer getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Integer isCurrent) {
        this.isCurrent = isCurrent;
    }

    public Integer getSalaryCurency() {
        return salaryCurency;
    }

    public void setSalaryCurency(Integer salaryCurency) {
        this.salaryCurency = salaryCurency;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public Integer getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(Integer humanResources) {
        this.humanResources = humanResources;
    }

    public Integer getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Integer currentJob) {
        this.currentJob = currentJob;
    }

}
