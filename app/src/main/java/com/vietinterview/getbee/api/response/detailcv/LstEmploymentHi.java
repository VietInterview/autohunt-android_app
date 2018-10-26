
package com.vietinterview.getbee.api.response.detailcv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstEmploymentHi {

    @SerializedName("achievement")
    @Expose
    private String achievement;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("fromMonth")
    @Expose
    private Integer fromMonth;
    @SerializedName("humanResources")
    @Expose
    private Integer humanResources;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("isCurrent")
    @Expose
    private Integer isCurrent;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;
    @SerializedName("salary")
    @Expose
    private Integer salary;
    @SerializedName("salaryCurency")
    @Expose
    private Integer salaryCurency;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("toMonth")
    @Expose
    private Integer toMonth;

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public Integer getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(Integer fromMonth) {
        this.fromMonth = fromMonth;
    }

    public Integer getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(Integer humanResources) {
        this.humanResources = humanResources;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Integer isCurrent) {
        this.isCurrent = isCurrent;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getSalaryCurency() {
        return salaryCurency;
    }

    public void setSalaryCurency(Integer salaryCurency) {
        this.salaryCurency = salaryCurency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getToMonth() {
        return toMonth;
    }

    public void setToMonth(Integer toMonth) {
        this.toMonth = toMonth;
    }

}
