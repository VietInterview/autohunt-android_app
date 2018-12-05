
package com.vietinterview.getbee.api.response.listcvcustomer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CvsCustomerResponse {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("cvList")
    @Expose
    private List<CvList> cvList = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<CvList> getCvList() {
        return cvList;
    }

    public void setCvList(List<CvList> cvList) {
        this.cvList = cvList;
    }

}
