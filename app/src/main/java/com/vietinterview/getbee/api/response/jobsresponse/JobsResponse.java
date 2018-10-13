
package com.vietinterview.getbee.api.response.jobsresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobsResponse {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("jobList")
    @Expose
    private List<JobList> jobList = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<JobList> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobList> jobList) {
        this.jobList = jobList;
    }

}
