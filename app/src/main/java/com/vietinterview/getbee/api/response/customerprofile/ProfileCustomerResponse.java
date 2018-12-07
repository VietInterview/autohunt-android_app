
package com.vietinterview.getbee.api.response.customerprofile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileCustomerResponse {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("careerSelectedItems")
    @Expose
    private List<CareerSelectedItem> careerSelectedItems = null;
    @SerializedName("city")
    @Expose
    private List<City> city = null;
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
    @SerializedName("country")
    @Expose
    private List<Country> country = null;
    @SerializedName("customerImg")
    @Expose
    private List<CustomerImg> customerImg = null;
    @SerializedName("customerWelfare")
    @Expose
    private List<CustomerWelfare> customerWelfare = null;
    @SerializedName("descripstion")
    @Expose
    private String descripstion;
    @SerializedName("humanResources")
    @Expose
    private List<HumanResource> humanResources = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("logoImg")
    @Expose
    private String logoImg;
    @SerializedName("saleAssignDto")
    @Expose
    private SaleAssignDto saleAssignDto;
    @SerializedName("salerId")
    @Expose
    private Integer salerId;
    @SerializedName("timeservingSelectedItems")
    @Expose
    private List<TimeservingSelectedItem> timeservingSelectedItems = null;
    @SerializedName("videoLink")
    @Expose
    private String videoLink;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CareerSelectedItem> getCareerSelectedItems() {
        return careerSelectedItems;
    }

    public void setCareerSelectedItems(List<CareerSelectedItem> careerSelectedItems) {
        this.careerSelectedItems = careerSelectedItems;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
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

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    public List<CustomerImg> getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(List<CustomerImg> customerImg) {
        this.customerImg = customerImg;
    }

    public List<CustomerWelfare> getCustomerWelfare() {
        return customerWelfare;
    }

    public void setCustomerWelfare(List<CustomerWelfare> customerWelfare) {
        this.customerWelfare = customerWelfare;
    }

    public String getDescripstion() {
        return descripstion;
    }

    public void setDescripstion(String descripstion) {
        this.descripstion = descripstion;
    }

    public List<HumanResource> getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(List<HumanResource> humanResources) {
        this.humanResources = humanResources;
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

    public SaleAssignDto getSaleAssignDto() {
        return saleAssignDto;
    }

    public void setSaleAssignDto(SaleAssignDto saleAssignDto) {
        this.saleAssignDto = saleAssignDto;
    }

    public Integer getSalerId() {
        return salerId;
    }

    public void setSalerId(Integer salerId) {
        this.salerId = salerId;
    }

    public List<TimeservingSelectedItem> getTimeservingSelectedItems() {
        return timeservingSelectedItems;
    }

    public void setTimeservingSelectedItems(List<TimeservingSelectedItem> timeservingSelectedItems) {
        this.timeservingSelectedItems = timeservingSelectedItems;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

}
