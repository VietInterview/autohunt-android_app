
package com.vietinterview.getbee.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoToWorkUpdateResponse {

    @SerializedName("countUpdate")
    @Expose
    private Integer countUpdate;
    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("numDayWarranty")
    @Expose
    private Integer numDayWarranty;
    @SerializedName("startWorkDate")
    @Expose
    private String startWorkDate;
    @SerializedName("updateBy")
    @Expose
    private Integer updateBy;
    @SerializedName("updateDate")
    @Expose
    private String updateDate;
    @SerializedName("warrantyExpireDate")
    @Expose
    private String warrantyExpireDate;

    public Integer getCountUpdate() {
        return countUpdate;
    }

    public void setCountUpdate(Integer countUpdate) {
        this.countUpdate = countUpdate;
    }

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
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

    public Integer getNumDayWarranty() {
        return numDayWarranty;
    }

    public void setNumDayWarranty(Integer numDayWarranty) {
        this.numDayWarranty = numDayWarranty;
    }

    public String getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(String startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getWarrantyExpireDate() {
        return warrantyExpireDate;
    }

    public void setWarrantyExpireDate(String warrantyExpireDate) {
        this.warrantyExpireDate = warrantyExpireDate;
    }

}
