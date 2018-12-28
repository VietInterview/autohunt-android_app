
package com.vietinterview.getbee.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RejectResponse {

    @SerializedName("cvId")
    @Expose
    private Integer cvId;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("reasonNote")
    @Expose
    private String reasonNote;
    @SerializedName("reasonRejectId")
    @Expose
    private Integer reasonRejectId;
    @SerializedName("rejectStep")
    @Expose
    private Integer rejectStep;

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

    public String getReasonNote() {
        return reasonNote;
    }

    public void setReasonNote(String reasonNote) {
        this.reasonNote = reasonNote;
    }

    public Integer getReasonRejectId() {
        return reasonRejectId;
    }

    public void setReasonRejectId(Integer reasonRejectId) {
        this.reasonRejectId = reasonRejectId;
    }

    public Integer getRejectStep() {
        return rejectStep;
    }

    public void setRejectStep(Integer rejectStep) {
        this.rejectStep = rejectStep;
    }

}
