
package com.vietinterview.getbee.api.response.jobsresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("careerId")
    @Expose
    private Integer careerId;
    @SerializedName("jobLevel")
    @Expose
    private Integer jobLevel;
    @SerializedName("lstCityId")
    @Expose
    private String lstCityId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("fromSalary")
    @Expose
    private Integer fromSalary;
    @SerializedName("toSalary")
    @Expose
    private Integer toSalary;
    @SerializedName("currency")
    @Expose
    private Integer currency;
    @SerializedName("fee")
    @Expose
    private Integer fee;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;
    @SerializedName("submitDate")
    @Expose
    private String submitDate;
    @SerializedName("expireDate")
    @Expose
    private String expireDate;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("isHotjob")
    @Expose
    private Integer isHotjob;
    @SerializedName("countCv")
    @Expose
    private Integer countCv;
    @SerializedName("countColl")
    @Expose
    private Integer countColl;
    @SerializedName("countInterview")
    @Expose
    private Integer countInterview;
    @SerializedName("countOffer")
    @Expose
    private Integer countOffer;
    @SerializedName("countGotoWork")
    @Expose
    private Integer countGotoWork;
    @SerializedName("createDate")
    @Expose
    private Object createDate;
    @SerializedName("createBy")
    @Expose
    private Object createBy;
    @SerializedName("updateDate")
    @Expose
    private Object updateDate;
    @SerializedName("updateBy")
    @Expose
    private Object updateBy;
    @SerializedName("collStatus")
    @Expose
    private Object collStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getCareerId() {
        return careerId;
    }

    public void setCareerId(Integer careerId) {
        this.careerId = careerId;
    }

    public Integer getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Integer jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getLstCityId() {
        return lstCityId;
    }

    public void setLstCityId(String lstCityId) {
        this.lstCityId = lstCityId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getFromSalary() {
        return fromSalary;
    }

    public void setFromSalary(Integer fromSalary) {
        this.fromSalary = fromSalary;
    }

    public Integer getToSalary() {
        return toSalary;
    }

    public void setToSalary(Integer toSalary) {
        this.toSalary = toSalary;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsHotjob() {
        return isHotjob;
    }

    public void setIsHotjob(Integer isHotjob) {
        this.isHotjob = isHotjob;
    }

    public Integer getCountCv() {
        return countCv;
    }

    public void setCountCv(Integer countCv) {
        this.countCv = countCv;
    }

    public Integer getCountColl() {
        return countColl;
    }

    public void setCountColl(Integer countColl) {
        this.countColl = countColl;
    }

    public Integer getCountInterview() {
        return countInterview;
    }

    public void setCountInterview(Integer countInterview) {
        this.countInterview = countInterview;
    }

    public Integer getCountOffer() {
        return countOffer;
    }

    public void setCountOffer(Integer countOffer) {
        this.countOffer = countOffer;
    }

    public Integer getCountGotoWork() {
        return countGotoWork;
    }

    public void setCountGotoWork(Integer countGotoWork) {
        this.countGotoWork = countGotoWork;
    }

    public Object getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getCollStatus() {
        return collStatus;
    }

    public void setCollStatus(Object collStatus) {
        this.collStatus = collStatus;
    }

}
