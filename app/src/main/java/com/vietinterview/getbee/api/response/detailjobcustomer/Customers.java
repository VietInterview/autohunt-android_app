
package com.vietinterview.getbee.api.response.detailjobcustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customers {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("contactEmail")
    @Expose
    private String contactEmail;
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("contactPhone")
    @Expose
    private String contactPhone;
    @SerializedName("countryId")
    @Expose
    private Integer countryId;
    @SerializedName("createdBy")
    @Expose
    private Integer createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("descripstion")
    @Expose
    private String descripstion;
    @SerializedName("humanResourcesId")
    @Expose
    private Integer humanResourcesId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("logoImg")
    @Expose
    private String logoImg;
    @SerializedName("salerId")
    @Expose
    private Integer salerId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("updatedBy")
    @Expose
    private Integer updatedBy;
    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("videoLink")
    @Expose
    private String videoLink;
    @SerializedName("workingTime")
    @Expose
    private Integer workingTime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescripstion() {
        return descripstion;
    }

    public void setDescripstion(String descripstion) {
        this.descripstion = descripstion;
    }

    public Integer getHumanResourcesId() {
        return humanResourcesId;
    }

    public void setHumanResourcesId(Integer humanResourcesId) {
        this.humanResourcesId = humanResourcesId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public Integer getSalerId() {
        return salerId;
    }

    public void setSalerId(Integer salerId) {
        this.salerId = salerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public Integer getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Integer workingTime) {
        this.workingTime = workingTime;
    }

}
