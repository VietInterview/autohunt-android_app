
package com.vietinterview.getbee.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitCVResponse {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("type")
    @Expose
    private Integer type;

    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
