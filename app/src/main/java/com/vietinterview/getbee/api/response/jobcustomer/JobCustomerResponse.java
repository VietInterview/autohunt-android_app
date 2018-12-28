
package com.vietinterview.getbee.api.response.jobcustomer;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobCustomerResponse {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("jobList")
    @Expose
    private ArrayList<JobList> jobList = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ArrayList<JobList> getJobList() {
        return jobList;
    }

    public void setJobList(ArrayList<JobList> jobList) {
        this.jobList = jobList;
    }

}
