
package com.vietinterview.getbee.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddRemoveJobResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("collaboratoId")
    @Expose
    private Integer collaboratoId;
    @SerializedName("cvId")
    @Expose
    private Object cvId;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("updateDate")
    @Expose
    private String updateDate;
    @SerializedName("updateBy")
    @Expose
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollaboratoId() {
        return collaboratoId;
    }

    public void setCollaboratoId(Integer collaboratoId) {
        this.collaboratoId = collaboratoId;
    }

    public Object getCvId() {
        return cvId;
    }

    public void setCvId(Object cvId) {
        this.cvId = cvId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

}
