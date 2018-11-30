
package com.vietinterview.getbee.api.response.jobcustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("expireDate")
    @Expose
    private String expireDate;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("countView")
    @Expose
    private Integer countView;
    @SerializedName("countOffer")
    @Expose
    private Integer countOffer;
    @SerializedName("countCv")
    @Expose
    private Integer countCv;
    @SerializedName("limited")
    @Expose
    private Integer limited;
    @SerializedName("submitDate")
    @Expose
    private String submitDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public Integer getCountView() {
        return countView;
    }

    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    public Integer getCountOffer() {
        return countOffer;
    }

    public void setCountOffer(Integer countOffer) {
        this.countOffer = countOffer;
    }

    public Integer getCountCv() {
        return countCv;
    }

    public void setCountCv(Integer countCv) {
        this.countCv = countCv;
    }

    public Integer getLimited() {
        return limited;
    }

    public void setLimited(Integer limited) {
        this.limited = limited;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

}
