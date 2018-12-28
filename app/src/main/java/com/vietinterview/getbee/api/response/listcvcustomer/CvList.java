
package com.vietinterview.getbee.api.response.listcvcustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CvList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("educationLevel")
    @Expose
    private String educationLevel;
    @SerializedName("experienceYear")
    @Expose
    private String experienceYear;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("companyNameOld")
    @Expose
    private String companyNameOld;
    @SerializedName("countDay")
    @Expose
    private Integer countDay;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(String experienceYear) {
        this.experienceYear = experienceYear;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCompanyNameOld() {
        return companyNameOld;
    }

    public void setCompanyNameOld(String companyNameOld) {
        this.companyNameOld = companyNameOld;
    }

    public Integer getCountDay() {
        return countDay;
    }

    public void setCountDay(Integer countDay) {
        this.countDay = countDay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
