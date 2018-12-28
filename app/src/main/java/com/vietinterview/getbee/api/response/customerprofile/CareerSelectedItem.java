
package com.vietinterview.getbee.api.response.customerprofile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CareerSelectedItem {

    @SerializedName("careerId")
    @Expose
    private Integer careerId;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lstCareer")
    @Expose
    private List<LstCareer> lstCareer = null;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getCareerId() {
        return careerId;
    }

    public void setCareerId(Integer careerId) {
        this.careerId = careerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<LstCareer> getLstCareer() {
        return lstCareer;
    }

    public void setLstCareer(List<LstCareer> lstCareer) {
        this.lstCareer = lstCareer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
